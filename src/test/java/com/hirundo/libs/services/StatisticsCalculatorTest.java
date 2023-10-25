package com.hirundo.libs.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatisticsCalculatorTest {

    StatisticsCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new StatisticsCalculator();
    }

    @Test
    public void givenOneValue_whenCalculateMedian_thenResultEqualsValue() {
        var values = new BigDecimal[]{BigDecimal.valueOf(1)};

        var result = calculator.calculateMedian(values);

        assertEquals(BigDecimal.valueOf(1), result);
    }

    @Test
    public void givenTwoValues_whenCalculateMedian_thenResultEqualsAverage() {
        var values = new BigDecimal[]{BigDecimal.valueOf(1), BigDecimal.valueOf(2)};

        var result = calculator.calculateMedian(values);

        assertEquals(new BigDecimal("1.500"), result);
    }

    @Test
    public void givenThreeValues_whenCalculateMedian_thenResultEqualsMiddleValue() {
        var values = new BigDecimal[]{BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        var result = calculator.calculateMedian(values);

        assertEquals(BigDecimal.valueOf(2), result);
    }

    @Test
    public void givenNullValues_whenCalculateMedian_ignoreNullValues(){
        var values = new BigDecimal[]{BigDecimal.valueOf(1), null, BigDecimal.valueOf(3)};

        var result = calculator.calculateMedian(values);

        assertEquals(new BigDecimal("2.000"), result);
    }

    @Test
    public void givenOnlyNullValues_whenCalculateMedian_returnZero(){
        var values = new BigDecimal[]{null, null, null};

        var result = calculator.calculateMedian(values);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void givenOneValue_whenCalculateStandardDeviation_thenResultEqualsZero() {
        var values = new BigDecimal[]{BigDecimal.valueOf(1)};

        var result = calculator.calculateStandardDeviation(values);

        assertEquals(new BigDecimal("0.0"), result);
    }

    @Test
    public void givenTwoValues_whenCalculateStandardDeviation_thenResultEqualsZero() {
        var values = new BigDecimal[]{
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(4),
                BigDecimal.valueOf(4),
                BigDecimal.valueOf(4),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(7),
                BigDecimal.valueOf(9),
        };

        var result = calculator.calculateStandardDeviation(values);

        assertEquals(new BigDecimal("2.0"), result);
    }

    @Test
    public void givenTwoValues_whenCalculateStandardDeviation_thenResultIsSquares(){
        var values = new BigDecimal[]{
          BigDecimal.valueOf(0),
          BigDecimal.valueOf(4),
        };

        var result = calculator.calculateStandardDeviation(values);

        assertEquals(new BigDecimal("2.0"), result);
    }

    @Test
    public void givenNullValues_whenCalculateStandardDeviation_thenResultIsSquares(){
        var values = new BigDecimal[]{
          BigDecimal.valueOf(0),
          BigDecimal.valueOf(4),
          null,
        };

        var result = calculator.calculateStandardDeviation(values);

        assertEquals(new BigDecimal("2.0"), result);
    }

    @Test
    public void givenOnlyNullValues_whenCalculateStandardDeviation_thenResultIsZero(){
        var values = new BigDecimal[]{
          null,
          null,
          null,
        };

        var result = calculator.calculateStandardDeviation(values);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void givenOneValue_whenCalculateMean_thenResultEqualsValue() {
        var values = new BigDecimal[]{BigDecimal.valueOf(1)};

        var result = calculator.calculateMean(values);

        assertEquals(new BigDecimal("1.000"), result);
    }

    @Test
    public void givenTwoValues_whenCalculateMean_thenResultEqualsAverage() {
        var values = new BigDecimal[]{BigDecimal.valueOf(1), BigDecimal.valueOf(2)};

        var result = calculator.calculateMean(values);

        assertEquals(new BigDecimal("1.500"), result);
    }

    @Test
    public void givenNullValues_whenCalculateMean_ignoreNullValues(){
        var values = new BigDecimal[]{BigDecimal.valueOf(1), null, BigDecimal.valueOf(3)};

        var result = calculator.calculateMean(values);

        assertEquals(new BigDecimal("2.000"), result);
    }

    @Test
    public void givenOnlyNullValues_whenCalculateMean_returnZero(){
        var values = new BigDecimal[]{null, null, null};

        var result = calculator.calculateMean(values);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    public void GivenThreeValues_whenCalculateLowerQuartile_thenResultEqualsLowerValue() {
        var values = new BigDecimal[]{BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        var result = calculator.calculateLowerQuartile(values);

        assertEquals(BigDecimal.valueOf(1), result);
    }

    @Test
    public void GivenThreeValues_whenCalculateUpperQuartile_thenResultEqualsMiddleValue() {
        var values = new BigDecimal[]{BigDecimal.valueOf(1), BigDecimal.valueOf(2), BigDecimal.valueOf(3)};

        var result = calculator.calculateUpperQuartile(values);

        assertEquals(BigDecimal.valueOf(3), result);
    }

    @Test
    public void GivenElevenValues_whenCalculateUpperQuartile_thenResultEqualsNinthValue(){
        var values = new BigDecimal[]{
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(6),
                BigDecimal.valueOf(7),
                BigDecimal.valueOf(9),
                BigDecimal.valueOf(12),
                BigDecimal.valueOf(15),
                BigDecimal.valueOf(18),
                BigDecimal.valueOf(19),
                BigDecimal.valueOf(27),
        };

        var result = calculator.calculateUpperQuartile(values);

        assertEquals(BigDecimal.valueOf(18), result);
    }
    @Test
    public void GivenElevenValues_whenCalculateLowerQuartile_thenResultEqualsNinthValue(){
        var values = new BigDecimal[]{
                BigDecimal.valueOf(1),
                BigDecimal.valueOf(2),
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(6),
                BigDecimal.valueOf(7),
                BigDecimal.valueOf(9),
                BigDecimal.valueOf(12),
                BigDecimal.valueOf(15),
                BigDecimal.valueOf(18),
                BigDecimal.valueOf(19),
                BigDecimal.valueOf(27),
        };

        var result = calculator.calculateLowerQuartile(values);

        assertEquals(BigDecimal.valueOf(5), result);
    }

    @Test
    public void GivenTwelveValues_whenCalculateLowerQuartile_thenResultEqualsLowerQuartile(){
        //  19, 20, 21, 23, 23, 24, 25, 27, 31
        var values = new BigDecimal[]{
                BigDecimal.valueOf(19),
                BigDecimal.valueOf(21),
                BigDecimal.valueOf(23),
                BigDecimal.valueOf(20),
                BigDecimal.valueOf(23),
                BigDecimal.valueOf(27),
                BigDecimal.valueOf(25),
                BigDecimal.valueOf(24),
                BigDecimal.valueOf(31),
        };

        var result = calculator.calculateLowerQuartile(values);

        assertEquals(BigDecimal.valueOf(20), result);
    }
    @Test
    public void GivenTwelveValues_whenCalculateUpperQuartile_thenResultEqualsUpperQuartile(){
        //  19, 21, 23, 20, 23, 27, 25, 24, 31
        var values = new BigDecimal[]{
                BigDecimal.valueOf(19),
                BigDecimal.valueOf(20),
                BigDecimal.valueOf(21),
                BigDecimal.valueOf(23),
                BigDecimal.valueOf(23),
                BigDecimal.valueOf(24),
                BigDecimal.valueOf(25),
                BigDecimal.valueOf(27),
                BigDecimal.valueOf(31),
        };

        var result = calculator.calculateUpperQuartile(values);

        assertEquals(new BigDecimal("26.000"), result);
    }


}
