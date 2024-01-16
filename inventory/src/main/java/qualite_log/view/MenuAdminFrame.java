package qualite_log.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

public class MenuAdminFrame {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem bookingCreateMenuItem;

    @FXML
    private MenuItem bookingDeleteMenuItem;

    @FXML
    private MenuItem bookingListMenuItem;

    @FXML
    private MenuItem deconnexionMenuItem;

    @FXML
    private MenuBar menuAdminMenuBar;

    @FXML
    private VBox menuVBox;

    @FXML
    private MenuItem toolCreateMenuItem;

    @FXML
    private MenuItem toolDeleteMenuItem;

    @FXML
    private MenuItem toolListMenuItem;

    @FXML
    private MenuItem toolTypeCreateMenuItem;

    @FXML
    private MenuItem toolTypeDeleteMenuItem;

    @FXML
    private MenuItem toolTypeListMenuItem;

    @FXML
    private MenuItem toolUpdateMenuItem;

    @FXML
    private MenuItem userCreateMenuItem;

    @FXML
    private MenuItem userDeleteMenuItem;

    @FXML
    private MenuItem userListMenuItem;

    @FXML
    private MenuItem userUpdateMenuItem;

    private Parent root;

    @FXML
    void initialize() {
        assert bookingCreateMenuItem != null : "fx:id=\"bookingCreateMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert bookingDeleteMenuItem != null : "fx:id=\"bookingDeleteMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert bookingListMenuItem != null : "fx:id=\"bookingListMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert deconnexionMenuItem != null : "fx:id=\"deconnexionMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert menuAdminMenuBar != null : "fx:id=\"menuAdminMenuBar\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert menuVBox != null : "fx:id=\"menuVBox\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert toolCreateMenuItem != null : "fx:id=\"toolCreateMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert toolDeleteMenuItem != null : "fx:id=\"toolDeleteMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert toolListMenuItem != null : "fx:id=\"toolListMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert toolTypeCreateMenuItem != null : "fx:id=\"toolTypeCreateMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert toolTypeDeleteMenuItem != null : "fx:id=\"toolTypeDeleteMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert toolTypeListMenuItem != null : "fx:id=\"toolTypeListMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert toolUpdateMenuItem != null : "fx:id=\"toolUpdateMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert userCreateMenuItem != null : "fx:id=\"userCreateMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert userDeleteMenuItem != null : "fx:id=\"userDeleteMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert userListMenuItem != null : "fx:id=\"userListMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";
        assert userUpdateMenuItem != null : "fx:id=\"userUpdateMenuItem\" was not injected: check your FXML file 'MenuAdminFrame.fxml'.";

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolListFrame.fxml"));
            root = (Parent) fxmlLoader.load();

            menuVBox.getChildren().add(root);

            toolTypeCreateMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeCreateFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            toolTypeDeleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeDeleteFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            toolTypeListMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolTypeListFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            toolCreateMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolCreateFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            toolDeleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolDeleteFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            toolListMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolListFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            toolUpdateMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ToolUpdateFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            userCreateMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserCreateFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            userDeleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserDeleteFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            userListMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserListFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            userUpdateMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/UserUpdateFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            bookingListMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/BookingListFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            bookingCreateMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/BookingCreateFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            bookingDeleteMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/BookingDeleteFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            deconnexionMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent t) {
                    try {
                        menuVBox.getChildren().remove(root);
                        menuVBox.getChildren().remove(menuAdminMenuBar);
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/qualite_log/ConnexionFrame.fxml"));
                        root = (Parent) fxmlLoader.load();
                        menuVBox.getChildren().add(root);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public VBox getMenuVBox() {
        return menuVBox;
    }

    public Parent getRoot() {
        return root;
    }
}
