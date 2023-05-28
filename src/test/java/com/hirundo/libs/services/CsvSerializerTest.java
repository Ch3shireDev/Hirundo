package com.hirundo.libs.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class CsvSerializerTest {
    CsvSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new CsvSerializer<MockCsvBirdData>(MockCsvBirdData.class);
    }

    @Test
    void serializeToCsv() throws Exception {

        var birds = List.of(new MockCsvBirdData(1, "1234", LocalDateTime.of(2020, 1, 2, 0, 0)),
                            new MockCsvBirdData(2, "1235", LocalDateTime.of(2020, 1, 3, 0, 0)),
                            new MockCsvBirdData(3, "1236", LocalDateTime.of(2020, 1, 4, 0, 0)));

        var result = serializer.serializeToCsv(birds);

        var expectedResult = "Id,Ring,Date\r\n" +
                              "1,1234,2020-01-02\r\n" +
                              "2,1235,2020-01-03\r\n" +
                              "3,1236,2020-01-04\r\n";
        Assertions.assertEquals(expectedResult, result);
    }

}
