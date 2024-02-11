package qualite_log.controller.user;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
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
import qualite_log.data_import.DataReader;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.User;
import qualite_log.util.ValidationConstants;

public class UserCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createButton;

    @FXML
    private TextField mailTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    void initialize() {
        Data.updateData();
        
        roleComboBox.getItems().addAll(Arrays.asList("administrateur", "utilisateur"));
        createButton.setOnAction(this::handleCreateAction);
    }

    @FXML
    public void handleCreateAction(ActionEvent event) {
        if (validateInput()) {
            try {
                createUser();
                switchToUserListView();
            } catch (Exception e) {
                showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
            }
        } else {
            showAlert("Erreur", "Format de la saisie non conforme.");
        }
    }

    public boolean validateInput() {
        return Pattern.matches(ValidationConstants.EMAIL_REGEX, mailTextField.getText()) &&
               Pattern.matches(ValidationConstants.USERNAME_REGEX, nomTextField.getText()) &&
               Pattern.matches(ValidationConstants.USERNAME_REGEX, prenomTextField.getText());
    }

    public void createUser() {
        if (roleComboBox.getValue().equals("administrateur")) {
            Administrator newAdmin = new Administrator(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText());
            Data.getInstance().addAdministrator(newAdmin);

            DataWriter.extractPassword(newAdmin, "password" + "a" + newAdmin.getId());
            DataWriter.extractAdministrators(Data.getInstance()); // On met à jour les fichiers .json
        } else {
            User newUser = new User(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText());
            Data.getInstance().addUsers(newUser);
            
            DataWriter.extractPassword(newUser, "password" + "u" + newUser.getId());
            DataWriter.extractUsers(Data.getInstance()); // On met à jour les fichiers .json
        }
    }

    public void switchToUserListView() {
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
