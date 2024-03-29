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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
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
    private Rectangle warningRectangle;

    @FXML
    private Label warningLabel;

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
                if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", mailTextField.getText())) {
                    warningRectangle.setX(mailTextField.getLayoutX() - 1);
                    warningRectangle.setY(mailTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(mailTextField.getWidth() + 2);
                    warningRectangle.setHeight(mailTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);

                    warningLabel.setLayoutX(mailTextField.getLayoutX());
                    warningLabel.setLayoutY(mailTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else if (!Pattern.matches("^[a-zA-Z0-9\\-]{1,30}$", nomTextField.getText())) {
                    warningRectangle.setX(nomTextField.getLayoutX() - 1);
                    warningRectangle.setY(nomTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(nomTextField.getWidth() + 2);
                    warningRectangle.setHeight(nomTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);
                    
                    warningLabel.setLayoutX(nomTextField.getLayoutX());
                    warningLabel.setLayoutY(nomTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else if (!Pattern.matches("^[a-zA-Z0-9\\-]{1,30}$", prenomTextField.getText())) {
                    warningRectangle.setX(prenomTextField.getLayoutX() - 1);
                    warningRectangle.setY(prenomTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(prenomTextField.getWidth() + 2);
                    warningRectangle.setHeight(prenomTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);
                    
                    warningLabel.setLayoutX(prenomTextField.getLayoutX());
                    warningLabel.setLayoutY(prenomTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else {
                    try  {
                        if (roleComboBox.getValue().equals("administrateur")) {
                                    Data.getInstance().getAdministrators().add(new Administrator(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
                        }
                        else {
                            Data.getInstance().getUsers().add(new User(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
                        }

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserListFrame.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        anchorPane.getChildren().clear();
                        anchorPane.getChildren().add(root);
                    }
                    catch (Exception e) {
                        WarningFrame warning = new WarningFrame("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
                        warning.show();
                    }
                }
            }
        });
    }

}
