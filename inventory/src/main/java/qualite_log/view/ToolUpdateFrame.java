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
import javafx.scene.shape.Rectangle;
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
    private Rectangle warningRectangle;

    @FXML
    private Label warningLabel;

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

        List<Equipment> equipments = Data.getInstance().getEquipments();
        List<String> referenceData = new ArrayList<>();
        fillInformations(equipments, referenceData);
        buttonAction(equipments, referenceData);
    }

    public void fillInformations(List<Equipment> equipments, List<String> referenceData) {
        // Add elements in the comboBoxs
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
    }

    public void buttonAction(List<Equipment> equipments, List<String> referenceData) {
        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                // Patterns
                if (!Pattern.matches("^(AN|AP|XX)[A-Z0-9]\\d{3}$", referenceTextField.getText())) {
                    warningRectangle.setX(referenceTextField.getLayoutX() - 1);
                    warningRectangle.setY(referenceTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(referenceTextField.getWidth() + 2);
                    warningRectangle.setHeight(referenceTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);

                    warningLabel.setLayoutX(referenceTextField.getLayoutX());
                    warningLabel.setLayoutY(referenceTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else if (!Pattern.matches("^[a-zA-Z0-9\\-]{1,30}$", nomTextField.getText())) {
                    warningRectangle.setX(nomTextField.getLayoutX() - 1);
                    warningRectangle.setY(nomTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(nomTextField.getWidth() + 2);
                    warningRectangle.setHeight(nomTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);
                    
                    warningLabel.setLayoutX(nomTextField.getLayoutX());
                    warningLabel.setLayoutY(nomTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else if (!Pattern.matches("^[a-zA-Z0-9]{1,15}$", versionTextField.getText())) {
                    warningRectangle.setX(versionTextField.getLayoutX() - 1);
                    warningRectangle.setY(versionTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(versionTextField.getWidth() + 2);
                    warningRectangle.setHeight(versionTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);
                    
                    warningLabel.setLayoutX(versionTextField.getLayoutX());
                    warningLabel.setLayoutY(versionTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else {
                    try {
                        Equipment equipmentSelected = equipments.get(referenceData.indexOf(referenceComboBox.getValue()));
                        equipmentSelected.getType().getEquipments().remove(equipmentSelected);
                        Data.getInstance().getEquipments().add(new Equipment(referenceTextField.getText(), nomTextField.getText(), versionTextField.getText(), equipmentSelected.getType()));
                    
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolListFrame.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        anchorPane.getChildren().clear();
                        anchorPane.getChildren().add(root);
                    }
                    catch (Exception e) {
                        WarningFrame warning = new WarningFrame("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
                        warning.show();
                    }
                }
            }
        });
    }
}
