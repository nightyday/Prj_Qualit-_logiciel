package qualite_log.tooltype;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import qualite_log.model.EquipmentType;


@ExtendWith(ApplicationExtension.class)
public class ToolTypeCreateControllerTest extends FxRobot {

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeCreateFrame.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /*** CAS PASSANT */
    @SuppressWarnings("unchecked")
    @Test
    void testSuccessfulToolTypeCreation() {
        clickOn("#typeTextField").write("Casque");
        clickOn("#createButton");

        TableView<EquipmentType> tableView = lookup("#tableView").queryAs(TableView.class);

        // Vérifier que le type de matériel est listé
        boolean equipmentTypeExists = tableView.getItems().stream()
            .anyMatch(equipmentType -> "Casque".equals(equipmentType.getType()));

        // Le type de matériel doit exister dans la liste
        assertTrue(equipmentTypeExists, "Le type de matériel devrait être listé dans le TableView.");
    }

    /*** TEST AVEC UN CHAMP INCORRECT */
    @Test
    void testIncorrectType() {
        clickOn("#typeTextField").write("Casque-Audio");
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }

    /*** TEST AVEC UN CHAMP VIDE */
    @Test
    void testEmptyType() {
        clickOn("#createButton");
        
        // Vérification de l'affichage de l'alerte
        Node dialogPane = lookup(".dialog-pane").query();
        from(dialogPane).lookup((Text t) -> t.getText().startsWith("Format de la saisie non conforme."));
        verifyThat(dialogPane, isVisible());
    }
}
