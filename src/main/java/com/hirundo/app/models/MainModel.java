package com.hirundo.app.models;

import com.hirundo.app.services.CsvFileWriter;
import com.hirundo.app.services.ICsvFileWriter;
import com.hirundo.app.services.IFileChooser;
import com.hirundo.libs.data_structures.*;
import com.hirundo.libs.filters.ISpeciesFilter;
import com.hirundo.libs.filters.SpeciesFilter;
import com.hirundo.libs.loaders.IBirdRecordDataLoaderBuilder;
import com.hirundo.libs.mappers.IReturningBirdsDataCsvRecordMapper;
import com.hirundo.libs.mappers.ReturningBirdsDataCsvRecordMapper;
import com.hirundo.libs.serializers.CsvSerializer;
import com.hirundo.libs.serializers.ICsvSerializer;
import com.hirundo.libs.services.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MainModel {
    public List<DbBirdRecord> data = new ArrayList<>();
    private final IBirdRecordDataLoaderBuilder builder;
    public IFileChooser fileChooser;
    public ISpeciesFilter speciesFilter = new SpeciesFilter();
    public IReturningBirdsSummarizer returningBirdsSummarizer = new ReturningBirdsSummarizer();
    public IReturningBirdsDataCsvRecordMapper mapper = new ReturningBirdsDataCsvRecordMapper();
    public ICsvSerializer<CsvReturningBirdsData> serializer = new CsvSerializer<>(CsvReturningBirdsData.class);
    public ICsvFileWriter csvFileWriter = new CsvFileWriter();
    BirdSpecies selectedSpecies;
    BirdSex selectedSex = BirdSex.Any;
    ReturnsStatisticsCalculator calculator = new ReturnsStatisticsCalculator();
    private String selectedFileName;
    private String oldTableName;
    private String newTableName;
    private LocalDate dateRangeStart;
    private LocalDate dateRangeEnd;
    private boolean isDateRangeSelected;

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
        var parameters = getReturnsStatisticsCalculatorParameters();
        return calculator.getCalculatedData(data, parameters);
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
        var parameters = getReturningBirdsSummarizerParameters();
        var filteredResults = speciesFilter.filterBySpecies(data, selectedSpecies);
        var returningData = returningBirdsSummarizer.getSummary(filteredResults, parameters);
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
        var parameters = getReturningBirdsSummarizerParameters();
        var returningData = returningBirdsSummarizer.getSummary(data, parameters);
        var mappedData = mapper.getCsvReturningBirdsData(returningData);
        var result = serializer.serializeToCsv(mappedData);
        var filename = fileChooser.selectFileToSave("all-species.csv");
        csvFileWriter.writeToFile(filename, result);
        var fileSaveResult = new FileSaveResult();
        fileSaveResult.OutputFileName = filename;
        fileSaveResult.RecordsCount = mappedData.size();
        return fileSaveResult;
    }

    public void setDateRangeStart(LocalDate dateRangeStart) {
        this.dateRangeStart = dateRangeStart;
    }

    public void setDateRangeEnd(LocalDate dateRangeEnd) {
        this.dateRangeEnd = dateRangeEnd;
    }

    public void setIsDateRangeSelected(boolean selected) {
    this.isDateRangeSelected = selected;
    }

    private ReturningBirdsSummarizerParameters getReturningBirdsSummarizerParameters() {
        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.useDateRange = isDateRangeSelected;
        parameters.dateRangeStart = dateRangeStart;
        parameters.dateRangeEnd = dateRangeEnd;
        parameters.sex = selectedSex;
        parameters.speciesCode = selectedSpecies.speciesCode();
        return parameters;
    }

    private ReturnsStatisticsCalculatorParameters getReturnsStatisticsCalculatorParameters() {
        var parameters = new ReturnsStatisticsCalculatorParameters();
        parameters.species = selectedSpecies;
        parameters.selectedSex = selectedSex;
        parameters.dateRangeStart = dateRangeStart;
        parameters.dateRangeEnd = dateRangeEnd;
        parameters.isDateRangeSelected = isDateRangeSelected;
        return parameters;
    }
}

