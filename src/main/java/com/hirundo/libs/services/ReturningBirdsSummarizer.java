package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;
import com.hirundo.libs.data_structures.Season;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ReturningBirdsSummarizer implements IReturningBirdsSummarizer {
    public List<ReturningBirdsData> getSummary(List<DbBirdRecord> records) {
        var ringNumbers = records
                .stream()
                .filter(b -> null != b.getRing() && !b
                        .getRing()
                        .isBlank())
                .collect(Collectors.groupingBy(DbBirdRecord::getRing));

        List<ReturningBirdsData> result = new ArrayList<>();

        for (var ringNumber : ringNumbers.keySet()) {
            var ringRecords = ringNumbers.get(ringNumber);
            if (ringRecords.size() < 2) {
                continue;
            }

            var sortedRecords = ringRecords
                    .stream()
                    .sorted(Comparator.comparing(DbBirdRecord::getDate))
                    .toList();

            var firstRecord= sortedRecords
                    .stream()
                    .findFirst()
                    .get();

            var firstSeason = firstRecord
                    .getSeason();

            if (firstSeason != Season.Autumn) {
                continue;
            }

            if (!Objects.equals(firstRecord.getAge(), "I") && !Objects.equals(firstRecord.getAge(), "J")) {
                continue;
            }

            var years = sortedRecords
                    .stream()
                    .map(DbBirdRecord::getYear)
                    .distinct()
                    .toList();

            if (years.size() < 2) {
                continue;
            }

            DbBirdRecord record = sortedRecords.get(0);
            ReturningBirdsData returningBirds = getReturningBirdsData(ringRecords, record);
            result.add(returningBirds);


        }

        return result;
    }

    private ReturningBirdsData getReturningBirdsData(List<DbBirdRecord> ringRecords, DbBirdRecord record) {
        var returningBirds = new ReturningBirdsData();
        returningBirds.RingNumber = record.getRing();
        returningBirds.Species = record.getSpeciesCode();
        returningBirds.Records = ringRecords;
        var sortedRecords = ringRecords
                .stream()
                .sorted(Comparator.comparing(DbBirdRecord::getDate))
                .toList();

        DbBirdRecord first = sortedRecords.get(0);
        var last = sortedRecords.get(sortedRecords.size() - 1);

        returningBirds.FirstDateSeen = first.getDate();
        returningBirds.LastDateSeen = last.getDate();

        returningBirds.FirstSeasonSeen = first.getSeason();
        returningBirds.LastSeasonSeen = last.getSeason();

        returningBirds.Weight = first.getWeight();

        return returningBirds;
    }
}


