package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.ReturningBirdsData;

public class CsvReturningBirdsDataSerializer extends CsvSerializer<ReturningBirdsData> {
    public CsvReturningBirdsDataSerializer() {
        super(ReturningBirdsData.class);
    }
}
