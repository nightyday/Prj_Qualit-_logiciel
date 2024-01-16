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
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Person;

public class BookingListFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<?, ?> startingDateColumn;

    @FXML
    private TableColumn<?, ?> endingDateColumn;

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
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'BookingListFrame.fxml'.";
        assert startingDateColumn != null : "fx:id=\"bookingDateColumn\" was not injected: check your FXML file 'BookingListFrame.fxml'.";
        assert endingDateColumn != null : "fx:id=\"deleteDateColumn\" was not injected: check your FXML file 'BookingListFrame.fxml'.";
        assert listLabel != null : "fx:id=\"listLabel\" was not injected: check your FXML file 'BookingListFrame.fxml'.";
        assert mailColumn != null : "fx:id=\"mailColumn\" was not injected: check your FXML file 'BookingListFrame.fxml'.";
        assert referenceColumn != null : "fx:id=\"referenceColumn\" was not injected: check your FXML file 'BookingListFrame.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'BookingListFrame.fxml'.";

        ObservableList<Booking> data = FXCollections.observableArrayList(Data.getInstance().getBookings());
        mailColumn.setCellValueFactory(cellData->cellData.getValue().getEmail());
        referenceColumn.setCellValueFactory(cellData->cellData.getValue().getReference());
        startingDateColumn.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
        endingDateColumn.setCellValueFactory(new PropertyValueFactory<>("endingDate"));
        tableView.setItems(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

}
