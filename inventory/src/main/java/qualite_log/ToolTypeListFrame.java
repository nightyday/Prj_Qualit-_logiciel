package qualite_log;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class ToolTypeListFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label listLabel;

    @FXML
    private ScrollBar scrollBar;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolTypeListFrame.fxml'.";
        assert listLabel != null : "fx:id=\"listLabel\" was not injected: check your FXML file 'ToolTypeListFrame.fxml'.";
        assert scrollBar != null : "fx:id=\"scrollBar\" was not injected: check your FXML file 'ToolTypeListFrame.fxml'.";
        assert tableView != null : "fx:id=\"tableView\" was not injected: check your FXML file 'ToolTypeListFrame.fxml'.";
        assert typeColumn != null : "fx:id=\"typeColumn\" was not injected: check your FXML file 'ToolTypeListFrame.fxml'.";

    }

}
