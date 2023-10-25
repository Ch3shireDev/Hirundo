package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.util.List;

public class ReturningBirdsStatisticsResolver {
    public ReturningBirdsStatisticsResolver() {
        wingParametersCalculator = new WingParametersCalculator();
    }

    public ReturningBirdsStatisticsResolver(IWingParametersCalculator wingParametersCalculator) {
        this.wingParametersCalculator = wingParametersCalculator;
    }

    IWingParametersCalculator wingParametersCalculator;
    public ReturningBirdsData getReturningBirdsData(List<DbBirdRecord> sortedRecords, List<DbBirdRecord> population) {
        var returningBirds = new ReturningBirdsData();

        DbBirdRecord first = getFirstRecord(sortedRecords);
        DbBirdRecord last = getLastRecord(sortedRecords);

        returningBirds.RingNumber = first.getRing();
        returningBirds.Species = first.getSpeciesCode();
        returningBirds.Population = population.size();

        returningBirds.FirstDateSeen = first.getDate();
        returningBirds.LastDateSeen = last.getDate();

        returningBirds.FirstSeasonSeen = first.getSeason();
        returningBirds.LastSeasonSeen = last.getSeason();

        returningBirds.Pointedness = wingParametersCalculator.getPointednessFactor(first);
        returningBirds.Symmetry = wingParametersCalculator.getSymmetryFactor(first);

        returningBirds.Weight = first.getWeight();
        returningBirds.Fat = first.getFat();
        returningBirds.Wing = first.getWing();
        returningBirds.Tail = first.getTail();

        returningBirds.D2 = first.getD2();
        returningBirds.D3 = first.getD3();
        returningBirds.D4 = first.getD4();
        returningBirds.D5 = first.getD5();
        returningBirds.D6 = first.getD6();
        returningBirds.D7 = first.getD7();
        returningBirds.D8 = first.getD8();

        return returningBirds;
    }

    private DbBirdRecord getLastRecord(List<DbBirdRecord> sortedRecords) {
        var records = sortedRecords
                .stream()
                .filter(r -> null != r.getDate())
                .toList();

        if(records.isEmpty()) {
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

        if(records.isEmpty()) {
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
