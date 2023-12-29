module qualite_log {
    requires javafx.controls;
    requires javafx.fxml;

    opens qualite_log to javafx.fxml;
    exports qualite_log;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.datatype.jsr310;


    //opens com.fasterxml.jackson.datatype.jsr310;
    exports qualite_log.model;
    
    opens qualite_log.model to com.fasterxml.jackson.databind;
    opens qualite_log.data_import.serializers;
    opens qualite_log.data_import.deserializers;
}

