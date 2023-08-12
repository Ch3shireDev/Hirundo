package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.util.ArrayList;
import java.util.List;

public class ReturningBirdsDataCsvRecordMapper implements IReturningBirdsDataCsvRecordMapper {

    @Override
    public ArrayList<BirdSpecies.CsvReturningBirdsData> getCsvReturningBirdsData(List<ReturningBirdsData> returningBirds) {
        var list = new ArrayList<BirdSpecies.CsvReturningBirdsData>();

        for (var returningBird : returningBirds) {

            for (var record : returningBird.Records) {

                var csvData = BirdSpecies.CsvReturningBirdsData.from(returningBird, record);
                list.add(csvData);
            }
        }
        return list;
    }
}
