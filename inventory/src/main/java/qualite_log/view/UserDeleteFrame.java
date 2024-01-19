package qualite_log.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.Person;

public class UserDeleteFrame {

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

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'UserDeleteFrame.fxml'.";
        assert deleteButton != null : "fx:id=\"deleteButton\" was not injected: check your FXML file 'UserDeleteFrame.fxml'.";
        assert deleteLabel != null : "fx:id=\"deleteLabel\" was not injected: check your FXML file 'UserDeleteFrame.fxml'.";
        assert mailComboBox != null : "fx:id=\"mailComboBox\" was not injected: check your FXML file 'UserDeleteFrame.fxml'.";

        // Add elements in the comboBoxs
        List<Person> persons = new ArrayList<>();
        persons.addAll(Data.getInstance().getUsers());
        persons.addAll(Data.getInstance().getAdministrators());
        List<String> emailData = new ArrayList<>();
        int i;
        for (i = 0; i < persons.size(); i++) {
            emailData.add(persons.get(i).getEmail());
        }
        mailComboBox.getItems().addAll(emailData);

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    Person personSelected = persons.get(emailData.indexOf(mailComboBox.getValue()));
                    if (personSelected.getType().equals("administrateur")) {
                        Data.getInstance().getAdministrators().remove(personSelected);
                    }
                    if (personSelected.getType().equals("user")) {
                        Data.getInstance().getUsers().remove(personSelected);
                    }
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserListFrame.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        anchorPane.getChildren().clear();
                        anchorPane.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                catch (Exception e) {
                    System.out.println("Error");
                }
            }
        });
    }

}
