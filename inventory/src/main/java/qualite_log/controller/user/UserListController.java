package qualite_log.controller.user;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.Person;


public class UserListController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<Person, String> mailColumn;

    @FXML
    private TableColumn<Person, String> nomColumn;

    @FXML
    private TableColumn<Person, String> prenomColumn;

    @FXML
    private TableColumn<Person, String> roleColumn;

    @FXML
    private TableView<Person> tableView;

    @FXML
    void initialize() {
        try {
            List<Person> persons = new ArrayList<>();
            persons.addAll(Data.getInstance().getAdministrators());
            persons.addAll(Data.getInstance().getUsers());

            ObservableList<Person> data = FXCollections.observableArrayList(persons);
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            mailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            roleColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.setItems(data);
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        } catch (Exception e) {
            showAlert("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
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
