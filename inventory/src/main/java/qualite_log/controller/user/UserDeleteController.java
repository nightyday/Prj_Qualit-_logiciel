package qualite_log.controller.user;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import qualite_log.data_import.DataWriter;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.model.User;
public class UserDeleteController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button deleteButton;

    @FXML
    private Label deleteLabel;

    @FXML
    private ComboBox<String> mailComboBox;

    private List<Person> persons;

    @FXML
    void initialize() {
        Data.updateData();
        
        persons = new ArrayList<>();
        persons.addAll(Data.getInstance().getUsers());
        persons.addAll(Data.getInstance().getAdministrators());

        List<String> emailData = new ArrayList<>();
        for (Person person : persons) {
            emailData.add(person.getEmail());
        }
        mailComboBox.getItems().addAll(emailData);

        if (mailComboBox.getItems().size() > 0) {
            mailComboBox.setValue(mailComboBox.getItems().get(0));
        } 

        
    }

    @FXML
    public void handleDeleteAction(ActionEvent event) {
        try {
            Person personSelected = persons.get(mailComboBox.getItems().indexOf(mailComboBox.getValue()));
            deleteUser(personSelected);
            switchToUserListView();
        } catch (Exception e) {
            showAlert("Erreur", "Impossible de supprimer ce compte car celui-ci est actuellement utilisé.");
        }
    }

    private void deleteUser(Person person) {
        if (person.getType().equals("administrator")) {
            Administrator toRemove = Data.getInstance().getAdministrators().stream()
                .filter(admin -> admin.getEmail().equals(person.getEmail()))
                .findFirst()
                .orElse(null);
            if (toRemove != null) {
                toRemove.delete(); // On supprime toutes les réservations liés à l'admin
                Data.getInstance().removeAdministrator(toRemove);

                DataWriter.extractAdministrators(Data.getInstance()); // On met à jour les fichiers .json
            }
        } else if (person.getType().equals("user")) {
            User toRemove = Data.getInstance().getUsers().stream()
                .filter(user -> user.getEmail().equals(person.getEmail()))
                .findFirst()
                .orElse(null);
            if (toRemove != null) {
                toRemove.delete(); // On supprime toutes les réservations liés à l'utilisateur
                Data.getInstance().removeUser(toRemove);

                DataWriter.extractUsers(Data.getInstance()); // On met à jour les fichiers .json
            }
        }
    }


    private void switchToUserListView() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserListFrame.fxml"));
            Parent root = fxmlLoader.load();
            anchorPane.getChildren().clear();
            anchorPane.getChildren().add(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

