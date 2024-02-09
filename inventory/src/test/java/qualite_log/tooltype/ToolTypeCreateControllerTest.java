package qualite_log.tooltype;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import qualite_log.model.Person;

public class ToolTypeCreateControllerTest extends FxRobot {
@Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qualite_log/UserCreateFrame.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /*** CAS PASSANT */
    @SuppressWarnings("unchecked")
    @Test
    void testSuccessfulUserCreation() {
        clickOn("#nomTextField").write("Barré");
        clickOn("#prenomTextField").write("François");
        clickOn("#mailTextField").write("test@example.com");
        clickOn("#roleComboBox");
        type(KeyCode.DOWN, 2); // Déplace au deuxième choix
        type(KeyCode.ENTER); // Sélectionne le choix
        clickOn("#createButton");

        TableView<Person> tableView = lookup("#tableView").queryAs(TableView.class);

        // Vérifier que l'email de l'utilisateur est listé
        boolean emailExists = tableView.getItems().stream()
            .anyMatch(person -> "test@example.com".equals(person.getEmail()));

        // L'email doit exister dans la liste
        assertTrue(emailExists, "L'utilisateur devrait être listé dans le TableView.");
    }

    /*** TESTS AVEC UN CHAMP INCORRECT */
    @Test
    void testIncorrectLastName() {
        clickOn("#nomTextField").write("Barré000");
        clickOn("#prenomTextField").write("François");
        clickOn("#mailTextField").write("test@example.com");
        clickOn("#roleComboBox");
        type(KeyCode.DOWN, 2); // Déplace au deuxième choix
        type(KeyCode.ENTER); // Sélectionne le choix
        
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }
}
