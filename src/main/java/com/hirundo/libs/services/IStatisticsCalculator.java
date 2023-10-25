package com.hirundo.libs.services;

import java.math.BigDecimal;

public interface IStatisticsCalculator {

    BigDecimal calculateMean(BigDecimal[] values);
    BigDecimal calculateStandardDeviation(BigDecimal[] values);

    BigDecimal calculateMedian(BigDecimal[] values);

    BigDecimal calculateLowerQuartile(BigDecimal[] values);

    BigDecimal calculateUpperQuartile(BigDecimal[] values);
}
