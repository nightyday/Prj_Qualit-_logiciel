package qualite_log.view;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.User;

public class UserCreateFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createButton;

    @FXML
    private Label createLabel;

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
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'UserCreateFrame.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'UserCreateFrame.fxml'.";
        assert createLabel != null : "fx:id=\"createLabel\" was not injected: check your FXML file 'UserCreateFrame.fxml'.";
        assert mailTextField != null : "fx:id=\"mailTextField\" was not injected: check your FXML file 'UserCreateFrame.fxml'.";
        assert nomTextField != null : "fx:id=\"nomTextField\" was not injected: check your FXML file 'UserCreateFrame.fxml'.";
        assert prenomTextField != null : "fx:id=\"prenomTextField\" was not injected: check your FXML file 'UserCreateFrame.fxml'.";
        assert roleComboBox != null : "fx:id=\"roleComboBox\" was not injected: check your FXML file 'UserCreateFrame.fxml'.";

        // Add elements in the comboBoxs
        roleComboBox.getItems().addAll(Arrays.asList("administrateur", "utilisateur"));

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", mailTextField.getText()) && Pattern.matches("^[a-zA-Z0-9]{1,30}$", nomTextField.getText()) && Pattern.matches("^[a-zA-Z0-9]{1,30}$", prenomTextField.getText())) {
                    try  {
                        if (roleComboBox.getValue().equals("administrateur")) {
                                    Data.getInstance().getAdministrators().add(new Administrator(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
                        }
                        else {
                            Data.getInstance().getUsers().add(new User(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
                        }
                    }
                    catch (Exception e) {
                        try {
                            Alert alert = new Alert(AlertType.WARNING);
            
                            alert.setTitle("Erreur");
                            alert.setHeaderText(null);
                            alert.setContentText("Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
                            alert.showAndWait();
                        }
                        catch (Exception error) {
                            error.printStackTrace();
                        }
                    }
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserListFrame.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        anchorPane.getChildren().clear();
                        anchorPane.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        Alert alert = new Alert(AlertType.WARNING);

                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Format de la saisie non conforme.");
                        alert.showAndWait();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
