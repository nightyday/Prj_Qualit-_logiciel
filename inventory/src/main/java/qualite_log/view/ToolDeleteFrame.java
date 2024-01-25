package qualite_log.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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

public class ToolDeleteFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button deleteButton;

    @FXML
    private Label deleteLabel;

    @FXML
    private ComboBox<String> referenceComboBox;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolDeleteFrame.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'ToolDeleteFrame.fxml'.";
        assert deleteLabel != null : "fx:id=\"deleteLabel\" was not injected: check your FXML file 'ToolDeleteFrame.fxml'.";
        assert referenceComboBox != null : "fx:id=\"referenceComboBox\" was not injected: check your FXML file 'ToolDeleteFrame.fxml'.";

        // Add elements in the comboBoxs
        List<Equipment> equipments = Data.getInstance().getEquipments();
        List<String> referenceData = new ArrayList<>();
        int i;
        for (i = 0; i < equipments.size(); i++) {
            referenceData.add(equipments.get(i).getReference());
        }
        referenceComboBox.getItems().addAll(referenceData);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    Equipment equipmentSelected = equipments.get(referenceData.indexOf(referenceComboBox.getValue()));
                    equipmentSelected.getType().getEquipments().remove(equipmentSelected);
                }
                catch (Exception e) {
                    try {
                        Alert alert = new Alert(AlertType.WARNING);
        
                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
                        alert.showAndWait();
                    }
                    catch (Exception error) {
                        error.printStackTrace();
                    }
                }
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolListFrame.fxml"));
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
