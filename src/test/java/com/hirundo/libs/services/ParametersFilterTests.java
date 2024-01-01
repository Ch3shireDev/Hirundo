package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.DbBirdRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParametersFilterTests {
    private ParametersFilter filter;

    @BeforeEach
    void setUp() {
        filter = new ParametersFilter();
    }

    @Test
    public void givenMale_whenFilterWithAnySex_thenFiltersTrue(){
        var r1 = new DbBirdRecord();
        r1.sex = BirdSex.Male;

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.sex = BirdSex.Any;

        var result = filter.filter(r1, parameters);

        assertTrue(result);
    }

    @Test
    public void givenFemale_whenFilterWithAnySex_thenFiltersTrue(){
        var r1 = new DbBirdRecord();
        r1.sex = BirdSex.Female;

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.sex = BirdSex.Any;

        var result = filter.filter(r1, parameters);

        assertTrue(result);
    }

    @Test
    public void givenMale_whenFilteredWithMale_thenFilterReturnsTrue(){
        var r1 = new DbBirdRecord();
        r1.sex = BirdSex.Male;

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.sex  = BirdSex.Male;

        var result = filter.filter(r1, parameters);

        assertTrue(result);
    }

    @Test
    public void givenFemale_whenFilteredWithMale_thenFilterReturnsFalse(){
        var r1 = new DbBirdRecord();
        r1.sex = BirdSex.Female;

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.sex  = BirdSex.Male;

        var result = filter.filter(r1, parameters);

        assertFalse(result);
    }

    @Test
    public void givenBirdFromTimeRange_whenFilteredWithTimeRange_thenFilterReturnsTrue(){
        var r1 = new DbBirdRecord();
        r1.ring = "123";
        r1.date = LocalDate
                .parse("2020-09-01")
                .atStartOfDay();

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.useDateRange = true;
        parameters.dateRangeStart = LocalDate.parse("2020-09-01");
        parameters.dateRangeEnd = LocalDate.parse("2020-09-30");

        var result = filter.filter(r1, parameters);

        assertTrue(result);
    }

    @Test
    public void givenBirdFromOutsideTheTimeRange_whenFilteredWithTimeRange_thenFilterReturnsFalse(){
        var r1 = new DbBirdRecord();
        r1.ring = "123";
        r1.date = LocalDate
                .parse("2020-10-01")
                .atStartOfDay();

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.useDateRange = true;
        parameters.dateRangeStart = LocalDate.parse("2020-09-01");
        parameters.dateRangeEnd = LocalDate.parse("2020-09-30");

        var result = filter.filter(r1, parameters);

        assertFalse(result);
    }

    @Test
    public void givenBirdFromTheTimeRangeButDifferentYear_whenFilteredWithTimeRange_thenFilterReturnsTrue(){
        var r1 = new DbBirdRecord();
        r1.ring = "123";
        r1.date = LocalDate
                .parse("2020-09-15")
                .atStartOfDay();

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.useDateRange = true;
        parameters.dateRangeStart = LocalDate.parse("2019-09-01");
        parameters.dateRangeEnd = LocalDate.parse("2019-09-30");

        var result = filter.filter(r1, parameters);

        assertTrue(result);
    }

    @Test
    public void givenBirdFromOutsideTheTimeRangeButDifferentYear_whenFilteredWithTimeRange_thenFilterReturnsTrue(){
        var r1 = new DbBirdRecord();
        r1.ring = "123";
        r1.date = LocalDate
                .parse("2020-10-15")
                .atStartOfDay();

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.useDateRange = true;
        parameters.dateRangeStart = LocalDate.parse("2019-09-01");
        parameters.dateRangeEnd = LocalDate.parse("2019-09-30");

        var result = filter.filter(r1, parameters);

        assertFalse(result);
    }

    @Test
    public void givenBirdFromAugust_whenParametersAreFromJulyToNovember_thenFilterReturnsTrue(){
        var r1 = new DbBirdRecord();
        r1.ring = "123";
        r1.date = LocalDate
                .parse("2020-08-16")
                .atStartOfDay();

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.useDateRange = true;
        parameters.dateRangeStart = LocalDate.parse("2020-07-15");
        parameters.dateRangeEnd = LocalDate.parse("2020-11-15");

        var result = filter.filter(r1, parameters);

        assertTrue(result);
    }
}
