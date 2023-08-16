package com.hirundo.libs.serializers;

import com.hirundo.libs.data_structures.CsvReturningBirdsData;

public class CsvReturningBirdsDataSerializer extends CsvSerializer<CsvReturningBirdsData> {
    public CsvReturningBirdsDataSerializer() {
        super(CsvReturningBirdsData.class);
    }

    public String serializeToCsv(java.util.List<CsvReturningBirdsData> birdDataList) throws Exception {
        return super.serializeToCsv(birdDataList);
    }
}
