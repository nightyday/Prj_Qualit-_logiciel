module qualite_log {
    requires javafx.controls;
    requires javafx.fxml;

    opens qualite_log to javafx.fxml;
    exports qualite_log;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;


    exports qualite_log.model;
}

