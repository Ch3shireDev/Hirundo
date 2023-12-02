package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdAge;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.Season;

import java.util.Comparator;
import java.util.List;

public class SummaryFilter implements ISummaryFilter {
    public boolean isReturningBird(List<DbBirdRecord> records) {
        if (2 > records.size()) {
            return false;
        }

        DbBirdRecord firstRecord = getFirstRecord(records);

        if (!isFirstSeasonAutumn(firstRecord)) {
            return false;
        }

        if (!isJuvenile(firstRecord)) {
            return false;
        }

        return isFromMoreThanOneYear(records);
    }

    private DbBirdRecord getFirstRecord(List<DbBirdRecord> records) {
        return records
                .stream()
                .min(Comparator.comparing(DbBirdRecord::getDate))
                .orElse(records.get(0));
    }

    boolean isFirstSeasonAutumn(DbBirdRecord record) {
        return Season.Autumn == record.getSeason();
    }

    boolean isJuvenile(DbBirdRecord record) {
        return BirdAge.Infantile == record.getAge() || BirdAge.Juvenile == record.getAge();
    }

    boolean isFromMoreThanOneYear(List<DbBirdRecord> sortedRecords) {
        return 1 < getYears(sortedRecords).size();
    }

    private List<Integer> getYears(List<DbBirdRecord> sortedRecords) {
        return sortedRecords
                .stream()
                .map(DbBirdRecord::getYear)
                .distinct()
                .toList();
    }

}
