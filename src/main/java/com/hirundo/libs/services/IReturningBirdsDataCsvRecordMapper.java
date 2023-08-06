package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.util.List;

public interface IReturningBirdsDataCsvRecordMapper {
    List<BirdSpecies.CsvReturningBirdsData> getCsvReturningBirdsData(List<ReturningBirdsData> returningBirds);
}
