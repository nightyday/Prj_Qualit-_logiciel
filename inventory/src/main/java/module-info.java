module qualite_log {
    requires javafx.controls;
    requires javafx.fxml;

    opens qualite_log to javafx.fxml;
    exports qualite_log;
}
