package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdAge;
import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class ReturningBirdsStatisticsResolverTest {
    IWingParametersCalculator wingParametersCalculator;
    IStatisticsCalculator statisticsCalculator;
    ReturningBirdsStatisticsResolver resolver;

    @BeforeEach
    void setUp() {
        wingParametersCalculator = Mockito.mock(IWingParametersCalculator.class);
        statisticsCalculator = Mockito.mock(IStatisticsCalculator.class);
        resolver = new ReturningBirdsStatisticsResolver(wingParametersCalculator, statisticsCalculator);
    }

    @Test
    public void returnsCountDifferentSeasonTest() {
        var r1 = new DbBirdRecord();
        r1.speciesCode = "XXX.YYY";
        r1.ring = "1234";
        r1.date = LocalDateTime.of(2019, 11, 1, 0, 0);
        r1.season = Season.Autumn;
        r1.age = BirdAge.Infantile;

        var r2 = new DbBirdRecord();
        r2.speciesCode = "XXX.YYY";
        r2.ring = "1234";
        r2.date = LocalDateTime.of(2020, 3, 1, 0, 0);
        r2.season = Season.Spring;
        r2.age = BirdAge.Infantile;

        var r3 = new DbBirdRecord();
        r3.speciesCode = "XXX.YYY";
        r3.age = BirdAge.Infantile;

        var birds = List.of(r1, r2, r3);

        var result = resolver.getReturningBirdsData(List.of(r1, r2), birds);


        assertEquals("1234", result.RingNumber);
        assertEquals("XXX.YYY", result.Species);
        assertEquals(LocalDateTime.of(2019, 11, 1, 0, 0), result.FirstDateSeen);
        assertEquals(Season.Autumn, result.FirstSeasonSeen);
        assertEquals(LocalDateTime.of(2020, 3, 1, 0, 0), result.LastDateSeen);
        assertEquals(Season.Spring, result.LastSeasonSeen);
    }

    @Test
    public void allDataShouldBeContainedInSummary() {
        var r1 = new DbBirdRecord();
        r1.speciesCode = "XXX.YYY";
        r1.ring = "1234";
        r1.date = LocalDateTime.of(2019, 11, 1, 0, 0);
        r1.season = Season.Autumn;
        r1.age = BirdAge.Infantile;
        r1.weight = new BigDecimal("50.500");
        r1.fat = 3;
        r1.wing = new BigDecimal("60.000");
        r1.tail = new BigDecimal("70");
        r1.d2 = 2;
        r1.d3 = 3;
        r1.d4 = 4;
        r1.d5 = 0;
        r1.d6 = 6;
        r1.d7 = 7;
        r1.d8 = 8;

        var r2 = new DbBirdRecord();
        r2.speciesCode = "XXX.YYY";
        r2.ring = "1234";
        r2.date = LocalDateTime.of(2020, 3, 1, 0, 0);
        r2.season = Season.Spring;
        r2.age = BirdAge.Infantile;

        var birds = List.of(r1, r2);

        var result = resolver.getReturningBirdsData(birds, birds);

        assertEquals("1234", result.RingNumber);
        assertEquals("XXX.YYY", result.Species);
        assertEquals(LocalDateTime.of(2019, 11, 1, 0, 0), result.FirstDateSeen);
        assertEquals(Season.Autumn, result.FirstSeasonSeen);
        assertEquals(LocalDateTime.of(2020, 3, 1, 0, 0), result.LastDateSeen);
        assertEquals(Season.Spring, result.LastSeasonSeen);
        assertEquals(new BigDecimal("50.500"), result.Weight);
        assertEquals(3, result.Fat);
        assertEquals(new BigDecimal("60.000"), result.Wing);
        assertEquals(new BigDecimal("70"), result.Tail);
        assertEquals(2, result.D2);
        assertEquals(3, result.D3);
        assertEquals(4, result.D4);
        assertEquals(0, result.D5);
        assertEquals(6, result.D6);
        assertEquals(7, result.D7);
        assertEquals(8, result.D8);
    }

    @Test
    public void PointednessShouldBeIncluded(){
        var r1 = new DbBirdRecord();
        Mockito.when(wingParametersCalculator.getPointednessFactor(any())).thenReturn(new BigDecimal("10.000"));
        Mockito.when(statisticsCalculator.calculateMean(any())).thenReturn(new BigDecimal("15.000"));
        Mockito.when(statisticsCalculator.calculateStandardDeviation(any())).thenReturn(new BigDecimal("5.000"));

        var result = resolver.getReturningBirdsData(List.of(r1), List.of(r1));

        assertEquals(new BigDecimal("10.000"), result.Pointedness);
        assertEquals(new BigDecimal("15.000"), result.PointednessMean);
        assertEquals(new BigDecimal("5.000"), result.PointednessStandardDeviation);
    }

    @Test
    public void SymmetryShouldBeIncluded(){
        var r1 = new DbBirdRecord();
        Mockito.when(wingParametersCalculator.getSymmetryFactor(any())).thenReturn(new BigDecimal("20.000"));
        Mockito.when(statisticsCalculator.calculateMean(any())).thenReturn(new BigDecimal("25.000"));
        Mockito.when(statisticsCalculator.calculateStandardDeviation(any())).thenReturn(new BigDecimal("15.000"));

        var result = resolver.getReturningBirdsData(List.of(r1), List.of(r1));

        assertEquals(new BigDecimal("20.000"), result.Symmetry);
        assertEquals(new BigDecimal("25.000"), result.SymmetryMean);
        assertEquals(new BigDecimal("15.000"), result.SymmetryStandardDeviation);
    }

    @Test
    public void WeightShouldBeIncluded(){
        var r1 = new DbBirdRecord();
        r1.weight = new BigDecimal("30.000");

        Mockito.when(statisticsCalculator.calculateMean(any())).thenReturn(new BigDecimal("40.000"));
        Mockito.when(statisticsCalculator.calculateStandardDeviation(any())).thenReturn(new BigDecimal("10.000"));

        var result = resolver.getReturningBirdsData(List.of(r1), List.of(r1));

        assertEquals(new BigDecimal("30.000"), result.Weight);
        assertEquals(new BigDecimal("40.000"), result.WeightMean);
        assertEquals(new BigDecimal("10.000"), result.WeightStandardDeviation);
    }

    @Test
    public void FatShouldBeIncluded(){
        var r1 = new DbBirdRecord();
        r1.fat = 3;

        Mockito.when(statisticsCalculator.calculateMedian(any())).thenReturn(new BigDecimal("4.000"));
        Mockito.when(statisticsCalculator.calculateUpperQuartile(any())).thenReturn(new BigDecimal("5.000"));
        Mockito.when(statisticsCalculator.calculateLowerQuartile(any())).thenReturn(new BigDecimal("3.000"));

        var result = resolver.getReturningBirdsData(List.of(r1), List.of(r1));

        assertEquals(3, result.Fat);
        assertEquals(new BigDecimal("4.000"), result.FatMedian);
        assertEquals(new BigDecimal("5.000"), result.FatUpperQuartile);
        assertEquals(new BigDecimal("3.000"), result.FatLowerQuartile);
    }

    @Test
    public void WingShouldBeIncluded(){
        var r1 = new DbBirdRecord();
        r1.wing = new BigDecimal("30.000");

        Mockito.when(statisticsCalculator.calculateMean(any())).thenReturn(new BigDecimal("40.000"));
        Mockito.when(statisticsCalculator.calculateStandardDeviation(any())).thenReturn(new BigDecimal("10.000"));

        var result = resolver.getReturningBirdsData(List.of(r1), List.of(r1));

        assertEquals(new BigDecimal("30.000"), result.Wing);
        assertEquals(new BigDecimal("40.000"), result.WingMean);
        assertEquals(new BigDecimal("10.000"), result.WingStandardDeviation);
    }

    @Test
    public void TailShouldBeIncluded(){
        var r1 = new DbBirdRecord();
        r1.tail = new BigDecimal("35.000");

        Mockito.when(statisticsCalculator.calculateMean(any())).thenReturn(new BigDecimal("45.000"));
        Mockito.when(statisticsCalculator.calculateStandardDeviation(any())).thenReturn(new BigDecimal("15.000"));

        var result = resolver.getReturningBirdsData(List.of(r1), List.of(r1));

        assertEquals(new BigDecimal("35.000"), result.Tail);
        assertEquals(new BigDecimal("45.000"), result.TailMean);
        assertEquals(new BigDecimal("15.000"), result.TailStandardDeviation);
    }

    @Test
    public void sexShouldBeIncluded(){
        var r1 = new DbBirdRecord();
        r1.sex = BirdSex.Male;

        var result = resolver.getReturningBirdsData(List.of(r1), List.of(r1));

        assertEquals(BirdSex.Male, result.Sex);
    }

    @Test
    public void ageShouldBeIncluded(){
        var r1 = new DbBirdRecord();
        r1.age = BirdAge.Juvenile;

        var result = resolver.getReturningBirdsData(List.of(r1), List.of(r1));

        assertEquals(BirdAge.Juvenile, result.Age);
    }

    @Test
    public void numberOfRecordsShouldBeIncluded(){
        var r1 = new DbBirdRecord();
        var r2 = new DbBirdRecord();
        var r3 = new DbBirdRecord();

        var p1 = new DbBirdRecord();
        var p2 = new DbBirdRecord();
        var p3 = new DbBirdRecord();
        var p4 = new DbBirdRecord();

        var result = resolver.getReturningBirdsData(List.of(r1, r2, r3), List.of(p1,p2,p3,p4));

        assertEquals(3, result.RecordsCount);
        assertEquals(4, result.Population);
    }
}

