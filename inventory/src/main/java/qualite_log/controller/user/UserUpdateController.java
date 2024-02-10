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
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.model.User;
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
            if (person.getType().equals(USER)) {
                System.out.println("User");
                Data.getInstance().getUsers().remove(person);
                if (selectedRole.equals(ADMINISTRATOR)) {
                    Data.getInstance().getAdministrators().add(new Administrator(person.getLastName(), person.getFirstName(), person.getEmail()));
                    person.setType(ADMINISTRATOR);
                    System.out.println("Admin added");
                }
            } else if (person.getType().equals(ADMINISTRATOR)) {
                System.out.println("Admin");
                Data.getInstance().getAdministrators().remove(person);
                if (selectedRole.equals(USER)) {
                    Data.getInstance().getUsers().add(new User(person.getLastName(), person.getFirstName(), person.getEmail()));
                    person.setType(USER);
                    System.out.println("User added");
                }
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
