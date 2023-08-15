package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.CsvReturningBirdsData;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.util.List;

public interface IReturningBirdsDataCsvRecordMapper {
    List<CsvReturningBirdsData> getCsvReturningBirdsData(List<ReturningBirdsData> returningBirds);
}
