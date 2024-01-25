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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;

public class ToolCreateFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createButton;

    @FXML
    private Label createLabel;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField referenceTextField;

    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    private TextField versionTextField;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert createLabel != null : "fx:id=\"createLabel\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert nomTextField != null : "fx:id=\"nomTextField\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert referenceTextField != null : "fx:id=\"referenceTextField\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert typeComboBox != null : "fx:id=\"typeComboBox\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert versionTextField != null : "fx:id=\"versionTextField\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";

        // Add elements in the comboBoxs
        List<EquipmentType> equipmentsType = Data.getInstance().getEquipmentTypes();
        List<String> typeData = new ArrayList<>();
        for (int i = 0; i < equipmentsType.size(); i++) {
            typeData.add(equipmentsType.get(i).getType());
        }
        typeComboBox.getItems().addAll(typeData);

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (Pattern.matches("^(AN|AP|XX)[A-Z0-9]\\d{3}$", referenceTextField.getText()) && Pattern.matches("^[a-zA-Z0-9]{1,30}$", nomTextField.getText()) && Pattern.matches("^[a-zA-Z0-9]{1,15}$", versionTextField.getText())) {
                    try {
                        EquipmentType equipmentTypeSelected = equipmentsType.get(typeData.indexOf(typeComboBox.getValue()));
                        Data.getInstance().getEquipments().add(new Equipment(referenceTextField.getText(), nomTextField.getText(), versionTextField.getText(), equipmentTypeSelected));
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
                
            else {
                try {
                    Alert alert = new Alert(AlertType.WARNING);

                    alert.setTitle("Erreur");
                    alert.setHeaderText(null);
                    alert.setContentText("Format de la saisie non conforme.");
                    alert.showAndWait();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
            }
            }
        });
    }

}
