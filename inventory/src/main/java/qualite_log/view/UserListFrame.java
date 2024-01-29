package qualite_log.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
<<<<<<< HEAD
=======
import javafx.scene.control.Label;
>>>>>>> main
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Person;

public class UserListFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableColumn<?, ?> mailColumn;

    @FXML
    private TableColumn<?, ?> nomColumn;

    @FXML
    private TableColumn<?, ?> prenomColumn;

    @FXML
    private TableColumn<?, ?> roleColumn;

    @FXML
    private TableView<Person> tableView;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert mailColumn != null : "fx:id=\"mailColumn\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert nomColumn != null : "fx:id=\"nomColumn\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert prenomColumn != null : "fx:id=\"prenomColumn\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert roleColumn != null : "fx:id=\"roleColumn\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'UserListFrame.fxml'.";

        try {
            List<Person> persons = new ArrayList<>();
            persons.addAll(Data.getInstance().getAdministrators());
            persons.addAll(Data.getInstance().getUsers());

            ObservableList<Person> data = FXCollections.observableArrayList(persons);
            nomColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            prenomColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
            mailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            roleColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.setItems(data);
            
            tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        }
        catch (Exception e) {
            WarningFrame warning = new WarningFrame("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
            warning.show();
        }
    }    
}
