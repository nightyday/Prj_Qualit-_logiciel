package qualite_log;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class UserListFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label listLabel;

    @FXML
    private TableColumn<?, ?> mailColumn;

    @FXML
    private TableColumn<?, ?> nomColumn;

    @FXML
    private TableColumn<?, ?> prenomColumn;

    @FXML
    private TableColumn<?, ?> roleColumn;

    @FXML
    private ScrollBar scrollBar;

    @FXML
    private TableView<?> tableView;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert listLabel != null : "fx:id=\"listLabel\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert mailColumn != null : "fx:id=\"mailColumn\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert nomColumn != null : "fx:id=\"nomColumn\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert prenomColumn != null : "fx:id=\"prenomColumn\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert roleColumn != null : "fx:id=\"roleColumn\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert scrollBar != null : "fx:id=\"scrollBar\" was not injected: check your FXML file 'UserListFrame.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'UserListFrame.fxml'.";

    }    
}
