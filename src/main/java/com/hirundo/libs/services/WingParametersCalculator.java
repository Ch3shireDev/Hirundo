package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

public class WingParametersCalculator implements IWingParametersCalculator {
    public BigDecimal getPointednessFactor(DbBirdRecord record) {
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

    public BigDecimal getSymmetryFactor(DbBirdRecord record) {
        if (isPointednessFactorNotValid(record)) {
            return null;
        }
        if (!isSymmetryFactorValid(record)) {
            return null;
        }

        var array = new BigDecimal[]{new BigDecimal(record.getD2()), new BigDecimal(record.getD3()), new BigDecimal(record.getD4()), new BigDecimal(record.getD5()), new BigDecimal(record.getD6()), new BigDecimal(record.getD7()), new BigDecimal(
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

    private boolean isSymmetryFactorValid(DbBirdRecord record) {
        return 0 == record.getD2() || 0 == record.getD3() || 0 == record.getD4() || 0 == record.getD5() || 0 == record.getD6() || 0 == record.getD7() || 0 == record.getD8();
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
        return 0 == record
                .getWing()
                .compareTo(BigDecimal.ZERO);
    }

}
