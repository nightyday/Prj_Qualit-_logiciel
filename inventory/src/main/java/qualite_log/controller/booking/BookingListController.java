package qualite_log.controller.booking;

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
import qualite_log.model.Booking;
import qualite_log.model.Data;

public class BookingListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<Booking, String> startingDateColumn;

    @FXML
    private TableColumn<Booking, String> endingDateColumn;

    @FXML
    private Label listLabel;

    @FXML
    private TableColumn<Booking, String> mailColumn;

    @FXML
    private TableColumn<Booking, String> referenceColumn;

    @FXML
    private TableView<Booking> tableView;

    @FXML
    void initialize() {
        try {
            ObservableList<Booking> data = FXCollections.observableArrayList(Data.getInstance().getBookings());
            
            
            mailColumn.setCellValueFactory(cellData->cellData.getValue().getEmail());
            referenceColumn.setCellValueFactory(cellData->cellData.getValue().getReference());
            // If startingDate and endingDate are not simple properties, you will need to use a cell value factory like for email and reference
            startingDateColumn.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
            endingDateColumn.setCellValueFactory(new PropertyValueFactory<>("endingDate"));

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

