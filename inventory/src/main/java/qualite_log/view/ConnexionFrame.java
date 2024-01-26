package qualite_log.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import qualite_log.model.Administrator;
import qualite_log.model.User;
import qualite_log.session.Authentification;
import qualite_log.session.SessionManager;

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
                String matricule = textFielMatricule.getText();
                String password = passWordField.getText();
                Administrator admin = Authentification.authenticateAdmin(matricule, password);
                User user = Authentification.authenticateUser(matricule, password);
                if (user != null) {
                    SessionManager.setCurrentUser(user);
                    // Chargez l interface User
                    loadUserMenu();
                } else if (admin != null) {
                    SessionManager.setCurrentAdmin(admin);
                    // Chargez l interface Admin
                    loadAdminMenu();
                } else {
                    label.setText("Matricule ou mot de passe incorrect");
                }
            }

            private void loadUserMenu() {
                ////////// Visible invisible./////////////////////
            }

            private void loadAdminMenu() {
                ////////// Visible invisible./////////////////////
            }
        });
    }
}