package qualite_log.user;

import org.testfx.util.WaitForAsyncUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.model.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


@ExtendWith(ApplicationExtension.class)
public class UserUpdateControllerTest extends FxRobot {

    @Start
    public void start(Stage stage) throws Exception {
        configureData();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qualite_log/UserUpdateFrame.fxml"));
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
    void testSuccessfulUserUpdate() {
        clickOn("#mailComboBox").clickOn("test@example.com");

        clickOn("#mailTextField");
        push(KeyCode.CONTROL, KeyCode.A); // Pour sélectionner tout le texte à effacer
        eraseText(1);
        write("othertest@otherexample.com");

        doubleClickOn("#nomTextField").eraseText(1).write("Mbappé");
        doubleClickOn("#prenomTextField").eraseText(1).write("Kylian");
        clickOn("#roleComboBox").clickOn("administrateur");
        clickOn("#updateButton");

        WaitForAsyncUtils.waitForAsyncFx(500, () -> {}); // Attendre que la fenêtre s'affiche

        // On s'assure maintenant que le TableView contient les informations mises à jour
        TableView<Person> tableView = lookup("#tableView").queryAs(TableView.class);
        boolean userUpdated = tableView.getItems().stream()
            .anyMatch(person -> 
                "othertest@otherexample.com".equals(person.getEmail()) &&
                "Mbappé".equals(person.getLastName()) &&
                "Kylian".equals(person.getFirstName())
            );

        assertTrue(userUpdated, "Les informations de l'utilisateur devraient être mises à jour dans le TableView.");
    }

    /*** TESTS AVEC UN CHAMP INCORRECT */
    @Test
    void testIncorrectLastName() {
        clickOn("#mailComboBox").clickOn("test@example.com");

        clickOn("#mailTextField");
        push(KeyCode.CONTROL, KeyCode.A); // Pour sélectionner tout le texte à effacer
        eraseText(1);
        write("othertest@otherexample.com");

        doubleClickOn("#nomTextField").eraseText(1).write("Mbappé000");
        doubleClickOn("#prenomTextField").eraseText(1).write("Kylian");
        clickOn("#roleComboBox").clickOn("administrateur");
        clickOn("#updateButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testIncorrectFirstName() {
        clickOn("#mailComboBox").clickOn("test@example.com");

        clickOn("#mailTextField");
        push(KeyCode.CONTROL, KeyCode.A); // Pour sélectionner tout le texte à effacer
        eraseText(1);
        write("othertest@otherexample.com");

        doubleClickOn("#nomTextField").eraseText(1).write("Mbappé");
        doubleClickOn("#prenomTextField").eraseText(1).write("Kylian000");
        clickOn("#roleComboBox").clickOn("administrateur");
        clickOn("#updateButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testIncorrectMail() {
        clickOn("#mailComboBox").clickOn("test@example.com");

        clickOn("#mailTextField");
        push(KeyCode.CONTROL, KeyCode.A); // Pour sélectionner tout le texte à effacer
        eraseText(1);
        write("othertest@otherexamplecom");

        doubleClickOn("#nomTextField").eraseText(1).write("Mbappé");
        doubleClickOn("#prenomTextField").eraseText(1).write("Kylian");
        clickOn("#roleComboBox").clickOn("administrateur");
        clickOn("#updateButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    /*** TESTS AVEC UN CHAMP VIDE */
    @Test
    void testEmptyLastName() {
        clickOn("#mailComboBox").clickOn("test@example.com");

        clickOn("#mailTextField");
        push(KeyCode.CONTROL, KeyCode.A); // Pour sélectionner tout le texte à effacer
        eraseText(1);
        write("othertest@otherexample.com");

        doubleClickOn("#nomTextField").eraseText(1);
        doubleClickOn("#prenomTextField").eraseText(1).write("Kylian");
        clickOn("#roleComboBox").clickOn("administrateur");
        clickOn("#updateButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testEmptyFirstName() {
        clickOn("#mailComboBox").clickOn("test@example.com");

        clickOn("#mailTextField");
        push(KeyCode.CONTROL, KeyCode.A); // Pour sélectionner tout le texte à effacer
        eraseText(1);
        write("othertest@otherexample.com");

        doubleClickOn("#nomTextField").eraseText(1).write("Mbappé");
        doubleClickOn("#prenomTextField").eraseText(1);
        clickOn("#roleComboBox").clickOn("administrateur");
        clickOn("#updateButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testEmptyMail() {
        clickOn("#mailComboBox").clickOn("test@example.com");

        clickOn("#mailTextField");
        push(KeyCode.CONTROL, KeyCode.A); // Pour sélectionner tout le texte à effacer
        eraseText(1);

        doubleClickOn("#nomTextField").eraseText(1).write("Mbappé");
        doubleClickOn("#prenomTextField").eraseText(1).write("Kylian");
        clickOn("#roleComboBox").clickOn("administrateur");
        clickOn("#updateButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testNoMailSelection() {
        clickOn("#updateButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    // TODO: Ajouter des tests pour le cas où le compte sélectionné est utilisé actuellement
}
