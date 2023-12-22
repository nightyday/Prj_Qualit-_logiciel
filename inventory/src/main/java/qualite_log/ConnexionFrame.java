package qualite_log;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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

    }

}
