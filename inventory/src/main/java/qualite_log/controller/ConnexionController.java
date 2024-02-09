package qualite_log.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import qualite_log.model.Administrator;
import qualite_log.model.User;
import qualite_log.session.Authentification;
import qualite_log.session.SessionManager;

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

   
    @FXML
    public void handleLoginAction(ActionEvent event) {
        String matricule = textFielMatricule.getText();
        String password = passWordField.getText();
        
        Administrator admin = Authentification.authenticateAdmin(matricule, password);
        if (admin != null) {
            SessionManager.setCurrentAdmin(admin);
            switchToMenuAdminView();
            return; // Arrête l'exécution si un admin est authentifié
        }
        
        User user = Authentification.authenticateUser(matricule, password);
        if (user != null) {
            SessionManager.setCurrentUser(user);
            switchToMenuUserView();
            return; // Arrête l'exécution si un utilisateur est authentifié
        }
        
        // Si aucune des conditions ci-dessus n'est vraie, afficher une erreur
        showAlert("Erreur", "L'authentification a échoué. Veuillez vérifier vos identifiants et réessayer.");
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

