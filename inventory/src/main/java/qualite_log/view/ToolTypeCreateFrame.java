package qualite_log.view;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import qualite_log.model.Data;
import qualite_log.model.EquipmentType;

public class ToolTypeCreateFrame {

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
    private TextField typeTextField;

    @FXML
    void initialize() {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolTypeCreateFrame.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'ToolTypeCreateFrame.fxml'.";
        assert createLabel != null : "fx:id=\"createLabel\" was not injected: check your FXML file 'ToolTypeCreateFrame.fxml'.";
        assert typeTextField != null : "fx:id=\"typeTextField\" was not injected: check your FXML file 'ToolTypeCreateFrame.fxml'.";

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                try {
                    if (Pattern.matches("^[a-zA-Z0-9]{1,30}$", typeTextField.getText())) {
                        Data.getInstance().getEquipmentTypes().add(new EquipmentType(typeTextField.getText()));

                        try {
                            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeListFrame.fxml"));
                            Parent root = (Parent) fxmlLoader.load();
                            anchorPane.getChildren().clear();
                            anchorPane.getChildren().add(root);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("Error pattern");
                    }
                }
                catch (Exception e) {
                    System.out.println("Error");
                }
            }
        });
    }

}
