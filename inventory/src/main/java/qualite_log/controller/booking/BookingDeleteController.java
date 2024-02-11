package qualite_log.controller.booking;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.layout.AnchorPane;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Booking;
import qualite_log.model.Data;

public class BookingDeleteController {

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

    private List<Booking> bookings;

    @FXML
    void initialize() {
        Data.updateData();
        
        bookings = Data.getInstance().getBookings();
        List<String> referenceData = bookings.stream()
                                             .map(booking -> booking.getEquipment().getReference())
                                             .collect(Collectors.toList());
        referenceComboBox.getItems().addAll(referenceData);

        deleteButton.setOnAction(this::handleDeleteAction);
    }

    @FXML
    public void handleDeleteAction(ActionEvent event) {
        try {
            String selectedReference = referenceComboBox.getValue();
            if (selectedReference != null) {
                Booking bookingSelected = null;

                for (Booking booking : Data.getInstance().getBookings()) {
                    if(booking.getEquipment().getReference().equals(selectedReference)) {
                        bookingSelected = booking;
                    }
                }

                if (bookingSelected != null) {
                    List<Booking> bookings = Data.getInstance().getBookings();
                    bookings.remove(bookingSelected);
                    Data.getInstance().setBookings(bookings);                    
                    DataWriter.extractBookings(Data.getInstance()); // On met à jour les fichiers .json
                    switchToBookingListView();
                }
            }
            else {
                showAlert("Erreur", "Veuillez sélectionner un matériel à rendre.");
            }
        } catch (Exception e) {
            showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
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

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

