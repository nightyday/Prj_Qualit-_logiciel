package qualite_log.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ToolUpdateFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField nomTextField;

    @FXML
    private ComboBox<?> referenceComboBox;

    @FXML
    private TextField referenceTextField;

    @FXML
    private ComboBox<?> typeComboBox;

    @FXML
    private Button updateButton;

    @FXML
    private Label updateLabel;

    @FXML
    private TextField versionTextField;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert nomTextField != null : "fx:id=\"nomTextField\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert referenceComboBox != null : "fx:id=\"referenceComboBox\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert referenceTextField != null : "fx:id=\"referenceTextField\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert typeComboBox != null : "fx:id=\"typeComboBox\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert updateButton != null : "fx:id=\"updateButton\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert updateLabel != null : "fx:id=\"updateLabel\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";
        assert versionTextField != null : "fx:id=\"versionTextField\" was not injected: check your FXML file 'ToolUpdateFrame.fxml'.";

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
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
