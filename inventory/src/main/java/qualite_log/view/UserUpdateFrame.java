package qualite_log.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.xml.crypto.Data;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Administrator;
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
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert mailComboBox != null : "fx:id=\"mailComboBox\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert mailTextField != null : "fx:id=\"mailTextField\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert nomTextField != null : "fx:id=\"nomTextField\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert prenomTextField != null : "fx:id=\"prenomTextField\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert roleComboBox != null : "fx:id=\"roleComboBox\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'UserUpdateFrame.fxml'.";
        
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
                if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", mailTextField.getText())) {
                    warningRectangle.setX(mailTextField.getLayoutX() - 1);
                    warningRectangle.setY(mailTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(mailTextField.getWidth() + 2);
                    warningRectangle.setHeight(mailTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);

                    warningLabel.setLayoutX(mailTextField.getLayoutX());
                    warningLabel.setLayoutY(mailTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else if (!Pattern.matches("^[a-zA-Z0-9\\-]{1,30}$", nomTextField.getText())) {
                    warningRectangle.setX(nomTextField.getLayoutX() - 1);
                    warningRectangle.setY(nomTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(nomTextField.getWidth() + 2);
                    warningRectangle.setHeight(nomTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);
                    
                    warningLabel.setLayoutX(nomTextField.getLayoutX());
                    warningLabel.setLayoutY(nomTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else if (!Pattern.matches("^[a-zA-Z0-9\\-]{1,30}$", prenomTextField.getText())) {
                    warningRectangle.setX(prenomTextField.getLayoutX() - 1);
                    warningRectangle.setY(prenomTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(prenomTextField.getWidth() + 2);
                    warningRectangle.setHeight(prenomTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);
                    
                    warningLabel.setLayoutX(prenomTextField.getLayoutX());
                    warningLabel.setLayoutY(prenomTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else {
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

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserListFrame.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        anchorPane.getChildren().clear();
                        anchorPane.getChildren().add(root);
                    }
                    catch (Exception e) {
                        WarningFrame warning = new WarningFrame("Erreur", "Impossible de modifier ce compte car celui-ci est actuellement utilisé.");
                        warning.show();
                    }
                }
            }
        });
    }
}
