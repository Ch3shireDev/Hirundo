package com.hirundo.libs.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class CsvSerializerTest {
    CsvSerializer<MockCsvBirdData> serializer;

    @BeforeEach
    void setUp() {
        serializer = new CsvSerializer<>(MockCsvBirdData.class);
    }

    @Test
    void serializeToCsv() throws Exception {

        var birds = List.of(new MockCsvBirdData(1, "1234", LocalDateTime.of(2020, 1, 2, 0, 0)),
                            new MockCsvBirdData(2, "1235", LocalDateTime.of(2020, 1, 3, 0, 0)),
                            new MockCsvBirdData(3, "1236", LocalDateTime.of(2020, 1, 4, 0, 0)));

        var result = serializer.serializeToCsv(birds);

        var expectedResult = """
                Id,Ring,Date\r
                1,1234,2020-01-02\r
                2,1235,2020-01-03\r
                3,1236,2020-01-04\r
                """;
        Assertions.assertEquals(expectedResult, result);
    }

}
