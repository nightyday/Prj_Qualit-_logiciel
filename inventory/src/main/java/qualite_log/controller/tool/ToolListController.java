package qualite_log.controller.tool;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.Equipment;

public class ToolListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label listLabel;

    @FXML
    private TableColumn<Equipment, String> nomColumn;

    @FXML
    private TableColumn<Equipment, String> referenceColumn;

    @FXML
    private TableView<Equipment> tableView;

    @FXML
    private TableColumn<Equipment, String> typeColumn;

    @FXML
    private TableColumn<Equipment, String> versionColumn;

    @FXML
    void initialize() {
        try {
            ObservableList<Equipment> data = FXCollections.observableArrayList(Data.getInstance().getEquipments());
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            referenceColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
            versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.setItems(data);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } catch (Exception e) {
            showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
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

