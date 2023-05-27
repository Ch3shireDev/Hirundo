package com.hirundo.app.views;

import com.hirundo.app.converters.BirdSpeciesStringConverter;
import com.hirundo.app.view_models.MainViewModel;
import com.hirundo.libs.data_structures.BirdSpecies;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {
    public ObservableList<BirdSpecies> speciesList;
    //    public StringProperty selectedFileName = new SimpleStringProperty("Wybierz plik bazy danych .mdb");
    public StringProperty fileName = new SimpleStringProperty("C:\\Users\\cheshire\\Documents\\GitHub\\AkcjaBaltyckaDB\\Ring_00_PODAB.mdb");
    public StringProperty oldTableName = new SimpleStringProperty("Tab_Ring_Podab");
    public StringProperty newTableName = new SimpleStringProperty("AB 2017_18_19_20_21S");
    public FloatProperty progress = new SimpleFloatProperty(0.0f);
    public StringProperty speciesName = new SimpleStringProperty();
    public StringProperty recordsCount = new SimpleStringProperty();
    public StringProperty returnsCount = new SimpleStringProperty();
    public StringProperty speciesCode = new SimpleStringProperty();
    public StringProperty speciesNameEng = new SimpleStringProperty();
    public StringProperty speciesNameLatin = new SimpleStringProperty();
    public BooleanProperty isWindowDisabled = new SimpleBooleanProperty(false);
    public BooleanProperty isResultsDisabled = new SimpleBooleanProperty(true);
    public BooleanProperty isWriteResultsDisabled = new SimpleBooleanProperty(true);
    public BooleanProperty isSpeciesSelectDisabled = new SimpleBooleanProperty(true);
    public ObjectProperty<BirdSpecies> selectedSpecies = new SimpleObjectProperty<>();
    MainViewModel viewModel;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ComboBox<BirdSpecies> speciesComboBox;
    @FXML
    private Label recordsCountLabel;
    @FXML
    private Label returnsCountLabel;
    @FXML
    private Label speciesCodeLabel;
    @FXML
    private Label speciesNameEngLabel;
    @FXML
    private Label speciesNameLatinLabel;
    @FXML
    private Pane mainView;
    @FXML
    private Pane resultsPane;
    @FXML
    private Pane writeResultsPane;
    @FXML
    private Pane speciesSelectPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        speciesList = FXCollections.observableArrayList();
        speciesComboBox.setConverter(new BirdSpeciesStringConverter());
        selectedSpecies.bind(speciesComboBox.getSelectionModel().selectedItemProperty());

        mainView.disableProperty().bind(isWindowDisabled);
        resultsPane.disableProperty().bind(isResultsDisabled);
        writeResultsPane.disableProperty().bind(isWriteResultsDisabled);
        speciesSelectPane.disableProperty().bind(isSpeciesSelectDisabled);

        progressBar.progressProperty().bind(progress);
        speciesCodeLabel.textProperty().bind(speciesCode);
        recordsCountLabel.textProperty().bind(recordsCount);
        returnsCountLabel.textProperty().bind(returnsCount);
        speciesNameEngLabel.textProperty().bind(speciesNameEng);
        speciesNameLatinLabel.textProperty().bind(speciesNameLatin);

        viewModel.setOldTableName(oldTableName.getValue());
        viewModel.setNewTableName(newTableName.getValue());
        viewModel.setFileName(fileName.getValue());
    }

    public String getDescription(){
        return "Program do wybierania z bazy danych rekordów zdarzeń schwytania ptaków w czasie migracji.\r\n\r\nProgram łączy się z bazą danych RINGER ( plik .mdb) w celu pobrania rekordów schwytań ptaków. Dane są przetwarzane w poszukiwaniu przypadków tych samych ptaków schwytanych w następujących po sobie sezonach jesiennym i wiosennym. Takie zdarzenie sugeruje, że ptak był w stanie przeżyć wędrówkę i powrót. Hipoteza badawcza mówi, że mierzone cechy takiego ptaka będą w sposób istotny różne od średniej populacji ptaków danego gatunku i płci w danym sezonie. Program ma ułatwiać przeglądanie i wybieranie z bazy danych interesujących nas rekordów powracających ptaków.";
    }
    public Boolean getIsSpeciesSelectDisabled() {
        return isSpeciesSelectDisabled.getValue();
    }

    public Boolean getIsResultsDisabled() {
        return isResultsDisabled.getValue();
    }

    public Boolean getIsWriteResultsDisabled() {
        return isWriteResultsDisabled.getValue();
    }

    public String getSpeciesCode() {
        return speciesCode.getValue();
    }

    public String getSpeciesNameEng() {
        return speciesNameEng.getValue();
    }

    public String getSpeciesNameLatin() {
        return speciesNameLatin.getValue();
    }

    public String getRecordsCount() {
        return recordsCount.getValue();
    }

    public String getReturnsCount() {
        return returnsCount.getValue();
    }

    public String getSpeciesName() {
        return speciesName.getValue();
    }

    public ObservableList<BirdSpecies> getSpeciesList() {
        return speciesList;
    }

    public Boolean getIsWindowDisabled() {
        return isWindowDisabled.getValue();
    }

    public void setIsWindowDisabled(Boolean value) {
        isWindowDisabled.setValue(value);
    }

    public Float getProgress() {
        return progress.getValue();
    }

    public void setProgress(Float value) {
        progress.set(value);
    }

    public String getFileName() {
        return fileName.getValue();
    }

    public void setFileName(String value) {
        fileName.set(value);

        viewModel.setFileName(fileName.getValue());
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
            isWindowDisabled.setValue(true);
            progress.setValue(-1);
            new Thread(() -> {
                try {
//                   viewModel.loadData();
                    Platform.runLater(() -> progress.setValue(1.0f));
                } catch (Exception e) {
                    Platform.runLater(() -> progress.setValue(0.0f));
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText(e.getMessage());
                    a.show();
                } finally {
                    Platform.runLater(() -> isWindowDisabled.setValue(false));
                }
            }).start();

            var speciesList = viewModel.getSpeciesList();
            this.speciesList.addAll(speciesList);
            this.speciesComboBox.getSelectionModel().selectFirst();
            this.speciesName.setValue(selectedSpecies.getValue().speciesNameEng());
            this.isSpeciesSelectDisabled.setValue(false);
        } catch (Exception e) {
            progress.setValue(0.0f);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    public void selectFileName(final ActionEvent actionEvent) {

        if (null == viewModel) return;
        String result = viewModel.selectFileName();
        if (null != result) fileName.setValue(result);
    }

    public void applySelectedSpeciesAction(ActionEvent actionEvent) {
        if (null == viewModel) return;
        try {
            var calculatedData = viewModel.getCalculatedData();
            speciesCode.setValue(calculatedData.speciesCode());
            speciesNameEng.setValue(calculatedData.speciesNameEng());
            speciesNameLatin.setValue(calculatedData.speciesNameLatin());
            recordsCount.setValue(calculatedData.recordsCount().toString());
            returnsCount.setValue(calculatedData.returnsCount().toString());
            isResultsDisabled.setValue(false);
            isWriteResultsDisabled.setValue(false);
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }
    }
}

