module com.hirundo.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires com.healthmarketscience.jackcess;

    opens com.hirundo.app to javafx.fxml;
    exports com.hirundo.app;
    exports com.hirundo.app.views;
    opens com.hirundo.app.views to javafx.fxml;
    exports com.hirundo.app.models;
    opens com.hirundo.app.models to javafx.fxml;
    exports com.hirundo.app.view_models;
    opens com.hirundo.app.view_models to javafx.fxml;
    exports com.hirundo.libs.services;
    opens com.hirundo.libs.services to javafx.fxml;
    opens com.hirundo.libs.data_structures to javafx.fxml;
    exports com.hirundo.app.services;
    opens com.hirundo.app.services to javafx.fxml;

    exports com.hirundo.libs.data_structures;
}