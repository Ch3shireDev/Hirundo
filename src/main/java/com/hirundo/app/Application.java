package com.hirundo.app;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {

        stage.setMinWidth(400);
        stage.setMinWidth(600);
        stage.setResizable(false);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HelloView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("view.css")).toExternalForm());
        stage.setTitle("Hirundo - powroty ptaków");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}