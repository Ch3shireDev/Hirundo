package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.CsvReturningBirdsData;
import com.hirundo.libs.data_structures.ReturningBirdsData;

import java.util.ArrayList;
import java.util.List;

public class ReturningBirdsDataCsvRecordMapper implements IReturningBirdsDataCsvRecordMapper {

    @Override
    public ArrayList<CsvReturningBirdsData> getCsvReturningBirdsData(List<ReturningBirdsData> returningBirds) {
        var list = new ArrayList<CsvReturningBirdsData>();

        for (var returningBird : returningBirds) {

            for (var record : returningBird.Records) {

                var csvData = CsvReturningBirdsData.from(returningBird, record);
                list.add(csvData);
            }
        }
        return list;
    }
}
