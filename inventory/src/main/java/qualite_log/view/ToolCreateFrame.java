package qualite_log.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ToolCreateFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button createButton;

    @FXML
    private Label createLabel;

    @FXML
    private TextField nomTextField;

    @FXML
    private TextField referenceTextField;

    @FXML
    private TextField versionTextField;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert createLabel != null : "fx:id=\"createLabel\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert nomTextField != null : "fx:id=\"nomTextField\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert referenceTextField != null : "fx:id=\"referenceTextField\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";
        assert versionTextField != null : "fx:id=\"versionTextField\" was not injected: check your FXML file 'ToolCreateFrame.fxml'.";

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolListFrame.fxml"));
                    Parent root = (Parent) fxmlLoader.load();
                    anchorPane.getChildren().clear();
                    anchorPane.getChildren().add(root);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
