package com.hirundo.app;

import com.hirundo.app.models.MainModel;
import com.hirundo.app.view_models.MainViewModel;
import com.hirundo.app.views.MainView;
import com.hirundo.libs.services.AccessNewDbBirdRecordDataLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public Application() {
    }

    final double width = 600.0;
    final double height = 400.0;

    @Override
    public void start(Stage stage) throws IOException {

        var dataLoader = new AccessNewDbBirdRecordDataLoader();

        var model = new MainModel(dataLoader);
        var viewModel = new MainViewModel(model);
        var view = new MainView();
        view.setViewModel(viewModel);

        stage.setMinHeight(width);
        stage.setMinWidth(height);
        stage.setResizable(false);

        Scene scene = new Scene(view.getParent());

        stage.setTitle("Hirundo - powroty ptaków");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}