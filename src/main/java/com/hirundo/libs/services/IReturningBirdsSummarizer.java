package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.util.List;

public interface IReturningBirdsSummarizer {
    List<ReturningBirdsData> getSummary(List<DbBirdRecord> records);
}
