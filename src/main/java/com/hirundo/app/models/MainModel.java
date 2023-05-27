package com.hirundo.app.models;

import com.hirundo.app.services.IFileChooser;
import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.data_structures.BirdSpeciesCalculatedData;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.services.IBirdRecordDataLoaderBuilder;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainModel {
    private final IBirdRecordDataLoaderBuilder builder;
    private final IFileChooser fileChooser;
    List<DbBirdRecord> data = new ArrayList<>();
    BirdSpecies selectedSpecies;
    BirdSex selectedSex = BirdSex.Any;
    private String selectedFileName;
    private String oldTableName;
    private String newTableName;

    public MainModel(IBirdRecordDataLoaderBuilder builder, IFileChooser fileChooser) {
        this.builder = builder;
        this.fileChooser = fileChooser;
    }

    public void writeResults() {
        var file = fileChooser.selectFileToSave("example.csv");
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
        var birdSpecies = new BirdSpecies(record.getSpeciesCode(), record.getSpeciesNameEng(), record.getSpeciesNameLat());
        return birdSpecies;
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
        var speciesNameEng = selectedSpecies.speciesNameEng();
        var speciesNameLat = selectedSpecies.speciesNameLat();
        var sexName = getSexName();

        if (null == speciesCode || speciesCode.isBlank()) {
            throw new Exception("Species code not selected");
        }

        var filteredData = data
                .stream()
                .filter(b -> speciesCode.equals(b.getSpeciesCode()));

        if (BirdSex.Any != selectedSex) {
            filteredData = filteredData.filter(b -> selectedSex == b.getSex());
        }

        var list = filteredData.toList();

        var recordsCount = list.size();

        var returningBirds = findReturningBirds(list);

        var returnsCount =  returningBirds.size();

        return new BirdSpeciesCalculatedData(speciesCode, speciesNameEng, speciesNameLat, sexName, recordsCount, returnsCount);
    }

    private String getSexName() {
        return switch (selectedSex) {
            case Male -> "Samiec";
            case Female -> "Samica";
            case Any -> "Dowolna";
            default -> "Nieznana";
        };
    }

    public void setSpeciesSelected(BirdSpecies species) {
        this.selectedSpecies = species;
    }

    public void setSexSelected(BirdSex sex) {
        selectedSex = sex;
    }

    public List<DbBirdRecord> findReturningBirds(List<DbBirdRecord> records) {
        var ringNumbers = records
                .stream()
                .filter(b -> null != b.getRing() && !b.getRing().isBlank())
                .collect(Collectors.groupingBy(DbBirdRecord::getRing));

        List<DbBirdRecord> result = new ArrayList<>();

        for (var ringNumber : ringNumbers.keySet()) {
            var ringRecords = ringNumbers.get(ringNumber);
            if (2 > ringRecords.size()) {
                continue;
            }

            var seasons = ringRecords
                    .stream()
                    .collect(Collectors.groupingBy(DbBirdRecord::getSeason));

            if (2 > seasons.size()) {
                continue;
            }

            DbBirdRecord record = (DbBirdRecord) ringRecords
                    .stream()
                    .sorted(Comparator.comparing(DbBirdRecord::getDate, Comparator.reverseOrder()))

                    .toArray()[0];

            result.add(record);
        }

        return result;
    }
}

