package qualite_log.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Administrator;
import qualite_log.model.Data;
import qualite_log.model.Person;
import qualite_log.model.User;

public class UserUpdateFrame {

    private static final String ADMINISTRATEUR = "administrateur";
    private static final String USER = "user";

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ComboBox<String> mailComboBox;

    @FXML
    private TextField mailTextField;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField prenomTextField;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private Button updateButton;

    @FXML
    private Label updateLabel;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert mailComboBox != null : "fx:id=\"mailComboBox\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert mailTextField != null : "fx:id=\"mailTextField\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert nomTextField != null : "fx:id=\"nomTextField\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert prenomTextField != null : "fx:id=\"prenomTextField\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert roleComboBox != null : "fx:id=\"roleComboBox\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert updateLabel != null : "fx:id=\"updateLabel\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        
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

        roleComboBox.getItems().addAll(ADMINISTRATEUR, USER);

        // Fill informations when the user is choose
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                Person personSelected = persons.get(emailData.indexOf(mailComboBox.getValue()));
                mailTextField.setText(personSelected.getEmail());
                nomTextField.setText(personSelected.getLastName());
                prenomTextField.setText(personSelected.getFirstName());
                roleComboBox.setValue(personSelected.getType());
            }
        };
        mailComboBox.setOnAction(event);

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                if (Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", mailTextField.getText()) && Pattern.matches("^[a-zA-Z0-9]{1,30}$", nomTextField.getText()) && Pattern.matches("^[a-zA-Z0-9]{1,30}$", prenomTextField.getText())) {
                    try {
                        Person personSelected = persons.get(emailData.indexOf(mailComboBox.getValue()));
                        if (personSelected.getType().equals(ADMINISTRATEUR)) {
                            Data.getInstance().getAdministrators().remove(personSelected);
                            if (roleComboBox.getValue().equals(ADMINISTRATEUR)) {
                                Data.getInstance().getAdministrators().add(new Administrator(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
                            }
                            else {
                                Data.getInstance().getUsers().add(new User(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
                            }
                        }
                        if (personSelected.getType().equals(USER)) {
                            Data.getInstance().getUsers().remove(personSelected);
                            if (roleComboBox.getValue().equals(ADMINISTRATEUR)) {
                                Data.getInstance().getAdministrators().add(new Administrator(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
                            }
                            else {
                                Data.getInstance().getUsers().add(new User(nomTextField.getText(), prenomTextField.getText(), mailTextField.getText()));
                            }
                        }
                    }
                    catch (Exception e) {
                        try {
                            Alert alert = new Alert(AlertType.WARNING);
            
                            alert.setTitle("Erreur");
                            alert.setHeaderText(null);
                            alert.setContentText("Impossible de modifier les informations de ce compte car celui-ci est actuellement utilisé.");
                            alert.showAndWait();
                        }
                        catch (Exception error) {
                            error.printStackTrace();
                        }
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
                else {
                    try {
                        Alert alert = new Alert(AlertType.WARNING);

                        alert.setTitle("Erreur");
                        alert.setHeaderText(null);
                        alert.setContentText("Format de la saisie non conforme.");
                        alert.showAndWait();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
