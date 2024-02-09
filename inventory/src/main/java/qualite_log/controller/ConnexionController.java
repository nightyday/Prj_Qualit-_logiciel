package qualite_log.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import qualite_log.model.Administrator;
import qualite_log.model.User;
import qualite_log.session.Authentification;
import qualite_log.session.SessionManager;
import qualite_log.view.WarningFrame;

public class ConnexionController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPanel;

    @FXML
    private Button button;

    @FXML
    private PasswordField passWordField;

    @FXML
    private TextField textFielMatricule;

    @FXML
    private VBox vBox;

    @FXML
    void initialize() {
        // Ajouter si besoin
    }

    /*
     * @FXML
     * public void handleLoginAction(ActionEvent event) {
     * try {
     * // Pour l'instant, cela passe directement à la vue suivante
     * FXMLLoader fxmlLoader = new
     * FXMLLoader(getClass().getResource("/qualite_log/MenuAdminFrame.fxml"));
     * Parent root = fxmlLoader.load();
     * vBox.getChildren().clear();
     * vBox.getChildren().add(root);
     * } catch (Exception e) {
     * e.printStackTrace();
     * // Gérer ici l'erreur
     * }
     * }
     */
    @FXML
    public void handleLoginAction(ActionEvent event) {
        try {
            String matricule = textFielMatricule.getText();
            String password = passWordField.getText();
            Administrator admin = Authentification.authenticateAdmin(matricule, password);
            User user = Authentification.authenticateUser(matricule, password);
            if (user != null) {
                 SessionManager.setCurrentUser(user);
                 // Chargez l interface User
                switchToMenuUserView();
             } else if (admin != null) {
                SessionManager.setCurrentAdmin(admin);
                // Chargez l interface Admin
                switchToMenuAdminView();
             }
        } catch (Exception e) {
            e.printStackTrace();
            WarningFrame warning = new WarningFrame("Erreur", "L'authentification a échoué. Veuillez réessayer.");
                    warning.show();        }
    }

    private void switchToMenuAdminView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/MenuAdminFrame.fxml"));
            Parent root = fxmlLoader.load();
            anchorPanel.getChildren().clear();
            anchorPanel.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void switchToMenuUserView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/MenuUserFrame.fxml"));
            Parent root = fxmlLoader.load();
            anchorPanel.getChildren().clear();
            anchorPanel.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

