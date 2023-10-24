package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class ReturningBirdsStatisticsResolver {

    public ReturningBirdsData getReturningBirdsData(List<DbBirdRecord> sortedRecords, List<DbBirdRecord> population) {
        var returningBirds = new ReturningBirdsData();

        DbBirdRecord first = sortedRecords.get(0);
        var last = sortedRecords.get(sortedRecords.size() - 1);

        returningBirds.RingNumber = first.getRing();
        returningBirds.Species = first.getSpeciesCode();
        returningBirds.Population = population.size();

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

        if (isPointednessFactorNotValid(record)) {
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

    private boolean isPointednessFactorNotValid(DbBirdRecord record) {
        if (null == record.getD2()) return true;
        if (null == record.getD3()) return true;
        if (null == record.getD4()) return true;
        if (null == record.getD5()) return true;
        if (null == record.getD6()) return true;
        if (null == record.getD7()) return true;
        if (null == record.getD8()) return true;
        if (null == record.getWing()) return true;
        return record
                .getWing()
                .compareTo(BigDecimal.ZERO) == 0;
    }

    private BigDecimal getSymmetryFactor(DbBirdRecord record) {
        if (isPointednessFactorNotValid(record)) {
            return null;
        }
        if (!isSymmetryFactorValid(record)) {
            return null;
        }

        var array = new BigDecimal[]{new BigDecimal(record.getD2()), new BigDecimal(record.getD3()), new BigDecimal(
                record.getD4()), new BigDecimal(record.getD5()), new BigDecimal(record.getD6()), new BigDecimal(record.getD7()),
                new BigDecimal(record.getD8())};

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
