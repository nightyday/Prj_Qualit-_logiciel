package qualite_log.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


@ExtendWith(ApplicationExtension.class)
public class UserCreateControllerTest extends FxRobot {

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qualite_log/UserCreateFrame.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /*** CAS PASSANT */
    @Test
    void testSuccessfulUserCreation() {
        clickOn("#nomTextField").write("Barré");
        clickOn("#prenomTextField").write("François");
        clickOn("#mailTextField").write("test@example.com");
        clickOn("#roleComboBox");
        type(KeyCode.DOWN); // Déplace le curseur au premier choix
        type(KeyCode.DOWN); // Déplace au deuxième choix
        type(KeyCode.ENTER); // Sélectionne le choix
        
        clickOn("#createButton");
        
        verifyThat("#tableView", isVisible()); // Vérifier que la liste des utilisateurs est visible
    }

    /*** TESTS AVEC UN CHAMP INCORRECT */
    @Test
    void testIncorrectLastName() {
        clickOn("#nomTextField").write("Barré000");
        clickOn("#prenomTextField").write("François");
        clickOn("#mailTextField").write("test@example.com");
        clickOn("#roleComboBox");
        type(KeyCode.DOWN); // Déplace le curseur au premier choix
        type(KeyCode.DOWN); // Déplace au deuxième choix
        type(KeyCode.ENTER); // Sélectionne le choix
        
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testIncorrectFirstName() {
        clickOn("#nomTextField").write("Barré");
        clickOn("#prenomTextField").write("François000");
        clickOn("#mailTextField").write("test@example.com");
        clickOn("#roleComboBox");
        type(KeyCode.DOWN); // Déplace le curseur au premier choix
        type(KeyCode.DOWN); // Déplace au deuxième choix
        type(KeyCode.ENTER); // Sélectionne le choix
        
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testIncorrectMail() {
        clickOn("#nomTextField").write("Barré");
        clickOn("#prenomTextField").write("François");
        clickOn("#mailTextField").write("test@examplecom");
        clickOn("#roleComboBox");
        type(KeyCode.DOWN); // Déplace le curseur au premier choix
        type(KeyCode.DOWN); // Déplace au deuxième choix
        type(KeyCode.ENTER); // Sélectionne le choix
        
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testNoRoleSelection() {
        clickOn("#nomTextField").write("Barré");
        clickOn("#prenomTextField").write("François");
        clickOn("#mailTextField").write("test@example.com");
        
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    /*** TESTS AVEC UN CHAMP VIDE */
    @Test
    void testEmptyLastName() {
        clickOn("#prenomTextField").write("François");
        clickOn("#mailTextField").write("test@example.com");
        clickOn("#roleComboBox");
        type(KeyCode.DOWN); // Déplace le curseur au premier choix
        type(KeyCode.DOWN); // Déplace au deuxième choix
        type(KeyCode.ENTER); // Sélectionne le choix
        
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testEmptyFirstName() {
        clickOn("#nomTextField").write("Barré");
        clickOn("#mailTextField").write("test@example.com");
        clickOn("#roleComboBox");
        type(KeyCode.DOWN); // Déplace le curseur au premier choix
        type(KeyCode.DOWN); // Déplace au deuxième choix
        type(KeyCode.ENTER); // Sélectionne le choix
        
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    @Test
    void testEmptyMail() {
        clickOn("#nomTextField").write("Barré");
        clickOn("#prenomTextField").write("François");
        clickOn("#mailTextField").write("test@examplecom");
        clickOn("#roleComboBox");
        type(KeyCode.DOWN); // Déplace le curseur au premier choix
        type(KeyCode.DOWN); // Déplace au deuxième choix
        type(KeyCode.ENTER); // Sélectionne le choix
        
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }
}