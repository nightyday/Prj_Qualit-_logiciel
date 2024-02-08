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
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.User;

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
        return Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", mailTextField.getText()) &&
               Pattern.matches("^[a-zA-Z\\u00C0-\\u00FF]{1,30}$", nomTextField.getText()) &&
               Pattern.matches("^[a-zA-Z\\u00C0-\\u00FF]{1,30}$", prenomTextField.getText());
    }

    public void createUser() {
        if (roleComboBox.getValue().equals("administrateur")) {
            Data.getInstance().getAdministrators().add(new Administrator(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
        } else {
            Data.getInstance().getUsers().add(new User(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
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
