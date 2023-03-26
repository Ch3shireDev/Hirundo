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
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setViewModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public Parent getParent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("MainView.fxml"),
                null,
                null,
                (x) -> this);

        return fxmlLoader.load();
    }

    public void loadDataAction(ActionEvent actionEvent) {

        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if (null != file) {
            this.openFile(file);
            this.viewModel.loadData(file);
        }

    }

    private void openFile(File file) {
        try {
            this.desktop.open(file);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}