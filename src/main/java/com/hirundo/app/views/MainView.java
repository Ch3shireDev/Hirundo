package com.hirundo.app.views;

import com.hirundo.app.view_models.MainViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    private final Desktop desktop = Desktop.getDesktop();
    MainViewModel viewModel;

    public MainView() {
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    public void setViewModel(final MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public Parent getParent() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MainView.fxml"),
                null,
                null,
                (x) -> this);

        return fxmlLoader.load();
    }

    public void loadDataAction(final ActionEvent actionEvent) {

        FileChooser fileChooser = new FileChooser();
        final File file = fileChooser.showOpenDialog(null);
        if (null != file) {
            this.openFile(file);
            this.viewModel.loadData(file);
        }

    }

    private void openFile(final File file) {
        try {
            this.desktop.open(file);
        } catch (final IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}