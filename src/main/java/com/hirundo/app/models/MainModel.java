package com.hirundo.app.models;

import com.hirundo.app.services.IFileChooser;
import com.hirundo.libs.data_structures.*;
import com.hirundo.libs.services.*;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
    private final IBirdRecordDataLoaderBuilder builder;
    public IFileChooser fileChooser;
    public ISpeciesFilter speciesFilter = new SpeciesFilter();
    public IReturningBirdsSummarizer returningBirdsSummarizer = new ReturningBirdsSummarizer();
    public IReturningBirdsDataCsvRecordMapper mapper = new ReturningBirdsDataCsvRecordMapper();
    public ICsvSerializer<CsvReturningBirdsData> serializer = new CsvSerializer<>(CsvReturningBirdsData.class);
    public ICsvFileWriter csvFileWriter = new CsvFileWriter();
    public List<DbBirdRecord> data = new ArrayList<>();
    BirdSpecies selectedSpecies;
    BirdSex selectedSex = BirdSex.Any;
    ReturnsStatisticsCalculator calculator = new ReturnsStatisticsCalculator();
    private String selectedFileName;
    private String oldTableName;
    private String newTableName;

    public MainModel(IBirdRecordDataLoaderBuilder builder, IFileChooser fileChooser) {
        this.builder = builder;
        this.fileChooser = fileChooser;
    }

    public void loadData() throws Exception {
        var dataLoader = builder
                .withFilename(selectedFileName)
                .withOldTableName(oldTableName)
                .withNewTableName(newTableName)
                .build();
        data = dataLoader.loadData();
    }

    public BirdSpeciesCalculatedData getCalculatedData() throws Exception {
        return calculator.getCalculatedData(data, selectedSpecies, selectedSex);
    }

    public String selectFileName() {
        var file = fileChooser.selectFileToOpen();
        if (null != file) {
            selectedFileName = file;
        }
        return selectedFileName;
    }

    public String getSelectedFileName() {
        return selectedFileName;
    }

    public void setNewTableName(String value) {
        this.newTableName = value;
    }

    public void setOldTableName(String value) {
        this.oldTableName = value;
    }

    public List<BirdSpecies> getSpeciesList() {
        return speciesFilter.getSpeciesList(data);
    }

    public Integer getRecordsCount() {
        return data.size();
    }

    public void setSpeciesSelected(BirdSpecies species) {
        this.selectedSpecies = species;
    }

    public void setSexSelected(BirdSex sex) {
        selectedSex = sex;
    }

    public FileSaveResult writeResultsForSelectedSpecies() throws Exception {
        var filteredResults = speciesFilter.filterBySpecies(data, selectedSpecies);
        var returningData = returningBirdsSummarizer.getSummary(filteredResults);
        var mappedData = mapper.getCsvReturningBirdsData(returningData);
        var result = serializer.serializeToCsv(mappedData);
        var filename = fileChooser.selectFileToSave(String.format("%s.csv", selectedSpecies.speciesCode().replace(".", "-")));
        csvFileWriter.writeToFile(filename, result);
        var fileSaveResult = new FileSaveResult();
        fileSaveResult.OutputFileName = filename;
        fileSaveResult.RecordsCount = mappedData.size();
        return fileSaveResult;
    }

    public FileSaveResult writeResultsForAllSpecies() throws Exception {
        var returningData = returningBirdsSummarizer.getSummary(data);
        var mappedData = mapper.getCsvReturningBirdsData(returningData);
        var result = serializer.serializeToCsv(mappedData);
        var filename = fileChooser.selectFileToSave("all-species.csv");
        csvFileWriter.writeToFile(filename, result);
        var fileSaveResult = new FileSaveResult();
        fileSaveResult.OutputFileName = filename;
        fileSaveResult.RecordsCount = mappedData.size();
        return fileSaveResult;
    }
}

