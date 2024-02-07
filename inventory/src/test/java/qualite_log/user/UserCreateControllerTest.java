package qualite_log.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import qualite_log.controller.user.UserCreateController;

import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.api.FxRobot;

@ExtendWith(ApplicationExtension.class)
public class UserCreateControllerTest {

    UserCreateController controller;
    FxRobot robot = new FxRobot();

    @Start
    public void start(Stage stage) throws Exception {
         // Charger le FXML de votre interface utilisateur
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/qualite_log/UserCreateFrame.fxml"));
        Parent root = loader.load();

        // Récupérer le contrôleur associé au FXML
        controller = loader.getController();

        // Créer une scène et l'assigner au stage
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    void testValidateInputValid() {
        // Simuler la saisie de l'utilisateur
        robot.clickOn("#nomTextField").write("Doe");
        robot.clickOn("#prenomTextField").write("John");
        robot.clickOn("#mailTextField").write("test@example.com");
        robot.clickOn("#roleComboBox");
        robot.type(KeyCode.DOWN); // Sélectionner le premier élément
        robot.type(KeyCode.ENTER); // "administrateur" ou "utilisateur", selon l'ordre dans le ComboBox
        
        // Simuler un clic sur le bouton de création
        robot.clickOn("#createButton");
        
        // Ici, vous devez définir le comportement attendu de votre application.
        // Par exemple, si vous vous attendez à ce qu'une nouvelle vue soit chargée, vous devrez vérifier cela.
        // Comme nous ne pouvons pas interagir directement avec Data.getInstance() depuis TestFX,
        // vous pouvez vérifier si une vue spécifique est affichée ou non, ou utiliser un spy/mock de Data.getInstance()
        // pour vérifier si les méthodes getAdministrators().add(...) ou getUsers().add(...) ont été appelées.
    }
}
