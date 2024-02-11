package qualite_log.controller.tooltype;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import qualite_log.data_import.DataReader;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Data;
import qualite_log.model.EquipmentType;

public class ToolTypeCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createButton;

    @FXML
    private TextField typeTextField;

    @FXML
    void initialize() {
        DataReader.insert(Data.getInstance());
    }

    @FXML
    public void handleCreateAction(ActionEvent event) {
        if (validateInput(typeTextField.getText())) {
            try {
                Data.getInstance().addEquipmentTypes(new EquipmentType(typeTextField.getText()));
                
                DataWriter.extractEquipmentTypes(Data.getInstance()); // On met à jour les fichiers .json

                switchToToolTypeListView();
            } catch (Exception e) {
                showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
            }
        } else {
            showAlert("Erreur", "Format de la saisie non conforme.");
        }
    }

    private boolean validateInput(String input) {
        return Pattern.matches("^[a-zA-Z0-9]{1,30}$", input);
    }

    private void switchToToolTypeListView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeListFrame.fxml"));
            Parent root = fxmlLoader.load();

            // Obtention d'une référence au contrôleur de la vue ToolTypeListFrame
            ToolTypeListController controller = fxmlLoader.getController();
            
            // Appel de la méthode refreshData pour s'assurer que les données sont à jour
            controller.refreshData();

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

