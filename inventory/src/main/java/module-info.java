module qualite_log {
    requires javafx.base;
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;

    opens qualite_log to javafx.fxml;
    exports qualite_log;

    opens qualite_log.controller to javafx.fxml;
    exports qualite_log.controller;

    opens qualite_log.controller.booking to javafx.fxml;
    exports qualite_log.controller.booking;

    opens qualite_log.controller.tool to javafx.fxml;
    exports qualite_log.controller.tool;

    opens qualite_log.controller.tooltype to javafx.fxml;
    exports qualite_log.controller.tooltype;

    opens qualite_log.controller.user to javafx.fxml;
    exports qualite_log.controller.user;

    opens qualite_log.view to javafx.fxml;
    exports qualite_log.view;

    // Modules pour Jackson (json)
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;
    // Accès à Jackson aux Serializers et Deserializers
    opens qualite_log.model to com.fasterxml.jackson.databind;
    opens qualite_log.data_import.serializers;
    opens qualite_log.data_import.deserializers;

    exports qualite_log.model;
}
