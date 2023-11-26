package com.hirundo.libs.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Objects;

public class StatisticsCalculator implements IStatisticsCalculator {

    public BigDecimal calculateMedian(BigDecimal[] values) {

        values = Arrays
                .stream(values)
                .filter(Objects::nonNull)
                .toArray(BigDecimal[]::new);

        if (0 == values.length) return null;

        Arrays.sort(values);

        if (0 == values.length % 2) return (values[values.length / 2].add(values[values.length / 2 - 1])).divide(BigDecimal.valueOf(2), 3, RoundingMode.DOWN);
        else return values[values.length / 2];
    }

    public BigDecimal calculateStandardDeviation(BigDecimal[] values) {

        values = Arrays
                .stream(values)
                .filter(Objects::nonNull)
                .toArray(BigDecimal[]::new);

        if (0 == values.length) return null;

        var mean = Arrays
                .stream(values)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(values.length), 3, RoundingMode.DOWN);

        var squaredDifferences = Arrays
                .stream(values)
                .map(value -> value
                        .subtract(mean)
                        .pow(2))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return BigDecimal.valueOf(Math.sqrt(squaredDifferences.doubleValue() / values.length));
    }

    public BigDecimal calculateMean(BigDecimal[] values) {
        values = Arrays
                .stream(values)
                .filter(Objects::nonNull)
                .toArray(BigDecimal[]::new);

        if (0 == values.length) return null;

        return Arrays
                .stream(values)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(values.length), 3, RoundingMode.DOWN);
    }

    public BigDecimal calculateLowerQuartile(BigDecimal[] values) {
        values = Arrays
                .stream(values)
                .filter(Objects::nonNull)
                .toArray(BigDecimal[]::new);

        if (0 == values.length) return null;

        var median = calculateMedian(values);

        var lowerValues = Arrays
                .stream(values)
                .filter(value -> 0 > value.compareTo(median))
                .toArray(BigDecimal[]::new);

        return calculateMedian(lowerValues);
    }

    public BigDecimal calculateUpperQuartile(BigDecimal[] values) {
        values = Arrays
                .stream(values)
                .filter(Objects::nonNull)
                .toArray(BigDecimal[]::new);

        if (0 == values.length) return null;

        var median = calculateMedian(values);

        var upperValues = Arrays
                .stream(values)
                .filter(value -> 0 < value.compareTo(median))
                .toArray(BigDecimal[]::new);

        return calculateMedian(upperValues);
    }

}
