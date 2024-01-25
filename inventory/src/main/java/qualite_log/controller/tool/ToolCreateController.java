package qualite_log.controller.tool;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javafx.event.ActionEvent;
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


public class ToolCreateController {

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

    private List<EquipmentType> equipmentTypes;

    @FXML
    void initialize() {
        equipmentTypes = Data.getInstance().getEquipmentTypes();
        List<String> typeData = equipmentTypes.stream()
                                              .map(EquipmentType::getType)
                                              .collect(Collectors.toList());
        typeComboBox.getItems().addAll(typeData);

        createButton.setOnAction(this::handleCreateAction);
    }

    @FXML
    public void handleCreateAction(ActionEvent event) {
        if (validateInput()) {
            try {
                EquipmentType equipmentTypeSelected = equipmentTypes.get(typeComboBox.getItems().indexOf(typeComboBox.getValue()));
                Equipment newEquipment = new Equipment(referenceTextField.getText(), nomTextField.getText(), versionTextField.getText(), equipmentTypeSelected);
                Data.getInstance().getEquipments().add(newEquipment);
                switchToToolListView();
            } catch (Exception e) {
                showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
            }
        } else {
            showAlert("Erreur", "Format de la saisie non conforme.");
        }
    }

    private boolean validateInput() {
        return Pattern.matches("^(AN|AP|XX)[A-Z0-9]\\d{3}$", referenceTextField.getText()) &&
               Pattern.matches("^[a-zA-Z0-9]{1,30}$", nomTextField.getText()) &&
               Pattern.matches("^[a-zA-Z0-9]{1,15}$", versionTextField.getText());
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
