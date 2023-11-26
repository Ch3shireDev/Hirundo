package com.hirundo.mockups;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;
import com.hirundo.libs.services.IReturningBirdsSummarizer;
import com.hirundo.libs.services.ReturningBirdsSummarizerParameters;

import java.util.ArrayList;
import java.util.List;

public class MockReturningBirdsSummarizer implements IReturningBirdsSummarizer {
    public boolean isCreateSummaryCalled;
    public List<ReturningBirdsData> summary = new ArrayList<>();
    public List<DbBirdRecord> inputData;

    @Override
    public List<ReturningBirdsData> getSummary(List<DbBirdRecord> records, ReturningBirdsSummarizerParameters parameters) {
        isCreateSummaryCalled = true;
        inputData = records;
        return summary;
    }
}
