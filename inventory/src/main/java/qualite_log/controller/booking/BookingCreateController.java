package qualite_log.controller.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import qualite_log.model.Equipment;
import qualite_log.model.Person;
import qualite_log.session.SessionManager;

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
        Data.updateData();
        
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

            // Récupère l'utilisateur ou l'administrateur actuellement connecté
            Person person = SessionManager.getCurrentUser();
            if (person == null) {
                person = SessionManager.getCurrentAdmin();
            }

            if (person != null) {
                List<Booking> currentBookings = Data.getInstance().getBookings();
                currentBookings.add(new Booking(person, equipmentSelected));
                Data.getInstance().setBookings(currentBookings); // Mise à jour de la liste dans Data

                DataWriter.extractBookings(Data.getInstance()); // On met à jour les fichiers .json
                
                switchToBookingListView();
            } else {
                showAlert("Erreur", "Aucune session active trouvée.");
            }
        } else {
            showAlert("Erreur", "Aucun équipement sélectionné.");
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
