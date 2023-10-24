package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdAge;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.Season;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ReturningBirdsSummarizerTest {
    ReturningBirdsSummarizer finder;

    @BeforeEach
    void setUp() {
        finder = new ReturningBirdsSummarizer();
    }

    @Nested
    public class SummaryFilterTest {

        SummaryFilter filter;

        @BeforeEach
        void setUp() {
            filter = new SummaryFilter();
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

            var result = filter.isForSummary(List.of(r1, r2));

            assertTrue(result);
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

            var result = filter.isForSummary(List.of(r1, r2));

            assertFalse(result);
        }
    }
}