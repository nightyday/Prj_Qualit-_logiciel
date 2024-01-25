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
        //Ajouter si besoin 
    }

    @FXML
    public void handleLoginAction(ActionEvent event) {
        try {
            // Pour l'instant, cela passe directement à la vue suivante
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/MenuAdminFrame.fxml"));
            Parent root = fxmlLoader.load();
            vBox.getChildren().clear();
            vBox.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
            // Gérer ici l'erreur 
        }
    }
}
