package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.BirdSpeciesCalculatedData;
import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;
import java.util.stream.Stream;

public class ReturnsStatisticsCalculator {
    ReturningBirdsSummarizer returningBirdsSummarizer = new ReturningBirdsSummarizer();

    public BirdSpeciesCalculatedData getCalculatedData(List<DbBirdRecord> data, ReturnsStatisticsCalculatorParameters parameters) throws Exception {

        if (null == parameters.selectedSpecies) {
            throw new Exception("Species not selected");
        }

        if (BirdSex.Undefined == parameters.selectedSex) {
            throw new Exception("Sex not selected");
        }

        var speciesCode = parameters.selectedSpecies.speciesCode();

        if (speciesCode == null || speciesCode.isBlank()) {
            throw new Exception("Species code not selected");
        }

        var speciesNameEng = parameters.selectedSpecies.speciesNameEng();
        var speciesNameLat = parameters.selectedSpecies.speciesNameLat();
        var sexName = getSexName(parameters.selectedSex);

        Stream<DbBirdRecord> filteredData = data
                .stream()
                .filter(b -> speciesCode
                        .equals(b.getSpeciesCode()));

        if (BirdSex.Any != parameters.selectedSex) {
            filteredData = filteredData.filter(b -> b.getSex() == parameters.selectedSex);
        }

        var list = filteredData.toList();

        var recordsCount = list.size();

        var parameters2 = new ReturningBirdsSummarizerParameters();
        var returningBirds = returningBirdsSummarizer.getSummary(list, parameters2);

        var returnsCount = returningBirds.size();

        return new BirdSpeciesCalculatedData(speciesCode,
                                             speciesNameEng,
                                             speciesNameLat,
                                             sexName,
                                             recordsCount,
                                             returnsCount);
    }

    private String getSexName(BirdSex selectedSex) {
        return switch (selectedSex) {
            case Male -> "Samiec";
            case Female -> "Samica";
            case Any -> "Dowolna";
            default -> "Nieznana";
        };
    }
}
