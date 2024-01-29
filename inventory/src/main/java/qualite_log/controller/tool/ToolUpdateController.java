package qualite_log.controller.tool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.util.FxUtil;
import qualite_log.util.ValidationConstants;

public class ToolUpdateController {

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
    private TextField versionTextField;

    private List<Equipment> equipments;
    private List<EquipmentType> equipmentTypes;

    @FXML
    void initialize() {
        equipments = Data.getInstance().getEquipments();
        equipmentTypes = Data.getInstance().getEquipmentTypes();
        FxUtil.addTextChangeListener(referenceTextField, ValidationConstants.REF_REGEX);
        FxUtil.addTextChangeListener(nomTextField, ValidationConstants.NAME_REGEX);
        FxUtil.addTextChangeListener(versionTextField, ValidationConstants.VERSION_REGEX);
        fillComboBoxes();
        
    }

    private void fillComboBoxes() {
        List<String> referenceData = new ArrayList<>();
        equipments.forEach(equipment -> referenceData.add(equipment.getReference()));
        referenceComboBox.getItems().addAll(referenceData);

        List<String> typeData = new ArrayList<>();
        equipmentTypes.forEach(type -> typeData.add(type.getType()));
        typeComboBox.getItems().addAll(typeData);
    }

    @FXML
    public void handleReferenceSelection(ActionEvent event) {
        String selectedReference = referenceComboBox.getValue();
        Equipment equipmentSelected = equipments.stream()
                                                .filter(e -> e.getReference().equals(selectedReference))
                                                .findFirst()
                                                .orElse(null);
        if (equipmentSelected != null) {
            referenceTextField.setText(equipmentSelected.getReference());
            nomTextField.setText(equipmentSelected.getName());
            versionTextField.setText(equipmentSelected.getVersion());
            typeComboBox.setValue(equipmentSelected.getType().getType());
        }
    }

    @FXML
    public void handleUpdateAction(ActionEvent event) {
        if (validateInput()) {
            try {
                String selectedReference = referenceComboBox.getValue();
                Equipment equipmentSelected = equipments.stream()
                                                        .filter(e -> e.getReference().equals(selectedReference))
                                                        .findFirst()
                                                        .orElse(null);
                if (equipmentSelected != null) {
                    updateEquipment(equipmentSelected);
                    switchToToolListView();
                }
            } catch (Exception e) {
                showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
            }
        }
    }

    private boolean validateInput() {
        List<Pair<TextField, String>> fieldRegexPairs = Arrays.asList(
                new Pair<>(referenceTextField, ValidationConstants.REF_REGEX),
                new Pair<>(nomTextField, ValidationConstants.NAME_REGEX),
                new Pair<>(versionTextField, ValidationConstants.VERSION_REGEX));
        return FxUtil.validateInputs(fieldRegexPairs);
    }

    private void updateEquipment(Equipment equipment) {
        // Update the equipment details
        equipment.setReference(referenceTextField.getText());
        equipment.setName(nomTextField.getText());
        equipment.setVersion(versionTextField.getText());
        // Find and set the new type
        EquipmentType newType = equipmentTypes.stream()
                                              .filter(type -> type.getType().equals(typeComboBox.getValue()))
                                              .findFirst()
                                              .orElse(null);
        if (newType != null) {
            equipment.setType(newType);
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

