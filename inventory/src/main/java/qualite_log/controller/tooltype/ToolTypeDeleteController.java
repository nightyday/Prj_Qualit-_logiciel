package qualite_log.controller.tooltype;

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
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.EquipmentType;
public class ToolTypeDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button deleteButton;

    @FXML
    private ComboBox<String> typeComboBox;

    private List<EquipmentType> equipmentTypes;

    @FXML
    void initialize() {
        equipmentTypes = Data.getInstance().getEquipmentTypes();
        List<String> typeData = new ArrayList<>();
        for (EquipmentType type : equipmentTypes) {
            typeData.add(type.getType());
        }
        typeComboBox.getItems().addAll(typeData);

    }

    @FXML
    public void handleDeleteAction(ActionEvent event) {
        try {
            EquipmentType equipmentTypeSelected = equipmentTypes.get(typeComboBox.getItems().indexOf(typeComboBox.getValue()));
            Data.getInstance().getEquipmentTypes().remove(equipmentTypeSelected);
            switchToToolTypeListView();
        } catch (Exception e) {
            showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
        }
    }

    private void switchToToolTypeListView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeListFrame.fxml"));
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

