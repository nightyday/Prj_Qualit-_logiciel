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
import javafx.scene.shape.Rectangle;
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
    private Rectangle warningRectangle;

    @FXML
    private Label warningLabel;

    @FXML
    void initialize() throws IllegalArgumentException {
        assert anchorPane != null : "fx:id=\"anchorPane\" was not injected: check your FXML file 'ToolTypeCreateFrame.fxml'.";
        assert createButton != null : "fx:id=\"createButton\" was not injected: check your FXML file 'ToolTypeCreateFrame.fxml'.";
        assert createLabel != null : "fx:id=\"createLabel\" was not injected: check your FXML file 'ToolTypeCreateFrame.fxml'.";
        assert typeTextField != null : "fx:id=\"typeTextField\" was not injected: check your FXML file 'ToolTypeCreateFrame.fxml'.";

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) throws IllegalArgumentException {
                if (!Pattern.matches("^[a-zA-Z0-9\\-]{1,30}$", typeTextField.getText())) {
                    warningRectangle.setX(typeTextField.getLayoutX() - 1);
                    warningRectangle.setY(typeTextField.getLayoutY() - 1);
                    warningRectangle.setWidth(typeTextField.getWidth() + 2);
                    warningRectangle.setHeight(typeTextField.getHeight() + 2);
                    warningRectangle.setVisible(true);
                    
                    warningLabel.setLayoutX(typeTextField.getLayoutX());
                    warningLabel.setLayoutY(typeTextField.getLayoutY() + 30);
                    warningLabel.setVisible(true);
                }
                else {
                    try {
                        Data.getInstance().getEquipmentTypes().add(new EquipmentType(typeTextField.getText()));

                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeListFrame.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        anchorPane.getChildren().clear();
                        anchorPane.getChildren().add(root);
                    }
                    catch (Exception e) {
                        WarningFrame warning = new WarningFrame("Erreur", "Désolé, l’action n’a pas pu être effectuée. Veuillez réessayer.");
                        warning.show();
                    }
                }
            }
        });
    }

}
