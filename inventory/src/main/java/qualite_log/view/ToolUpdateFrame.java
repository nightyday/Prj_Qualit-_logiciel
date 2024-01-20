package qualite_log.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;

public class ToolUpdateFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField nomTextField;

    @FXML
    private ComboBox<String> referenceComboBox;

    @FXML
    private TextField referenceTextField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private Button updateButton;

    @FXML
    private Label updateLabel;

    @FXML
    private TextField versionTextField;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert nomTextField != null : "fx:id=\"nomTextField\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert referenceComboBox != null : "fx:id=\"referenceComboBox\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert referenceTextField != null : "fx:id=\"referenceTextField\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert typeComboBox != null : "fx:id=\"typeComboBox\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert updateLabel != null : "fx:id=\"updateLabel\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert versionTextField != null : "fx:id=\"versionTextField\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";

        // Add elements in the comboBoxs
        List<Equipment> equipments = Data.getInstance().getEquipments();
        List<String> referenceData = new ArrayList<>();
        int i;
        for (i = 0; i < equipments.size(); i++) {
            referenceData.add(equipments.get(i).getReference());
        }
        referenceComboBox.getItems().addAll(referenceData);

        List<EquipmentType> equipmentsType = Data.getInstance().getEquipmentTypes();
        List<String> typeData = new ArrayList<>();
        for (i = 0; i < equipmentsType.size(); i++) {
            typeData.add(equipmentsType.get(i).getType());
        }
        typeComboBox.getItems().addAll(typeData);

        // Fill informations when the user is choose
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Equipment equipmentSelected = equipments.get(referenceData.indexOf(referenceComboBox.getValue()));
                referenceTextField.setText(equipmentSelected.getReference());
                nomTextField.setText(equipmentSelected.getName());
                versionTextField.setText(equipmentSelected.getVersion());
                typeComboBox.setValue(equipmentSelected.getType().getType());
            }
        };
        referenceComboBox.setOnAction(event);

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    // Patterns
                    if (Pattern.matches("^(AN|AP|XX)[A-Z0-9]\\d{3}$", referenceTextField.getText()) && Pattern.matches("^[a-zA-Z0-9]{1,30}$", nomTextField.getText()) && Pattern.matches("^[a-zA-Z0-9]{1,15}$", versionTextField.getText())) {
                        Equipment equipmentSelected = equipments.get(referenceData.indexOf(referenceComboBox.getValue()));
                        equipmentSelected.getType().getEquipments().remove(equipmentSelected);
                        Data.getInstance().getEquipments().add(new Equipment(referenceTextField.getText(), nomTextField.getText(), versionTextField.getText(), equipmentSelected.getType()));
                
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
                    else {
                        System.out.println("Error pattern");
                    }
                }
                catch (Exception e) {
                    System.out.println("Error");
                }
            }
        });
    }

}
