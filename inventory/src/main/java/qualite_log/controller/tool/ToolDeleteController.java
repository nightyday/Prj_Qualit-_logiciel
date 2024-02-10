package qualite_log.controller.tool;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;

public class ToolDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button returnButton;

    @FXML
    private Label returnLabel;

    @FXML
    private ComboBox<String> referenceComboBox;

    private List<Equipment> equipments;

    @FXML
    void initialize() {
        equipments = Data.getInstance().getEquipments();
        List<String> referenceData = new ArrayList<>();
        for (Equipment equipment : equipments) {
            referenceData.add(equipment.getReference());
        }
        referenceComboBox.getItems().addAll(referenceData);

    }

    @FXML
    public void handleDeleteAction(ActionEvent event) {
        String selectedReference = referenceComboBox.getValue();
        // Vérifier si une référence a été sélectionnée dans la ComboBox
        if (selectedReference == null || selectedReference.trim().isEmpty()) {
            // Si aucune référence n'est sélectionnée, afficher un message d'erreur et retourner
            showAlert("Erreur", "Désolé, vous devez sélectionner une référence avant de supprimer.");
            return;
        }

        try {
            for (EquipmentType type : Data.getInstance().getEquipmentTypes()) {
                Equipment equipmentFound = type.getEquipments().stream()
                        .filter(e -> e.getReference().equals(selectedReference))
                        .findFirst()
                        .orElse(null);
                if (equipmentFound != null) {
                    type.getEquipments().remove(equipmentFound);
                    switchToToolListView(); // Actualiser la vue après la suppression
                    return; // Quitter la méthode après la suppression réussie
                }
            }
        } catch (Exception e) {
            showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
        }
    }

    private void switchToToolListView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolListFrame.fxml"));
            Parent root = fxmlLoader.load();
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
