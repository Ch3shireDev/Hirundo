package com.hirundo.app.models;

import com.hirundo.app.services.IFileChooser;
import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.data_structures.BirdSpeciesCalculatedData;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.services.IBirdRecordDataLoaderBuilder;
import com.hirundo.libs.services.ReturnsStatisticsCalculator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MainModel {
    private final IBirdRecordDataLoaderBuilder builder;
    private final IFileChooser fileChooser;
    List<DbBirdRecord> data = new ArrayList<>();
    BirdSpecies selectedSpecies;
    BirdSex selectedSex = BirdSex.Any;
//    ReturningBirdsFinder returningBirdsFinder = new ReturningBirdsFinder();
    ReturnsStatisticsCalculator calculator = new ReturnsStatisticsCalculator();
    private String selectedFileName;
    private String oldTableName;
    private String newTableName;

    public MainModel(IBirdRecordDataLoaderBuilder builder, IFileChooser fileChooser) {
        this.builder = builder;
        this.fileChooser = fileChooser;
    }

    public void writeResultsForSelectedSpecies() {
//        var file = fileChooser.selectFileToSave("example.csv");
    }

    public void writeResultsForAllSpecies() {
    }

    public void loadData() throws Exception {

        var dataLoader = builder
                .withFilename(selectedFileName)
                .withOldTableName(oldTableName)
                .withNewTableName(newTableName)
                .build();
        data = dataLoader.loadData();

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

    public void setSelectedFileName(String selectedFileName) {
        this.selectedFileName = selectedFileName;
    }

    public void setNewTableName(String value) {
        this.newTableName = value;
    }

    public void setOldTableName(String value) {
        this.oldTableName = value;
    }

    public List<BirdSpecies> getSpeciesList() {

        if (0 == data.size()) {
            return new ArrayList<>();
        }

        var species = data
                .stream()
                .map(this::asSpecies)
                .toList();

        var distinctSpecies = new HashMap<String, BirdSpecies>();

        for (var birdSpecies : species) {
            var key = birdSpecies.speciesCode();
            if (isEmpty(birdSpecies)) continue;
            if (!distinctSpecies.containsKey(key)) {
                distinctSpecies.put(key, birdSpecies);
            } else {
                var existingSpecies = distinctSpecies.get(key);
                if (missingNames(existingSpecies) && !missingNames(birdSpecies)) {
                    distinctSpecies.put(key, birdSpecies);
                }
            }
        }

        return distinctSpecies
                .values()
                .stream()
                .sorted(Comparator.comparing(BirdSpecies::speciesCode))
                .toList();
    }

    Boolean isEmpty(BirdSpecies birdSpecies) {
        var key = birdSpecies.speciesCode();
        return (null == key || key.isBlank() || "-".equals(key) || "?".equals(key));
    }

    private BirdSpecies asSpecies(DbBirdRecord record) {
        return new BirdSpecies(record.getSpeciesCode(), record.getSpeciesNameEng(), record.getSpeciesNameLat());
    }

    private Boolean missingNames(BirdSpecies species) {
        var nameEng = species.speciesNameEng();
        var nameLat = species.speciesNameLat();
        return null == nameEng || null == nameLat || nameEng.isBlank() || nameLat.isBlank();
    }

    public Integer getRecordsCount() {
        return data.size();
    }

    public BirdSpeciesCalculatedData getCalculatedData() throws Exception {
        if (null == selectedSpecies) {
            throw new Exception("Species not selected");
        }
        if (BirdSex.Undefined == selectedSex) {
            throw new Exception("Sex not selected");
        }

        var speciesCode = selectedSpecies.speciesCode();

        if (null == speciesCode || speciesCode.isBlank()) {
            throw new Exception("Species code not selected");
        }

        return calculator.getCalculatedData(data, selectedSpecies, selectedSex);
    }

    public void setSpeciesSelected(BirdSpecies species) {
        this.selectedSpecies = species;
    }

    public void setSexSelected(BirdSex sex) {
        selectedSex = sex;
    }
}


