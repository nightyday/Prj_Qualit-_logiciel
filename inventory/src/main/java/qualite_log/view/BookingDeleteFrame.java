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
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;

public class BookingDeleteFrame {

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
    private ComboBox<String> referenceComboBox;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'BookingDeleteFrame.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'BookingDeleteFrame.fxml'.";
        assert deleteLabel != null : "fx:id=\"deleteLabel\" was not injected: check your FXML file 'BookingDeleteFrame.fxml'.";
        assert referenceComboBox != null : "fx:id=\"referenceComboBox\" was not injected: check your FXML file 'BookingDeleteFrame.fxml'.";
    
        // Add elements in the comboBoxs
        List<Equipment> equipment = Data.getInstance().getEquipments();
        List<String> referenceData = new ArrayList<>();
        int i;
        for (i = 0; i < equipment.size(); i++) {
            referenceData.add(equipment.get(i).getReference());
        }
        referenceComboBox.getItems().addAll(referenceData);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                int index = -1;
                List<Booking> bookings = Data.getInstance().getBookings();
                for (int i = 0; i < bookings.size(); i++) {
                    if (bookings.get(i).getEquipment().getReference() == referenceComboBox.getValue()) {
                        index = i;
                        i = bookings.size();
                    }
                }

                if (index >= 0) {
                    Data.getInstance().getBookings().remove(index);
                    
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/BookingListFrame.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        anchorPane.getChildren().clear();
                        anchorPane.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Error");
                }
            }
        });
    }

}

