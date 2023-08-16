package com.hirundo.app.views;

import com.hirundo.app.converters.BirdSexStringConverter;
import com.hirundo.app.converters.BirdSpeciesStringConverter;
import com.hirundo.app.view_models.MainViewModel;
import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.BirdSpecies;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {
    public ObservableList<BirdSpecies> speciesList;
    public ObservableList<BirdSex> sexList;
    @FXML
    public ComboBox<BirdSex> sexComboBox;
    public StringProperty fileName = new SimpleStringProperty("Wybierz plik bazy danych .mdb");
    //    public StringProperty fileName = new SimpleStringProperty("C:\\Users\\cheshire\\Documents\\GitHub\\AkcjaBaltyckaDB\\Ring_00_PODAB.mdb");
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
    public ObjectProperty<BirdSex> selectedSex = new SimpleObjectProperty<>(BirdSex.Female);
    public StringProperty selectedSexName = new SimpleStringProperty();
    public StringProperty writingResultsText = new SimpleStringProperty();
    public StringProperty loadingDatabaseStatus = new SimpleStringProperty();
    MainViewModel viewModel;
    BirdSpeciesStringConverter speciesConverter = new BirdSpeciesStringConverter();
    BirdSexStringConverter sexConverter = new BirdSexStringConverter();
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
    private Label writingResultsLabel;
    @FXML
    private Pane mainView;
    @FXML
    private Pane resultsPane;
    @FXML
    private Pane writeResultsPane;
    @FXML
    private Label loadingDatabaseStatusLabel;
    @FXML
    private Tab dataProcessingTab;
    @FXML
    private TabPane tabPane;
    @FXML
    private Button dataLoadingTabNextButton;
    @FXML
    private Label sexLabel;
    @FXML
    private TextField oldTableNameTextField;
    @FXML
    private TextField newTableNameTextField;
    @FXML
    private Label fileNameLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        speciesList = FXCollections.observableArrayList();
        speciesComboBox.setConverter(speciesConverter);
        selectedSpecies.bind(speciesComboBox
                                     .getSelectionModel()
                                     .selectedItemProperty());

        sexList = FXCollections.observableArrayList(BirdSex.Any, BirdSex.Male, BirdSex.Female);
        sexComboBox.setConverter(sexConverter);
        selectedSex.bind(sexComboBox
                                 .getSelectionModel()
                                 .selectedItemProperty());
        sexComboBox
                .getSelectionModel()
                .selectFirst();

        mainView
                .disableProperty()
                .bind(isWindowDisabled);
        resultsPane
                .disableProperty()
                .bind(isResultsDisabled);
        writeResultsPane
                .disableProperty()
                .bind(isWriteResultsDisabled);
        dataProcessingTab
                .disableProperty()
                .bind(isSpeciesSelectDisabled);
        dataLoadingTabNextButton
                .disableProperty()
                .bind(isSpeciesSelectDisabled);

        progressBar
                .progressProperty()
                .bind(progress);
        sexLabel
                .textProperty()
                .bind(selectedSexName);
        writingResultsLabel
                .textProperty()
                .bind(writingResultsText);
        loadingDatabaseStatusLabel
                .textProperty()
                .bind(loadingDatabaseStatus);
        speciesCodeLabel
                .textProperty()
                .bind(speciesCode);
        recordsCountLabel
                .textProperty()
                .bind(recordsCount);
        returnsCountLabel
                .textProperty()
                .bind(returnsCount);
        speciesNameEngLabel
                .textProperty()
                .bind(speciesNameEng);
        speciesNameLatinLabel
                .textProperty()
                .bind(speciesNameLatin);

        oldTableNameTextField
                .textProperty()
                .bindBidirectional(oldTableName);
        newTableNameTextField
                .textProperty()
                .bindBidirectional(newTableName);

        fileNameLabel
                .textProperty()
                .bind(fileName);

        viewModel.setOldTableName(oldTableName.getValue());
        viewModel.setNewTableName(newTableName.getValue());
//        viewModel.setFileName(fileName.getValue());
    }

    public String getLoadingDatabaseStatus() {
        return loadingDatabaseStatus.getValue();
    }

    public String getWritingResultsText() {
        return writingResultsText.getValue();
    }

    public Boolean getIsSpeciesSelectDisabled() {
        return isSpeciesSelectDisabled.getValue();
    }

    public String getSelectedSexName() {
        return selectedSexName.getValue();
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

    public ObservableList<BirdSpecies> getSpeciesList() {
        return speciesList;
    }

    public ObservableList<BirdSex> getSexList() {
        return sexList;
    }

    public Boolean getIsWindowDisabled() {
        return isWindowDisabled.getValue();
    }

    public Float getProgress() {
        return progress.getValue();
    }

    public String getFileName() {
        return fileName.getValue();
    }

    public void setViewModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public Parent getParent() throws IOException {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"), null, null, (x) -> this);

        return fxmlLoader.load();
    }

    public void loadDataAction() {

        var filename = viewModel.getSelectedFileName();
        if(filename == null || filename.isEmpty()){
            selectFileName();
        }

        var filename2 = viewModel.getSelectedFileName();
        if(filename2 == null || filename2.isEmpty()){
            return;
        }

        try {
            isWindowDisabled.setValue(true);
            progress.setValue(-1);
            loadingDatabaseStatus.setValue("Ładowanie danych...");
            new Thread(() -> {
                try {
                    viewModel.loadData();
                    var count = viewModel.getRecordsCount();
                    Platform.runLater(() -> loadingDatabaseStatus.setValue("Ładowanie zakończone. Załadowano " + count + " rekordów"));
                    Platform.runLater(() -> progress.setValue(1.0f));
                } catch (Exception e) {
                    Platform.runLater(() -> progress.setValue(0.0f));
                    Platform.runLater(() -> loadingDatabaseStatus.setValue("Błąd ładowania danych. " + e.getMessage()));
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setContentText(e.getMessage());
                    a.show();
                } finally {
                    Platform.runLater(() -> {
                        this.isWindowDisabled.setValue(false);
                        var speciesList = viewModel.getSpeciesList();
                        this.speciesList.addAll(speciesList);
                        this.speciesComboBox
                                .getSelectionModel()
                                .selectFirst();
                        this.speciesName.setValue(selectedSpecies
                                                          .getValue()
                                                          .speciesNameEng());
                        this.isSpeciesSelectDisabled.setValue(false);
                    });
                }
            }).start();

        } catch (Exception e) {
            progress.setValue(0.0f);
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    public void selectFileName() {

        if (null == viewModel) return;
        String result = viewModel.selectFileName();
        if (null != result) fileName.setValue(result);
    }


    public void introductionTabNext() {
        tabPane
                .getSelectionModel()
                .select(1);
    }

    public void dataLoadingTabNext() {
        tabPane
                .getSelectionModel()
                .select(2);
    }

    public void speciesComboBoxAction() {
        try {
            var species = speciesComboBox
                    .getSelectionModel()
                    .getSelectedItem();
            viewModel.setSpeciesSelected(species);

            getCalculatedData();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    public void sexComboBoxAction() {
        try {
            var sex = sexComboBox
                    .getSelectionModel()
                    .getSelectedItem();
            viewModel.setSexSelected(sex);
            getCalculatedData();
        } catch (Exception e) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }
    }


    private void getCalculatedData() throws Exception {
        var calculatedData = viewModel.getCalculatedData();
        speciesCode.setValue(calculatedData.speciesCode());
        speciesNameEng.setValue(calculatedData.speciesNameEng());
        speciesNameLatin.setValue(calculatedData.speciesNameLat());
        selectedSexName.setValue(calculatedData.selectedSexName());
        recordsCount.setValue(calculatedData
                                      .recordsCount()
                                      .toString());
        returnsCount.setValue(calculatedData
                                      .returnsCount()
                                      .toString());
        isResultsDisabled.setValue(false);
        isWriteResultsDisabled.setValue(false);
    }

    public void writeResultsForSelectedSpeciesAction() {
        try {
            var result = viewModel.writeResultsForSelectedSpecies();
            writingResultsText.setValue("Zapis zakończony. Zapisano " + result.RecordsCount + " rekordów do pliku " + result.OutputFileName);
        } catch (Exception e) {
            writingResultsText.setValue("Błąd zapisu. " + e.getMessage());
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    public void writeResultsForAllSpeciesAction() {
        try {
            var result = viewModel.writeResultsForAllSpecies();
            writingResultsText.setValue("Zapis zakończony. Zapisano " + result.RecordsCount + " rekordów do pliku " + result.OutputFileName);
        } catch (Exception e) {
            writingResultsText.setValue("Błąd zapisu. " + e.getMessage());
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(e.getMessage());
            a.show();
        }
    }

    public void updateOldTableName() {
        viewModel.setOldTableName(oldTableName.getValue());
    }

    public void updateNewTableName() {
        viewModel.setNewTableName(newTableName.getValue());
    }
}

