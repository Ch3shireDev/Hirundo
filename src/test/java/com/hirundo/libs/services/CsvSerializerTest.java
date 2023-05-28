package com.hirundo.libs.services;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class CsvSerializerTest {
    CsvSerializer serializer;

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

        var expectedResult = "\"Id\",\"Ring\",\"Date\"\n" +
                              "1,1234,2020-01-02\n" +
                              "2,1235,2020-01-03\n" +
                              "3,1236,2020-01-04\n";
        Assertions.assertEquals(expectedResult, result);
    }

    public class MockCsvBirdData {
        @CsvBindByName(column = "Id")
        @CsvBindByPosition(position = 0)
        public Integer Id;
        @CsvBindByName(column = "Ring")
        @CsvBindByPosition(position = 1)
        public String Ring;
        @CsvBindByName(column = "Date")
        @CsvBindByPosition(position = 2)
        @CsvDate("yyyy-MM-dd")
        public LocalDateTime Date;

        public MockCsvBirdData(Integer id, String ring, LocalDateTime date) {
            Id = id;
            Ring = ring;
            Date = date;
        }
    }
}