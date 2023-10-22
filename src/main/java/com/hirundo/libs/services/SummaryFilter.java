package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdAge;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.Season;

import java.util.List;

public class SummaryFilter {
    public boolean isForSummary(List<DbBirdRecord> sortedRecords) {
        if (sortedRecords.size() < 2) {
            return false;
        }

        DbBirdRecord firstRecord = getFirstRecord(sortedRecords);

        if (!isFirstSeasonAutumn(firstRecord)) {
            return false;
        }

        if (!isJuvenile(firstRecord)) {
            return false;
        }

        return isFromMoreThanOneYear(sortedRecords);
    }

    private DbBirdRecord getFirstRecord(List<DbBirdRecord> sortedRecords) {
        return sortedRecords
                .stream()
                .findFirst()
                .get();
    }

    boolean isFirstSeasonAutumn(DbBirdRecord record) {
        return Season.Autumn == record.getSeason();
    }

    boolean isJuvenile(DbBirdRecord record) {
        return record.getAge() == BirdAge.Infantile || record.getAge() == BirdAge.Juvenile;
    }

    boolean isFromMoreThanOneYear(List<DbBirdRecord> sortedRecords) {
        return getYears(sortedRecords).size() > 1;
    }

    private List<Integer> getYears(List<DbBirdRecord> sortedRecords) {
        return sortedRecords
                .stream()
                .map(DbBirdRecord::getYear)
                .distinct()
                .toList();
    }

}
