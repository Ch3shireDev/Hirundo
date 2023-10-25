package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReturningBirdsSummarizerTest {
    ReturningBirdsSummarizer summarizer;
    private ISummaryFilter mockSummaryFilter;
    private IPopulationFilter mockPopulationFilter;

    @BeforeEach
    void setUp() {
        mockSummaryFilter = Mockito.mock(ISummaryFilter.class);
        mockPopulationFilter = Mockito.mock(IPopulationFilter.class);
        summarizer = new ReturningBirdsSummarizer(mockSummaryFilter, mockPopulationFilter);
    }

    @Test
    public void populationShouldBeGivenInResults() {
        Mockito
                .when(mockSummaryFilter.isForSummary(ArgumentMatchers.anyList()))
                .thenReturn(true);
        Mockito
                .when(mockPopulationFilter.getPopulation(ArgumentMatchers.any(DbBirdRecord.class), ArgumentMatchers.anyList()))
                .thenReturn(List.of(new DbBirdRecord(), new DbBirdRecord(), new DbBirdRecord()));

        var r1 = new DbBirdRecord();
        r1.ring = "123";

        var result = summarizer.getSummary(List.of(r1));

        Mockito
                .verify(mockSummaryFilter, Mockito.times(1))
                .isForSummary(ArgumentMatchers.anyList());

        assertEquals(1, result.size());
        assertEquals(3, result.get(0).Population);
    }
}
