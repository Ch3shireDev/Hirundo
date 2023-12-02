package com.hirundo.app;

import com.hirundo.app.models.MainModel;
import com.hirundo.app.services.DialogFileChooser;
import com.hirundo.app.view_models.MainViewModel;
import com.hirundo.app.views.MainView;
import com.hirundo.libs.loaders.BirdRecordDataLoaderBuilder;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class Application extends javafx.application.Application {

    final double width = 600.0;
    final double height = 400.0;

    public Application() {
    }

    public static void main(final String[] args) {
        Locale.setDefault(Locale.of("pl", "PL"));
        javafx.application.Application.launch();
    }

    @Override
    public void start(final Stage stage) throws IOException {

        final var fileChooser = new DialogFileChooser();
        final var builder = new BirdRecordDataLoaderBuilder();
        final var model = new MainModel(builder, fileChooser);
        final var viewModel = new MainViewModel(model);
        final var view = new MainView();
        view.setViewModel(viewModel);

        var hirundo = getClass().getResourceAsStream("hirundo.png");
        if (null != hirundo) {
            stage.getIcons().add(new javafx.scene.image.Image(hirundo));
        }
        stage.setMinHeight(width);
        stage.setMinWidth(height);
        stage.setResizable(false);

        final Scene scene = new Scene(view.getParent());

        stage.setTitle("Hirundo ver. 1.6.0");
        stage.setScene(scene);
        stage.show();
    }
}