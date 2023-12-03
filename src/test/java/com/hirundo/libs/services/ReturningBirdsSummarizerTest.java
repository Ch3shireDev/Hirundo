package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.DbBirdRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturningBirdsSummarizerTest {
    ReturningBirdsSummarizer summarizer;
    private ISummaryFilter summaryFilter;
    private IPopulationFilter populationFilter;

    @BeforeEach
    void setUp() {
        summaryFilter = Mockito.mock(ISummaryFilter.class);
        populationFilter = Mockito.mock(IPopulationFilter.class);
        summarizer = new ReturningBirdsSummarizer(summaryFilter, populationFilter);
    }

    @Test
    public void populationShouldBeGivenInResults() {
        Mockito
                .when(summaryFilter.isReturningBird(ArgumentMatchers.anyList()))
                .thenReturn(true);
        Mockito
                .when(populationFilter.getPopulation(ArgumentMatchers.any(DbBirdRecord.class), ArgumentMatchers.anyList()))
                .thenReturn(List.of(new DbBirdRecord(), new DbBirdRecord(), new DbBirdRecord()));

        var r1 = new DbBirdRecord();
        r1.ring = "123";

        var parameters = new ReturningBirdsSummarizerParameters();
        var result = summarizer.getSummary(List.of(r1), parameters);

        Mockito
                .verify(summaryFilter, Mockito.times(1))
                .isReturningBird(ArgumentMatchers.anyList());

        assertEquals(1, result.size());
        assertEquals(3, result.get(0).Population);
    }

    @Test
    public void givenAnySex_whenFiltered_thenPopulationHasBothSexes(){
        Mockito
                .when(summaryFilter.isReturningBird(ArgumentMatchers.anyList()))
                .thenReturn(true);

        var r1 = new DbBirdRecord();
        r1.ring = "123";
        r1.sex = BirdSex.Male;

        var r2 = new DbBirdRecord();
        r2.ring = "345";
        r2.sex = BirdSex.Female;

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.sex = BirdSex.Any;

        summarizer.getSummary(List.of(r1, r2), parameters);

        Mockito
                .verify(populationFilter, Mockito.atLeastOnce())
                .getPopulation(ArgumentMatchers.any(), ArgumentMatchers.eq(List.of(r1, r2)));
    }

    @Test
    public void givenMaleInParameters_whenFiltered_thenPopulationHasMaleSex(){
        Mockito
                .when(summaryFilter.isReturningBird(ArgumentMatchers.anyList()))
                .thenReturn(true);

        var r1 = new DbBirdRecord();
        r1.ring = "123";
        r1.sex = BirdSex.Male;

        var r2 = new DbBirdRecord();
        r2.ring = "345";
        r2.sex = BirdSex.Female;

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.sex = BirdSex.Male;

        summarizer.getSummary(List.of(r1, r2), parameters);

        Mockito
                .verify(populationFilter, Mockito.atLeastOnce())
                .getPopulation(ArgumentMatchers.any(), ArgumentMatchers.eq(List.of(r1)));
    }

    @Test
    public void givenTimeRangeInParameters_whenFiltered_thenPopulationHasRecordsInRange(){
        Mockito
                .when(summaryFilter.isReturningBird(ArgumentMatchers.anyList()))
                .thenReturn(true);

        var r1 = new DbBirdRecord();
        r1.ring = "123";
        r1.date = LocalDate.parse( "2020-09-01").atStartOfDay();

        var r2 = new DbBirdRecord();
        r2.ring = "345";
        r2.date = LocalDate.parse("2020-09-30").atStartOfDay();

        var r3 = new DbBirdRecord();
        r3.ring = "567";
        r3.date = LocalDate.parse("2020-10-01").atStartOfDay();

        var parameters = new ReturningBirdsSummarizerParameters();
        parameters.useDateRange = true;
        parameters.dateRangeStart = LocalDate.parse("2020-09-01");
        parameters.dateRangeEnd = LocalDate.parse("2020-09-30");

        summarizer.getSummary(List.of(r1, r2, r3), parameters);

        Mockito
                .verify(populationFilter, Mockito.atLeastOnce())
                .getPopulation(ArgumentMatchers.any(), ArgumentMatchers.eq(List.of(r1, r2)));
    }
}

