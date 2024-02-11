package qualite_log.controller.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import qualite_log.data_import.DataReader;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.model.User;
import qualite_log.model.Booking;
import qualite_log.util.FxUtil;
import qualite_log.util.ValidationConstants;

public class UserUpdateController {

    private static final String ADMINISTRATOR = "administrator";
    private static final String USER = "user";

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> mailComboBox;

    @FXML
    private TextField mailTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button updateButton;

    private List<Person> persons;

    @FXML
    void initialize() {
        Data.updateData();

        persons = new ArrayList<>();
        persons.addAll(Data.getInstance().getUsers());
        persons.addAll(Data.getInstance().getAdministrators());
        FxUtil.addTextChangeListener(mailTextField, ValidationConstants.EMAIL_REGEX);
        FxUtil.addTextChangeListener(nomTextField, ValidationConstants.USERNAME_REGEX);
        FxUtil.addTextChangeListener(prenomTextField, ValidationConstants.USERNAME_REGEX);
        fillComboBoxes();
    }

    private void fillComboBoxes() {
        List<String> emailData = new ArrayList<>();
        persons.forEach(person -> emailData.add(person.getEmail()));
        mailComboBox.getItems().addAll(emailData);
        roleComboBox.getItems().addAll(ADMINISTRATOR, USER);
    }

    @FXML
    public void handleMailSelection(ActionEvent event) {
        String selectedMail = mailComboBox.getValue();
        Person personSelected = persons.stream()
                .filter(p -> p.getEmail().equals(selectedMail))
                .findFirst()
                .orElse(null);
        if (personSelected != null) {
            mailTextField.setText(personSelected.getEmail());
            nomTextField.setText(personSelected.getLastName());
            prenomTextField.setText(personSelected.getFirstName());
            roleComboBox.setValue(personSelected.getType());
        }
    }

    @FXML
    public void handleUpdateAction(ActionEvent event) {
        if (validateInput()) {
            try {
                String selectedMail = mailComboBox.getValue();
                Person personSelected = persons.stream()
                        .filter(p -> p.getEmail().equals(selectedMail))
                        .findFirst()
                        .orElse(null);
                if (personSelected != null) {
                    updatePerson(personSelected);
                    switchToUserListView();
                    
                    DataWriter.extractAdministrators(Data.getInstance()); // On met à jour les fichiers .json
                    DataWriter.extractUsers(Data.getInstance()); // On met à jour les fichiers .json
                }
            } catch (Exception e) {
                showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
            }
        } else {
            showAlert("Erreur", "Format de la saisie non conforme.");
        }
    }

    private boolean validateInput() {
        List<Pair<TextField, String>> fieldRegexPairs = Arrays.asList(
                new Pair<>(mailTextField, ValidationConstants.EMAIL_REGEX),
                new Pair<>(nomTextField, ValidationConstants.USERNAME_REGEX),
                new Pair<>(prenomTextField, ValidationConstants.USERNAME_REGEX));
        return FxUtil.validateInputs(fieldRegexPairs);
    }

    private void updatePerson(Person person) {
        person.setLastName(nomTextField.getText());
        person.setFirstName(prenomTextField.getText());
        person.setEmail(mailTextField.getText());
        switchRoleIfNecessary(person);
    
        // Mettre à jour les listes dans Data et la liste persons
        updatePersonsList();
    }
    
    private void switchRoleIfNecessary(Person person) {
        String selectedRole = roleComboBox.getValue();
        if (!person.getType().equals(selectedRole)) {
            List<User> users = Data.getInstance().getUsers();
            List<Administrator> admins = Data.getInstance().getAdministrators();
            Data data = Data.getInstance();

            if (person.getType().equals(USER)) {
                System.out.println("User");

                Administrator newAdmin = new Administrator(person.getLastName(), person.getFirstName(), person.getEmail());
                for (Booking booking : data.getBookings()) {
                    if(booking.getEmprunter().getId().equals(person.getId())) {
                        booking.setEmprunter(newAdmin);
                    }
                }
                DataWriter.extractPassword(newAdmin, DataReader.getPassword(person));

                users.remove(person);
                admins.add(newAdmin);
                data.setAdministrators(admins);
                data.setUsers(users);
                
                DataWriter.extractBookings(Data.getInstance()); // On met à jour les fichiers .json

                System.out.println("User added");
            }

            if (person.getType().equals(ADMINISTRATOR)) {
                System.out.println("Admin");

                User newUser = new User(person.getLastName(), person.getFirstName(), person.getEmail());
                for (Booking booking : data.getBookings()) {
                    if(booking.getEmprunter().getId().equals(person.getId())) {
                        booking.setEmprunter(newUser);
                    }
                }
                DataWriter.extractPassword(newUser, DataReader.getPassword(person));

                users.add(newUser);
                admins.remove(person);
                data.setAdministrators(admins);
                data.setUsers(users);
                
                DataWriter.extractBookings(Data.getInstance()); // On met à jour les fichiers .json

                System.out.println("Admin added");
            }
            
        }
    }
    
    private void updatePersonsList() {
        persons.clear();
        persons.addAll(Data.getInstance().getUsers());
        persons.addAll(Data.getInstance().getAdministrators());
        fillComboBoxes(); // Mettre à jour les données de la comboBox
    }
    

    private void switchToUserListView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserListFrame.fxml"));
            Parent root = fxmlLoader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
