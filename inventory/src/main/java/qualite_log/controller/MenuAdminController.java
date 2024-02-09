package qualite_log.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import qualite_log.session.SessionManager;

public class MenuAdminController {

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
            case "toolTypeCreateMenuItem": 
                return "/qualite_log/ToolTypeCreateFrame.fxml";
            case "toolTypeDeleteMenuItem": 
                return "/qualite_log/ToolTypeDeleteFrame.fxml";
            case "toolTypeListMenuItem": 
                return "/qualite_log/ToolTypeListFrame.fxml";
            case "toolCreateMenuItem": 
                return "/qualite_log/ToolCreateFrame.fxml";
            case "toolDeleteMenuItem": 
                return "/qualite_log/ToolDeleteFrame.fxml";
            case "toolListMenuItem": 
                return "/qualite_log/ToolListFrame.fxml";
            case "toolUpdateMenuItem": 
                return "/qualite_log/ToolUpdateFrame.fxml";
            case "userCreateMenuItem": 
                return "/qualite_log/UserCreateFrame.fxml";
            case "userDeleteMenuItem": 
                return "/qualite_log/UserDeleteFrame.fxml";
            case "userListMenuItem": 
                return "/qualite_log/UserListFrame.fxml";
            case "userUpdateMenuItem": 
                return "/qualite_log/UserUpdateFrame.fxml";
            case "bookingListMenuItem": 
                return "/qualite_log/BookingListFrame.fxml";
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
        changeView("/qualite_log/ConnexionFrame.fxml");
    }

    //  deconnexionMenuItem.setOnAction(new EventHandler<ActionEvent>() {
    //             public void handle(ActionEvent t) {
    //                 try {
    //                     menuVBox.getChildren().clear();
    //                     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ConnexionFrame.fxml"));
    //                     root = (Parent) fxmlLoader.load();
    //                     menuVBox.getChildren().add(root);
    //                     SessionManager.clearSession();
    //                 }
    //                 catch (Exception e) {
    //                     e.printStackTrace();
    //                 }
    //             }
    //         });

}

