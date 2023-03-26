package com.hirundo.app.views;

import com.hirundo.app.view_models.MainViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    public StringProperty selectedFileName = new SimpleStringProperty("Wybierz plik bazy danych .mdb");
    MainViewModel viewModel;

    public MainView() {
    }

    public String getSelectedFileName() {
        return selectedFileName.getValue();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setViewModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public Parent getParent() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"),
                null,
                null,
                (x) -> this);

        return fxmlLoader.load();
    }

    public void loadDataAction(final ActionEvent actionEvent) {
            viewModel.loadData();
    }

    public void selectFileName(final ActionEvent actionEvent) {

        if(viewModel==null)return;
        String result = viewModel.selectFileName();
        if(result!=null) selectedFileName.setValue(result);
    }
    public StringProperty selectedFileNameProperty() {
        return selectedFileName;
    }
    public void setSelectedFileName(String value) {
        selectedFileName.set(value);
    }
    private void openFile(final File file) {
        try {
            desktop.open(file);
        } catch (final IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}