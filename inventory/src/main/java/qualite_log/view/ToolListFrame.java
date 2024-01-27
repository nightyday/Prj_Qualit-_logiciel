package qualite_log.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.Equipment;

public class ToolListFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label listLabel;

    @FXML
    private TableColumn<?, ?> nomColumn;

    @FXML
    private TableColumn<?, ?> referenceColumn;

    @FXML
    private TableView<Equipment> tableView;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> versionColumn;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolListFrame.fxml'.";
        assert listLabel != null : "fx:id=\"listLabel\" was not injected: check your FXML file 'ToolListFrame.fxml'.";
        assert nomColumn != null : "fx:id=\"nomColumn\" was not injected: check your FXML file 'ToolListFrame.fxml'.";
        assert referenceColumn != null : "fx:id=\"referenceColumn\" was not injected: check your FXML file 'ToolListFrame.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'ToolListFrame.fxml'.";
        assert typeColumn != null : "fx:id=\"typeColumn\" was not injected: check your FXML file 'ToolListFrame.fxml'.";
        assert versionColumn != null : "fx:id=\"versionColumn\" was not injected: check your FXML file 'ToolListFrame.fxml'.";

        try {
            ObservableList<Equipment> data = FXCollections.observableArrayList(Data.getInstance().getEquipments());
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            referenceColumn.setCellValueFactory(new PropertyValueFactory<>("reference"));
            versionColumn.setCellValueFactory(new PropertyValueFactory<>("version"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.setItems(data);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
        catch (Exception e) {
            WarningFrame warning = new WarningFrame("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
            warning.show();
        }
    }

}
