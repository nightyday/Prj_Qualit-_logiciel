package qualite_log;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ConnexionFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPanel;

    @FXML
    private Button button;

    @FXML
    private Label label;

    @FXML
    private PasswordField passWordField;

    @FXML
    private TextField textFielMatricule;

    @FXML
    private VBox vBox;

    @FXML
    void initialize() {
        assert anchorPanel != null : "fx:id=\"anchorPanel\" was not injected: check your FXML file 'ConnexionFrame.fxml'.";
        assert button != null : "fx:id=\"button\" was not injected: check your FXML file 'ConnexionFrame.fxml'.";
        assert label != null : "fx:id=\"label\" was not injected: check your FXML file 'ConnexionFrame.fxml'.";
        assert passWordField != null : "fx:id=\"passWordField\" was not injected: check your FXML file 'ConnexionFrame.fxml'.";
        assert textFielMatricule != null : "fx:id=\"textFielMatricule\" was not injected: check your FXML file 'ConnexionFrame.fxml'.";
        assert vBox != null : "fx:id=\"vBox\" was not injected: check your FXML file 'ConnexionFrame.fxml'.";

        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/MenuAdminFrame.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    vBox.getChildren().clear();
                    vBox.getChildren().add(root);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
