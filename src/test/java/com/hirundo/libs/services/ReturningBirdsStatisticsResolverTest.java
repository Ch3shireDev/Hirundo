package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdAge;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ReturningBirdsStatisticsResolverTest {
    ReturningBirdsStatisticsResolver resolver;

    @BeforeEach
    void setUp() {
        resolver = new ReturningBirdsStatisticsResolver();
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
    public void wingParametersTranslateToPointednessAndSymmetryFactors() {
        var r1 = new DbBirdRecord();
        r1.speciesCode = "XXX.YYY";
        r1.ring = "1234";
        r1.date = LocalDateTime.of(2019, 11, 1, 0, 0);
        r1.season = Season.Autumn;
        r1.age = BirdAge.Infantile;
        r1.wing = new BigDecimal("60.0");
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

        assertEquals(new BigDecimal("0.500"), result.Pointedness);
        assertEquals(new BigDecimal("0.400"), result.Symmetry);
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
        assertNotEquals(null, result.Pointedness);
        assertNotEquals(null, result.Symmetry);
    }
}
