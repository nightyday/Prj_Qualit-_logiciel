package qualite_log.controller.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
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
import qualite_log.model.Person;

public class BookingCreateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createButton;

    @FXML
    private Label createLabel;

    @FXML
    private ComboBox<String> referenceComboBox;

    private List<Equipment> equipments;
    private List<String> referenceData;

    @FXML
    public void initialize() {
        equipments = Data.getInstance().getEquipments();
        referenceData = new ArrayList<>();
        for (Equipment equipment : equipments) {
            referenceData.add(equipment.getReference());
        }
        referenceComboBox.getItems().addAll(referenceData);
    }

    @FXML
    public void handleCreateAction(ActionEvent event) {
        if (referenceComboBox.getValue() != null) {
            Equipment equipmentSelected = equipments.get(referenceData.indexOf(referenceComboBox.getValue()));
            Person person = new Person(Data.getInstance().getUsers().get(0).getLastName(), 
                                       Data.getInstance().getUsers().get(0).getFirstName(), 
                                       "user", 
                                       Data.getInstance().getUsers().get(0).getEmail());
            Data.getInstance().getBookings().add(new Booking(person, equipmentSelected));
            switchToBookingListView();
        } else {
            System.out.println("Error");
        }
    }

    private void switchToBookingListView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/BookingListFrame.fxml"));
            Parent root = fxmlLoader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
