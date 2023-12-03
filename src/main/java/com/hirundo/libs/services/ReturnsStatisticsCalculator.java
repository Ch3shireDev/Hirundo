package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.BirdSpeciesCalculatedData;
import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;
import java.util.stream.Stream;

public class ReturnsStatisticsCalculator {
    ReturningBirdsSummarizer returningBirdsSummarizer = new ReturningBirdsSummarizer();

    public BirdSpeciesCalculatedData getCalculatedData(List<DbBirdRecord> data, ReturnsStatisticsCalculatorParameters parameters) throws Exception {

        if (null == parameters.species) {
            throw new Exception("Species not selected");
        }

        if (BirdSex.Undefined == parameters.selectedSex) {
            throw new Exception("Sex not selected");
        }

        var speciesCode = parameters.species.speciesCode();

        if (null == speciesCode || speciesCode.isBlank()) {
            throw new Exception("Species code not selected");
        }

        var speciesNameEng = parameters.species.speciesNameEng();
        var speciesNameLat = parameters.species.speciesNameLat();
        var sexName = getSexName(parameters.selectedSex);

        Stream<DbBirdRecord> filteredData = data
                .stream()
                .filter(b -> speciesCode
                        .equals(b.getSpeciesCode()));

        if(BirdSex.Male == parameters.selectedSex || BirdSex.Female == parameters.selectedSex){
            filteredData = filteredData.filter(b -> parameters.selectedSex == b.getSex());
        }

        var list = filteredData.toList();

        var recordsCount = list.size();

        ReturningBirdsSummarizerParameters parameters2 = getParameters(parameters);

        var returningBirds = returningBirdsSummarizer.getSummary(list, parameters2);

        var returnsCount = returningBirds.size();

        return new BirdSpeciesCalculatedData(speciesCode,
                                             speciesNameEng,
                                             speciesNameLat,
                                             sexName,
                                             recordsCount,
                                             returnsCount);
    }

    private ReturningBirdsSummarizerParameters getParameters(ReturnsStatisticsCalculatorParameters parameters) {
        var parameters2 = new ReturningBirdsSummarizerParameters();
        parameters2.dateRangeStart = parameters.dateRangeStart;
        parameters2.dateRangeEnd = parameters.dateRangeEnd;
        parameters2.useDateRange = parameters.isDateRangeSelected;
        parameters2.sex = parameters.selectedSex;
        return parameters2;
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
