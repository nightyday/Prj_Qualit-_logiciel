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
import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.model.User;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(ApplicationExtension.class)
public class UserEmptyListControllerTest extends FxRobot {

    @Start
    public void start(Stage stage) throws Exception {
        configureData();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qualite_log/UserListFrame.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private static void configureData() {
        Data data = Data.getInstance();

        List<User> users = new ArrayList<>();
        data.setUsers(users);
        DataWriter.extractUsers(data);

        List<Administrator> admins = new ArrayList<>();
        data.setAdministrators(admins);
        DataWriter.extractAdministrators(data);
    }

    /*** CAS D'ERREUR */
    @SuppressWarnings("unchecked")
    @Test
    void testNoUserInDatabase() {
        TableView<Person> tableView = lookup("#tableView").queryAs(TableView.class);

        // Vérifier que le TableView ne contient aucune ligne de données
        assertTrue(tableView.getItems().isEmpty(), "Le TableView ne devrait pas contenir d'utilisateur.");
    }
}
