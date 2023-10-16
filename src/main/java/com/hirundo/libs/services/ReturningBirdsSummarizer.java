package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;
import com.hirundo.libs.data_structures.Season;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReturningBirdsSummarizer implements IReturningBirdsSummarizer {

    public List<ReturningBirdsData> getSummary(List<DbBirdRecord> records) {
        Map<String, List<DbBirdRecord>> ringNumbers = getRingNumbers(records);

        List<ReturningBirdsData> result = new ArrayList<>();

        for (var ringNumber : ringNumbers.keySet()) {
            var ringRecords = ringNumbers.get(ringNumber);
            List<DbBirdRecord> sortedRecords = getSortedRecords(ringRecords);
            if (!isForSummary(sortedRecords)) continue;
            DbBirdRecord record = getFirstRecord(sortedRecords);
            ReturningBirdsData returningBirds = getReturningBirdsData(ringRecords, record);
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

    private boolean isForSummary(List<DbBirdRecord> sortedRecords) {
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

    boolean isFirstSeasonAutumn(DbBirdRecord record) {
        return Season.Autumn == record.getSeason();
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

    Boolean isJuvenile(DbBirdRecord record) {
        return Objects.equals(record.getAge(), "I") || Objects.equals(record.getAge(), "J");
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

    private ReturningBirdsData getReturningBirdsData(List<DbBirdRecord> ringRecords, DbBirdRecord record) {
        var returningBirds = new ReturningBirdsData();
        returningBirds.RingNumber = record.getRing();
        returningBirds.Species = record.getSpeciesCode();
        returningBirds.Records = ringRecords;
        List<DbBirdRecord> sortedRecords = getSortedRecords(ringRecords);

        DbBirdRecord first = sortedRecords.get(0);
        var last = sortedRecords.get(sortedRecords.size() - 1);

        returningBirds.FirstDateSeen = first.getDate();
        returningBirds.LastDateSeen = last.getDate();

        returningBirds.FirstSeasonSeen = first.getSeason();
        returningBirds.LastSeasonSeen = last.getSeason();

        returningBirds.Pointedness = getPointednessFactor(first);
        returningBirds.Symmetry = getSymmetryFactor(first);

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

    private BigDecimal getPointednessFactor(DbBirdRecord record) {

        if (!isPointednessFactorValid(record)) {
            return null;
        }

        var dSumDecimal = getSum(record);
        var wing = record.getWing();

        return dSumDecimal.divide(wing, 3, RoundingMode.DOWN);

    }

    BigDecimal getSum(DbBirdRecord record) {
        var d2 = record.getD2();
        var d3 = record.getD3();
        var d4 = record.getD4();
        var d5 = record.getD5();
        var d6 = record.getD6();
        var d7 = record.getD7();
        var d8 = record.getD8();

        var dSum = d2 + d3 + d4 + d5 + d6 + d7 + d8;
        return new BigDecimal(dSum);
    }

    private boolean isPointednessFactorValid(DbBirdRecord record) {
        if (null == record.getD2()) return false;
        if (null == record.getD3()) return false;
        if (null == record.getD4()) return false;
        if (null == record.getD5()) return false;
        if (null == record.getD6()) return false;
        if (null == record.getD7()) return false;
        if (null == record.getD8()) return false;
        if (null == record.getWing()) return false;
        return record
                .getWing()
                .compareTo(BigDecimal.ZERO) != 0;
    }

    private BigDecimal getSymmetryFactor(DbBirdRecord record) {
        if (!isPointednessFactorValid(record)) {
            return null;
        }
        if (!isSymmetryFactorValid(record)) {
            return null;
        }

        var array = new BigDecimal[]{new BigDecimal(record.getD2()), new BigDecimal(record.getD3()), new BigDecimal(
                record.getD4()), new BigDecimal(record.getD5()), new BigDecimal(record.getD6()), new BigDecimal(record.getD7()), new BigDecimal(
                record.getD8())};

        var zeroIndex = Arrays
                .asList(array)
                .indexOf(BigDecimal.ZERO);

        var leftSum = Arrays
                .asList(array)
                .subList(0, zeroIndex)
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var rightSum = Arrays
                .asList(array)
                .subList(zeroIndex + 1, array.length)
                .stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        var dSum = getSum(record);

        return (rightSum.subtract(leftSum)).divide(dSum, 3, RoundingMode.DOWN);
    }

    boolean isSymmetryFactorValid(DbBirdRecord record) {
        return 0 == record.getD2() || 0 == record.getD3() || 0 == record.getD4() || 0 == record.getD5() || 0 == record.getD6() || 0 == record.getD7() || 0 == record.getD8();
    }
}


