package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReturningBirdsSummarizer implements IReturningBirdsSummarizer {
    ISummaryFilter summaryFilter = new SummaryFilter();
    IPopulationFilter populationFilter = new PopulationFilter();
    ReturningBirdsStatisticsResolver resolver = new ReturningBirdsStatisticsResolver();

    public ReturningBirdsSummarizer() {
    }

    public ReturningBirdsSummarizer(ISummaryFilter summaryFilter, IPopulationFilter populationFilter) {
        this.summaryFilter = summaryFilter;
        this.populationFilter = populationFilter;
    }

    public List<ReturningBirdsData> getSummary(List<DbBirdRecord> records) {
        List<ReturningBirdsData> result = new ArrayList<>();

        Map<String, List<DbBirdRecord>> ringNumbers = getRingNumbers(records);

        for (var ringNumber : ringNumbers.keySet()) {

            var ringRecords = ringNumbers.get(ringNumber);
            List<DbBirdRecord> sortedRecords = getSortedRecords(ringRecords);
            if (!summaryFilter.isForSummary(sortedRecords)) continue;
            DbBirdRecord firstCatchRecord = getFirstRecord(sortedRecords);
            var population = populationFilter.getPopulation(firstCatchRecord, records);

            ReturningBirdsData returningBirds = resolver.getReturningBirdsData(sortedRecords, population);
            result.add(returningBirds);
        }
        return result;
    }

    private Map<String, List<DbBirdRecord>> getRingNumbers(List<DbBirdRecord> records) {
        return records
                .stream()
                .filter(isNotEmptyRing())
                .collect(Collectors.groupingBy(DbBirdRecord::getRing));
    }

    private Predicate<DbBirdRecord> isNotEmptyRing() {
        return b -> null != b.getRing() && !b
                .getRing()
                .isBlank();
    }

    private DbBirdRecord getFirstRecord(List<DbBirdRecord> sortedRecords) {
        return sortedRecords
                .stream()
                .findFirst()
                .get();
    }

    private List<DbBirdRecord> getSortedRecords(List<DbBirdRecord> ringRecords) {
        return ringRecords
                .stream()
                .sorted(Comparator.comparing(DbBirdRecord::getDate))
                .toList();
    }
}


