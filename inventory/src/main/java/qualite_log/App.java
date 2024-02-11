package qualite_log;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import qualite_log.data_import.DataReader;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;
import qualite_log.model.Booking;
import qualite_log.model.Data;
import qualite_log.model.Equipment;
import qualite_log.model.EquipmentType;
import qualite_log.model.User;

/**
 * JavaFX App
 */
public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ConnexionFrame.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Scene scene = new Scene(root);

            stage.setTitle("Inventory");
            stage.setScene(scene);
            stage.show();
            
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (needsInitialization()) {
            configureData(); // Initialise des données si nécessaire
        }
        Data data = Data.getInstance(); // Initialise la première instance de Data
    
        launch(args);
    
        DataWriter.extract(data);
    }
    
    private static boolean needsInitialization() {
    File file = new File(DataReader.getPath("administrators.json"));
    if (!file.exists() || file.length() == 0) {
        return true;
    }
    // Ajout ici d'autres vérifications si nécessaire
    return false;
}


    /*
     * Méthode permettant l'initialisation de données dans Data et dans les fichiers .json (à utiliser si ces derniers sont vides)
     */
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
        data.setBookings(bookings);
        DataWriter.extractBookings(data);
    }
}