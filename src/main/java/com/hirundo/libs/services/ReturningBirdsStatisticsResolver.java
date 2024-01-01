package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ReturningBirdsStatisticsResolver {
    IWingParametersCalculator wingParametersCalculator;
    IStatisticsCalculator statisticsCalculator;

    public ReturningBirdsStatisticsResolver() {
        wingParametersCalculator = new WingParametersCalculator();
        statisticsCalculator = new StatisticsCalculator();
    }

    public ReturningBirdsStatisticsResolver(IWingParametersCalculator wingParametersCalculator, IStatisticsCalculator statisticsCalculator) {
        this.wingParametersCalculator = wingParametersCalculator;
        this.statisticsCalculator = statisticsCalculator;
    }

    private static int countFat(List<DbBirdRecord> totalPopulation, Integer fatLevel) {
        return totalPopulation.stream().filter(b -> fatLevel.equals(b.getFat())).toList().size();
    }

    public ReturningBirdsData getReturningBirdsData(List<DbBirdRecord> returningBirdRecords, List<DbBirdRecord> totalPopulation) {
        var returningBirds = new ReturningBirdsData();

        DbBirdRecord first = getFirstRecord(returningBirdRecords);
        DbBirdRecord last = getLastRecord(returningBirdRecords);

        returningBirds.RingNumber = first.getRing();
        returningBirds.Species = first.getSpeciesCode();
        returningBirds.Sex = first.getSex();
        returningBirds.Age = first.getAge();
        returningBirds.Population = totalPopulation.size();
        returningBirds.RecordsCount = returningBirdRecords.size();

        returningBirds.FirstDateSeen = first.getDate();
        returningBirds.LastDateSeen = last.getDate();

        returningBirds.FirstSeasonSeen = first.getSeason();
        returningBirds.LastSeasonSeen = last.getSeason();

        returningBirds.Pointedness = wingParametersCalculator.getPointednessFactor(first);

        var populationPointedness = totalPopulation
                .stream()
                .map(b -> wingParametersCalculator.getPointednessFactor(b))
                .toArray(BigDecimal[]::new);

        returningBirds.PointednessMean = statisticsCalculator.calculateMean(populationPointedness);
        returningBirds.PointednessStandardDeviation = statisticsCalculator.calculateStandardDeviation(populationPointedness);

        var populationSymmetry = totalPopulation
                .stream()
                .map(b -> wingParametersCalculator.getSymmetryFactor(b))
                .toArray(BigDecimal[]::new);

        returningBirds.Symmetry = wingParametersCalculator.getSymmetryFactor(first);
        returningBirds.SymmetryMean = statisticsCalculator.calculateMean(populationSymmetry);
        returningBirds.SymmetryStandardDeviation = statisticsCalculator.calculateStandardDeviation(populationSymmetry);

        var populationWeight = totalPopulation
                .stream()
                .map(DbBirdRecord::getWeight)
                .toArray(BigDecimal[]::new);

        returningBirds.Weight = first.getWeight();
        returningBirds.WeightMean = statisticsCalculator.calculateMean(populationWeight);
        returningBirds.WeightStandardDeviation = statisticsCalculator.calculateStandardDeviation(populationWeight);

        var populationFat = totalPopulation
                .stream()
                .map(DbBirdRecord::getFat)
                .filter(Objects::nonNull)
                .map(BigDecimal::valueOf)
                .toArray(BigDecimal[]::new);

        returningBirds.Fat = first.getFat();
        returningBirds.FatMedian = statisticsCalculator.calculateMedian(populationFat);
        returningBirds.FatUpperQuartile = statisticsCalculator.calculateUpperQuartile(populationFat);
        returningBirds.FatLowerQuartile = statisticsCalculator.calculateLowerQuartile(populationFat);

        var populationWing = totalPopulation
                .stream()
                .map(DbBirdRecord::getWing)
                .toArray(BigDecimal[]::new);

        returningBirds.Wing = first.getWing();
        returningBirds.WingMean = statisticsCalculator.calculateMean(populationWing);
        returningBirds.WingStandardDeviation = statisticsCalculator.calculateStandardDeviation(populationWing);

        var populationTail = totalPopulation
                .stream()
                .map(DbBirdRecord::getTail)
                .toArray(BigDecimal[]::new);

        returningBirds.Tail = first.getTail();
        returningBirds.TailMean = statisticsCalculator.calculateMean(populationTail);
        returningBirds.TailStandardDeviation = statisticsCalculator.calculateStandardDeviation(populationTail);

        returningBirds.D2 = first.getD2();
        returningBirds.D3 = first.getD3();
        returningBirds.D4 = first.getD4();
        returningBirds.D5 = first.getD5();
        returningBirds.D6 = first.getD6();
        returningBirds.D7 = first.getD7();
        returningBirds.D8 = first.getD8();

        returningBirds.F0 = countFat(totalPopulation, 0);
        returningBirds.F1 = countFat(totalPopulation, 1);
        returningBirds.F2 = countFat(totalPopulation, 2);
        returningBirds.F3 = countFat(totalPopulation, 3);
        returningBirds.F4 = countFat(totalPopulation, 4);
        returningBirds.F5 = countFat(totalPopulation, 5);
        returningBirds.F6 = countFat(totalPopulation, 6);
        returningBirds.F7 = countFat(totalPopulation, 7);
        returningBirds.F8 = countFat(totalPopulation, 8);

        return returningBirds;
    }

    private DbBirdRecord getLastRecord(List<DbBirdRecord> sortedRecords) {
        var records = sortedRecords
                .stream()
                .filter(r -> null != r.getDate())
                .toList();

        if (records.isEmpty()) {
            return sortedRecords.get(sortedRecords.size() - 1);
        }

        DbBirdRecord last = records.get(0);
        for (DbBirdRecord record : records) {
            if (record
                    .getDate()
                    .isAfter(last.getDate())) {
                last = record;
            }
        }
        return last;
    }

    private DbBirdRecord getFirstRecord(List<DbBirdRecord> sortedRecords) {
        var records = sortedRecords
                .stream()
                .filter(r -> null != r.getDate())
                .toList();

        if (records.isEmpty()) {
            return sortedRecords.get(0);
        }

        var first = records.get(0);
        for (DbBirdRecord record : records) {
            var date1 = record.getDate();
            var date2 = first.getDate();
            if (date1.isBefore(date2)) {
                first = record;
            }
        }
        return first;
    }


}
