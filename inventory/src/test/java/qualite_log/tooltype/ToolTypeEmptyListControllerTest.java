package qualite_log.tooltype;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
import qualite_log.model.Data;
import qualite_log.model.EquipmentType;


@ExtendWith(ApplicationExtension.class)
public class ToolTypeEmptyListControllerTest extends FxRobot {

    @Start
    public void start(Stage stage) throws Exception {
        configureData();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeListFrame.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private static void configureData() {
        Data data = Data.getInstance();

        List<EquipmentType> equipmentTypes = new ArrayList<>();
        data.setEquipmentTypes(equipmentTypes);
        DataWriter.extractEquipmentTypes(data);
    }

    /*** CAS D'ERREUR */
    @SuppressWarnings("unchecked")
    @Test
    void testNoToolTypeInDatabase() {
        TableView<EquipmentType> tableView = lookup("#tableView").queryAs(TableView.class);

        // Vérifier que le TableView ne contient aucune ligne de données
        assertTrue(tableView.getItems().isEmpty(), "Le TableView ne devrait pas contenir de type de matériel.");
    }
}
