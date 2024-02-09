package qualite_log.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.Parent;
import javafx.stage.Stage;
import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.List;

import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;

import static org.junit.jupiter.api.Assertions.assertFalse;


@ExtendWith(ApplicationExtension.class)
public class UserDeleteControllerTest extends FxRobot {

    @Start
    public void start(Stage stage) throws Exception {
        configureData();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qualite_log/UserDeleteFrame.fxml"));
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
        users.add(new User("Barré", "François", "test@example.com"));
        data.setUsers(users);
        DataWriter.extractUsers(data);

        List<Administrator> admins = new ArrayList<>();
        admins.add(new Administrator("Admin", "admin", "admin.admin@email.com"));
        data.setAdministrators(admins);
        DataWriter.extractAdministrators(data);
    }

    /*** CAS PASSANT */
    @SuppressWarnings("unchecked")
    @Test
    void testSuccessfulUserDeletion() {
        clickOn("#mailComboBox");
        type(KeyCode.DOWN, 4); // Déplace au choix de l'utilisateur à supprimer
        type(KeyCode.ENTER); // Sélectionne le choix
        clickOn("#deleteButton");

        TableView<Person> tableView = lookup("#tableView").queryAs(TableView.class);

        // Vérifier que l'email de l'utilisateur supprimé n'est plus listé
        boolean emailExists = tableView.getItems().stream()
            .anyMatch(person -> "test@example.com".equals(person.getEmail()));

        // L'email ne doit pas exister dans la liste
        assertFalse(emailExists, "L'utilisateur ne devrait plus être listé dans le TableView.");
    }
}
