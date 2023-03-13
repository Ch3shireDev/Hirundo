module com.hirundo.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.hirundo.app to javafx.fxml;
    exports com.hirundo.app;
    exports com.hirundo.app.controllers;
    opens com.hirundo.app.controllers to javafx.fxml;
    exports com.hirundo.app.models;
    opens com.hirundo.app.models to javafx.fxml;
    exports com.hirundo.app.viewmodels;
    opens com.hirundo.app.viewmodels to javafx.fxml;
}