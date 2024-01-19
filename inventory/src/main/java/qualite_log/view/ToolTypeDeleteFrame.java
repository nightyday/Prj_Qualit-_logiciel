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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.EquipmentType;

public class ToolTypeDeleteFrame {

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
    private ComboBox<String> typeComboBox;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolTypeDeleteFrame.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'ToolTypeDeleteFrame.fxml'.";
        assert deleteLabel != null : "fx:id=\"deleteLabel\" was not injected: check your FXML file 'ToolTypeDeleteFrame.fxml'.";
        assert typeComboBox != null : "fx:id=\"typeComboBox\" was not injected: check your FXML file 'ToolTypeDeleteFrame.fxml'.";

        // Add elements in the comboBoxs
        List<EquipmentType> equipmentTypes = Data.getInstance().getEquipmentTypes();
        List<String> typeData = new ArrayList<>();
        int i;
        for (i = 0; i < equipmentTypes.size(); i++) {
            typeData.add(equipmentTypes.get(i).getType());
        }
        typeComboBox.getItems().addAll(typeData);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    EquipmentType equipmentTypeSelected = equipmentTypes.get(typeData.indexOf(typeComboBox.getValue()));
                    Data.getInstance().getEquipmentTypes().remove(equipmentTypeSelected);
                    
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeListFrame.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        anchorPane.getChildren().clear();
                        anchorPane.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                catch (Exception e) {
                    System.out.println("Error");
                }
            }
        });
    }

}
