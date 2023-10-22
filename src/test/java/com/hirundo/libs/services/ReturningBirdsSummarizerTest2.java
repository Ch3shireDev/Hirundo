//package com.hirundo.libs.services;
//
//import com.hirundo.libs.data_structures.DbBirdRecord;
//import org.jmock.Expectations;
//import org.jmock.Mockery;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class ReturningBirdsSummarizerTest2 {
//
//    ReturningBirdsSummarizer summarizer;
//    private Mockery context = new Mockery();
//    private ISummaryFilter mockSummaryFilter;
//    private IPopulationFilter mockPopulationFilter;
//
//    @BeforeEach
//    void setUp() {
//        mockSummaryFilter = context.mock(ISummaryFilter.class);
//        mockPopulationFilter = context.mock(IPopulationFilter.class);
//        summarizer = new ReturningBirdsSummarizer(mockSummaryFilter, mockPopulationFilter);
//    }
//
//    @Test
//    public void populationShouldBeGivenInResults(){
//        context.checking(new Expectations() {{
//            allowing(mockSummaryFilter).isForSummary(with(any(List.class)));
//            will(returnValue(true));
//        }});
//
//        context.checking(new Expectations() {{
//            allowing(mockPopulationFilter).getPopulation(with(any(DbBirdRecord.class)), with(any(List.class)));
//            will(returnValue(List.of(new DbBirdRecord(), new DbBirdRecord(), new DbBirdRecord())));
//        }});
//
//        var r1 = new DbBirdRecord();
//
//        var result = summarizer.getSummary(List.of(r1));
//
//        context.assertIsSatisfied();
//
//        assertEquals(1, result.size());
//        assertEquals(3, result.get(0).Population);
//    }
//
//}
