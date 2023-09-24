package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        r1.age = "I";

        var r2 = new DbBirdRecord();
        r2.speciesCode = "XXX.YYY";
        r2.ring = "1234";
        r2.date = LocalDateTime.of(2020, 3, 1, 0, 0);
        r2.season = Season.Spring;
        r2.age = "I";

        var r3 = new DbBirdRecord();
        r3.speciesCode = "XXX.YYY";
        r3.age = "I";

        var birds = List.of(r1, r2, r3);

        var returningBirds = finder.getSummary(birds);

        assertEquals(1, returningBirds.size());
        assertEquals(2, returningBirds.get(0).Records.size());
        assertEquals("1234", returningBirds.get(0).RingNumber);
        assertEquals("XXX.YYY", returningBirds.get(0).Species);
        assertEquals(LocalDateTime.of(2019, 11, 1, 0, 0), returningBirds.get(0).FirstDateSeen);
        assertEquals(Season.Autumn, returningBirds.get(0).FirstSeasonSeen);
        assertEquals(LocalDateTime.of(2020, 3, 1, 0, 0), returningBirds.get(0).LastDateSeen);
        assertEquals(Season.Spring, returningBirds.get(0).LastSeasonSeen);
    }
}