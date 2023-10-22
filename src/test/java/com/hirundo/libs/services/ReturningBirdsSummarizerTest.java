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

class ReturningBirdsSummarizerTest {
    ReturningBirdsSummarizer finder;

    @BeforeEach
    void setUp() {
        finder = new ReturningBirdsSummarizer();
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

        var returningBirds = finder.getSummary(birds);

        assertEquals(1, returningBirds.size());
        assertEquals("1234", returningBirds.get(0).RingNumber);
        assertEquals("XXX.YYY", returningBirds.get(0).Species);
        assertEquals(LocalDateTime.of(2019, 11, 1, 0, 0), returningBirds.get(0).FirstDateSeen);
        assertEquals(Season.Autumn, returningBirds.get(0).FirstSeasonSeen);
        assertEquals(LocalDateTime.of(2020, 3, 1, 0, 0), returningBirds.get(0).LastDateSeen);
        assertEquals(Season.Spring, returningBirds.get(0).LastSeasonSeen);
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

        var returningBirds = finder.getSummary(birds);

        assertEquals(new BigDecimal("0.500"), returningBirds.get(0).Pointedness);
        assertEquals(new BigDecimal("0.400"), returningBirds.get(0).Symmetry);
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

        var returningBirds = finder.getSummary(birds);

        assertEquals(1, returningBirds.size());
        assertEquals("1234", returningBirds.get(0).RingNumber);
        assertEquals("XXX.YYY", returningBirds.get(0).Species);
        assertEquals(LocalDateTime.of(2019, 11, 1, 0, 0), returningBirds.get(0).FirstDateSeen);
        assertEquals(Season.Autumn, returningBirds.get(0).FirstSeasonSeen);
        assertEquals(LocalDateTime.of(2020, 3, 1, 0, 0), returningBirds.get(0).LastDateSeen);
        assertEquals(Season.Spring, returningBirds.get(0).LastSeasonSeen);
        assertEquals(new BigDecimal("50.500"), returningBirds.get(0).Weight);
        assertEquals(3, returningBirds.get(0).Fat);
        assertEquals(new BigDecimal("60.000"), returningBirds.get(0).Wing);
        assertEquals(new BigDecimal("70"), returningBirds.get(0).Tail);
        assertEquals(2, returningBirds.get(0).D2);
        assertEquals(3, returningBirds.get(0).D3);
        assertEquals(4, returningBirds.get(0).D4);
        assertEquals(0, returningBirds.get(0).D5);
        assertEquals(6, returningBirds.get(0).D6);
        assertEquals(7, returningBirds.get(0).D7);
        assertEquals(8, returningBirds.get(0).D8);
        assertNotEquals(null, returningBirds.get(0).Pointedness);
        assertNotEquals(null, returningBirds.get(0).Symmetry);
    }

    @Test
    void summaryFromJustOneYearShouldBeSkipped() {
        var r1 = new DbBirdRecord();
        r1.speciesCode = "XXX.YYY";
        r1.ring = "1234";
        r1.season = Season.Autumn;
        r1.age = BirdAge.Infantile;
        r1.date = LocalDateTime.of(2019, 11, 1, 0, 0);

        var r2 = new DbBirdRecord();
        r2.speciesCode = "XXX.YYY";
        r2.ring = "1234";
        r2.season = Season.Autumn;
        r2.age = BirdAge.Infantile;
        r2.date = LocalDateTime.of(2019, 12, 1, 0, 0);

        var summary = finder.getSummary(List.of(r1, r2));

        assertEquals(0, summary.size());
    }

    @Test
    void summaryFromTwoYearsShouldNotBeSkipped() {
        var r1 = new DbBirdRecord();
        r1.speciesCode = "XXX.YYY";
        r1.ring = "1234";
        r1.season = Season.Autumn;
        r1.age = BirdAge.Infantile;
        r1.date = LocalDateTime.of(2019, 11, 1, 0, 0);

        var r2 = new DbBirdRecord();
        r2.speciesCode = "XXX.YYY";
        r2.ring = "1234";
        r2.season = Season.Autumn;
        r2.age = BirdAge.Infantile;
        r2.date = LocalDateTime.of(2020, 12, 1, 0, 0);

        var summary = finder.getSummary(List.of(r1, r2));

        assertEquals(1, summary.size());
    }
}

