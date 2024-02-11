package qualite_log.booking;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.model.User;
import qualite_log.session.SessionManager;


@ExtendWith(ApplicationExtension.class)
public class BookingFilledListControllerTest extends FxRobot {

    @Start
    public void start(Stage stage) throws Exception {
        configureData();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qualite_log/BookingListFrame.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private static void configureData() {
        Data data = Data.getInstance();

        List<User> users = new ArrayList<>();
        users.add(new User("Martin", "Claire", "claire.martin@email.com"));
        users.add(new User("Dubois", "Julien", "julien.dubois@email.com"));
        users.add(new User("Garcia", "Ana", "ana.garcia@email.com"));
        users.add(new User("Johnson", "Brian", "brian.johnson@email.com"));
        data.setUsers(users);

        DataWriter.extractUsers(data);

        for(User user : users) {
            DataWriter.extractPassword(user, "password" + "u" + user.getId());
        }

        List<Administrator> admins = new ArrayList<>();
        admins.add(new Administrator("Admin", "admin", "admin.admin@email.com"));
        data.setAdministrators(admins);

        DataWriter.extractAdministrators(data);

        for(Administrator admin : admins) {
            DataWriter.extractPassword(admin, "password" + "a" + admin.getId());
        }

        List<EquipmentType> equipmentTypes = new ArrayList<>();
        equipmentTypes.add(new EquipmentType("Téléphone"));
        equipmentTypes.add(new EquipmentType("Ordinateur"));
        equipmentTypes.add(new EquipmentType("Tablette"));
        data.setEquipmentTypes(equipmentTypes);
        DataWriter.extractEquipmentTypes(data);

        List<Equipment> equipments = new ArrayList<>();
        equipments.add(new Equipment("AN001", "A32", "1.2", data.getEquipmentTypes().get(0)));
        equipments.add(new Equipment("AN002", "A33", "1.2", data.getEquipmentTypes().get(0)));
        equipments.add(new Equipment("AN003", "R17", "1.2", data.getEquipmentTypes().get(2)));
        equipments.add(new Equipment("AN004", "P45", "1.2", data.getEquipmentTypes().get(1)));
        DataWriter.extractEquipments(data);

        List<Booking> bookings = new ArrayList<>();
        bookings.add(new Booking(data.getUsers().get(3), data.getEquipments().get(1), LocalDate.of(2023, 12, 9),
                LocalDate.of(2024, 01, 9)));
        bookings.add(new Booking(data.getUsers().get(2), data.getEquipments().get(0), LocalDate.of(2023, 12, 9),
                LocalDate.of(2024, 01, 9)));
        bookings.add(new Booking(data.getAdministrators().get(0), data.getEquipments().get(2), LocalDate.of(2024, 2, 11),
                LocalDate.of(2024, 4, 11)));
        data.setBookings(bookings);
        DataWriter.extractBookings(data);

        SessionManager.setCurrentAdmin(data.getAdministrators().get(0));
    }

    /*** CAS PASSANT */
    @SuppressWarnings("unchecked")
    @Test
    void testNoBookingDisplayOnList() {
        TableView<Booking> tableView = lookup("#tableView").queryAs(TableView.class);

        // Vérifier que le TableView contient au moins une ligne de données
        assertFalse(tableView.getItems().isEmpty(), "Le TableView devrait contenir au moins un emprunt.");
    }
}
