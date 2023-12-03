module com.hirundo.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.sql;
    requires com.healthmarketscience.jackcess;
    requires com.opencsv;
    requires org.apache.commons.collections4;
    requires org.apache.commons.lang3;

    opens com.hirundo.app to javafx.fxml;
    exports com.hirundo.app;
    exports com.hirundo.app.views;
    opens com.hirundo.app.views to javafx.fxml;
    exports com.hirundo.app.models;
    opens com.hirundo.app.models to javafx.fxml;
    exports com.hirundo.libs.services;
    opens com.hirundo.libs.services to javafx.fxml;
    opens com.hirundo.libs.data_structures to javafx.fxml;
    exports com.hirundo.app.models.services;
    opens com.hirundo.app.models.services to javafx.fxml;

    exports com.hirundo.libs.data_structures;
    exports com.hirundo.app.converters;
    opens com.hirundo.app.converters to javafx.fxml;
    exports com.hirundo.libs.serializers;
    opens com.hirundo.libs.serializers to javafx.fxml;
    exports com.hirundo.libs.loaders;
    opens com.hirundo.libs.loaders to javafx.fxml;
    exports com.hirundo.libs.filters;
    opens com.hirundo.libs.filters to javafx.fxml;
    exports com.hirundo.libs.mappers;
    opens com.hirundo.libs.mappers to javafx.fxml;
    exports com.hirundo.app.controllers;
    opens com.hirundo.app.controllers to javafx.fxml;
}