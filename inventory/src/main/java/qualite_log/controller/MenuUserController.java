package qualite_log.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import qualite_log.session.SessionManager;

public class MenuUserController {

    @FXML
    private AnchorPane anchorPane;

    private Parent root;

    @FXML
    private VBox menuVBox;

    @FXML
    void initialize() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeListFrame.fxml"));
        root = fxmlLoader.load();
        anchorPane.getChildren().add(root);
    }

    @FXML
    public void handleMenuAction(ActionEvent event) {
        Object source = event.getSource();
        if (source instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) source;
            String viewPath = getViewPathById(menuItem.getId());
            changeView(viewPath);
        }
    }

    private String getViewPathById(String id) {
        switch (id) {
            case "toolTypeListMenuItem":
                return "/qualite_log/ToolTypeListFrame.fxml";
            case "toolListMenuItem":
                return "/qualite_log/ToolListFrame.fxml";
            case "bookingCreateMenuItem":
                return "/qualite_log/BookingCreateFrame.fxml";
            case "bookingDeleteMenuItem":
                return "/qualite_log/BookingDeleteFrame.fxml";

            default:
                return null;
        }
    }

    private void changeView(String pathView) {
        if (pathView != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pathView));
                root = fxmlLoader.load();
                anchorPane.getChildren().clear();
                anchorPane.getChildren().add(root);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

@FXML
    public void handleDeconnexion(ActionEvent event) {
        SessionManager.clearSession();
       try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ConnexionFrame.fxml"));
            Parent parent = (Parent) fxmlLoader.load();
            Scene loginScene = new Scene(parent);

            // Get the current stage
            Stage currentStage = (Stage) menuVBox.getScene().getWindow();

            // Set the login scene in the current stage
            currentStage.setScene(loginScene);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}