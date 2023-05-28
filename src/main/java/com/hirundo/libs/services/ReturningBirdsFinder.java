package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.ReturningBirdsData;
import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReturningBirdsFinder {

    public List<ReturningBirdsData> findReturningBirds(List<DbBirdRecord> records) {
        var ringNumbers = records
                .stream()
                .filter(b -> null != b.getRing() && !b
                        .getRing()
                        .isBlank())
                .collect(Collectors.groupingBy(DbBirdRecord::getRing));

        List<ReturningBirdsData> result = new ArrayList<>();

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

            DbBirdRecord record = ringRecords.get(0);

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

        var first = sortedRecords.get(0);
        var last = sortedRecords.get(sortedRecords.size() - 1);

        returningBirds.FirstDateSeen = first.getDate();
        returningBirds.LastDateSeen = last.getDate();

        returningBirds.FirstSeasonSeen = first.getSeason();
        returningBirds.LastSeasonSeen = last.getSeason();
        return returningBirds;
    }
}


