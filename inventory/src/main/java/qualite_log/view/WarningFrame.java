package qualite_log.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class WarningFrame {
    private String title;
    private String message;

    public WarningFrame(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void show() {
        try {
            Alert alert = new Alert(AlertType.WARNING);

            alert.setTitle(title);
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
        catch (Exception error) {
            error.printStackTrace();
        }
    }
}