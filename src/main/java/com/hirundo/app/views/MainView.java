package com.hirundo.app.views;

import com.hirundo.app.view_models.MainViewModel;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {

    private final Desktop desktop = Desktop.getDesktop();
//    public StringProperty selectedFileName = new SimpleStringProperty("Wybierz plik bazy danych .mdb");
    public StringProperty selectedFileName = new SimpleStringProperty("C:\\Users\\cheshire\\Documents\\GitHub\\AkcjaBaltyckaDB\\Ring_00_PODAB.mdb");
    public StringProperty oldTableName = new SimpleStringProperty("Tab_Ring_Podab");
    public StringProperty newTableName = new SimpleStringProperty("AB 2017_18_19_20_21S");
    public BooleanProperty isWindowDisabled = new SimpleBooleanProperty(false);
    public FloatProperty progress = new SimpleFloatProperty(0.0f);
    MainViewModel viewModel;
    @FXML
    private ProgressBar pb;
    @FXML
    private GridPane mainView;


    public MainView() {
    }

    public Boolean getIsWindowDisabled(){
        return isWindowDisabled.getValue();
    }

    public void setIsWindowDisabled(Boolean value){
        isWindowDisabled.setValue(value);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pb.progressProperty().bind(progress);
        mainView.disableProperty().bind(isWindowDisabled);

    }
    public Float getProgress() {
        return progress.getValue();
    }

    public void setProgress(Float value) {
        progress.set(value);
    }

    public String getSelectedFileName() {
        return selectedFileName.getValue();
    }

    public void setSelectedFileName(String value) {
        selectedFileName.set(value);
    }

    public String getOldTableName() {
        return oldTableName.getValue();
    }

    public void setOldTableName(String value) {
        viewModel.setOldTableName(value);
        oldTableName.set(value);
    }

    public String getNewTableName() {
        return newTableName.getValue();
    }

    public void setNewTableName(String value) {
        viewModel.setNewTableName(value);
        newTableName.set(value);
    }


    public void setViewModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public Parent getParent() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"), null, null, (x) -> this);

        return fxmlLoader.load();
    }

    public void loadDataAction(final ActionEvent actionEvent) {
        try {
            viewModel.setOldTableName(oldTableName.getValue());
            viewModel.setNewTableName(newTableName.getValue());
            viewModel.setSelectedFileName(selectedFileName.getValue());
            isWindowDisabled.setValue(true);
            progress.setValue(-1);
            new Thread(()->{
               try {
                   viewModel.loadData();
                   Platform.runLater(() -> progress.setValue(1.0f));
               }
               catch (Exception e) {
                   Platform.runLater(() -> progress.setValue(0.0f));
                   Alert a = new Alert(Alert.AlertType.ERROR);
                   a.setContentText(e.getMessage());
                   a.show();
               }
               finally {
                   Platform.runLater(()-> isWindowDisabled.setValue(false));
               }
            }).start();
        }
        catch (Exception e) {
            progress.setValue(0.0f);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }



    }


    public void selectFileName(final ActionEvent actionEvent) {

        if (null == viewModel) return;
        String result = viewModel.selectFileName();
        if (null != result) selectedFileName.setValue(result);
    }

    public StringProperty selectedFileNameProperty() {
        return selectedFileName;
    }

    private void openFile(final File file) {
        try {
            desktop.open(file);
        } catch (final IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}