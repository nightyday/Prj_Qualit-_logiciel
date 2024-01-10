package qualite_log.view;

import java.net.URL;
import java.util.ResourceBundle;

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

public class UserUpdateFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<?> mailComboBox;

    @FXML
    private TextField mailTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private ComboBox<?> roleComboBox;

    @FXML
    private Button updateButton;

    @FXML
    private Label updateLabel;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert mailComboBox != null : "fx:id=\"mailComboBox\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert mailTextField != null : "fx:id=\"mailTextField\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert nomTextField != null : "fx:id=\"nomTextField\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert prenomTextField != null : "fx:id=\"prenomTextField\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert roleComboBox != null : "fx:id=\"roleComboBox\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert updateLabel != null : "fx:id=\"updateLabel\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
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
        });
    }
}
