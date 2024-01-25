package qualite_log.controller.user;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.model.User;

public class UserUpdateController {

    private static final String ADMINISTRATEUR = "administrateur";
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

        fillComboBoxes();
    }

    private void fillComboBoxes() {
        List<String> emailData = new ArrayList<>();
        persons.forEach(person -> emailData.add(person.getEmail()));
        mailComboBox.getItems().addAll(emailData);
        roleComboBox.getItems().addAll(ADMINISTRATEUR, USER);
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
                updateUser();
                switchToUserListView();
            } catch (Exception e) {
                showAlert("Erreur", "Impossible de modifier les informations de ce compte car celui-ci est actuellement utilisé.");
            }
        } else {
            showAlert("Erreur", "Format de la saisie non conforme.");
        }
    }

    private boolean validateInput() {
        return Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", mailTextField.getText()) &&
               Pattern.matches("^[a-zA-Z0-9]{1,30}$", nomTextField.getText()) &&
               Pattern.matches("^[a-zA-Z0-9]{1,30}$", prenomTextField.getText());
    }

    private void updateUser() {
        String selectedMail = mailComboBox.getValue();
        Person personSelected = persons.stream()
                                       .filter(p -> p.getEmail().equals(selectedMail))
                                       .findFirst()
                                       .orElse(null);
        if (personSelected != null) {
            personSelected.setEmail(mailTextField.getText());
            personSelected.setLastName(nomTextField.getText());
            personSelected.setFirstName(prenomTextField.getText());
            switchRoleIfNecessary(personSelected);
        }
    }

    private void switchRoleIfNecessary(Person person) {
        if (!person.getType().equals(roleComboBox.getValue())) {
            if (roleComboBox.getValue().equals(ADMINISTRATEUR)) {
                Data.getInstance().getUsers().remove(person);
                Data.getInstance().getAdministrators().add(new Administrator(person.getLastName(), person.getFirstName(), person.getEmail()));
            } else {
                Data.getInstance().getAdministrators().remove(person);
                Data.getInstance().getUsers().add(new User(person.getLastName(), person.getFirstName(), person.getEmail()));
            }
        }
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

