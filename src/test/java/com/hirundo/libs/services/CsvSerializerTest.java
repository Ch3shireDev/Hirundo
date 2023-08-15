package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.*;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class CsvSerializerTest {
    CsvSerializer<MockCsvBirdData> serializer;
    CsvSerializer<CsvReturningBirdsData> serializer2;

    @BeforeEach
    void setUp() {
        serializer = new CsvSerializer<>(MockCsvBirdData.class);
        serializer2 = new CsvSerializer<>(CsvReturningBirdsData.class);
    }

    @Test
    void serializeToCsv() throws Exception {

        var birds = List.of(new MockCsvBirdData(1, "1234", LocalDateTime.of(2020, 1, 2, 0, 0)),
                            new MockCsvBirdData(2, "1235", LocalDateTime.of(2020, 1, 3, 0, 0)),
                            new MockCsvBirdData(3, "1236", LocalDateTime.of(2020, 1, 4, 0, 0)));

        var result = serializer.serializeToCsv(birds);

        var expectedResult = """
                Id;Ring;Date\r
                1;1234;2020-01-02\r
                2;1235;2020-01-03\r
                3;1236;2020-01-04\r
                """;
        Assertions.assertEquals(expectedResult, result);
    }

    public static class MockCsvBirdData {
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

    @Test
    public void SerializeBirdData() throws Exception{
        var record = new DbBirdRecord();
        record.ring = "LA94007";
        record.speciesCode = "REG.REG";
        record.date = LocalDateTime.of(1982,10,12,0,0,0);
        record.sex = BirdSex.Male;
        record.age = "I";
        record.weight = BigDecimal.valueOf(50.5);
        record.wing = BigDecimal.valueOf(50.5);
        record.tail = 60;
        record.fat = 3;
        record.d2 = 1;
        record.d3 = 2;
        record.d4 = 3;
        record.d5 = 4;
        record.d6 = 5;
        record.d7 = 6;
        record.d8 = 7;

        var data = new ReturningBirdsData();
        data.Species = "REG.REG";
        data.FirstDateSeen = LocalDateTime.of(1982, 10, 12, 0, 0);
        data.LastDateSeen = LocalDateTime.of(1983, 4, 1, 0, 0);
        data.RingNumber ="LA94007";
        data.FirstSeasonSeen = Season.Autumn;
        data.LastSeasonSeen = Season.Spring;
        data.Records = List.of(record);

        var csv1 = CsvReturningBirdsData.from(data, record);

        var birds = List.of(csv1);

        var result = serializer2.serializeToCsv(birds);

        var expectedResult = """
RingNumber;Species;FirstDateSeen;LastDateSeen;FirstSeasonSeen;LastSeasonSeen;RecordDate;Sex;Age;Weight;Fat;Wing;Tail;D2;D3;D4;D5;D6;D7;D8\r
LA94007;REG.REG;1982-10-12;1983-04-01;Autumn;Spring;1982-10-12;M;I;50.500;3;50.500;60;1;2;3;4;5;6;7\r
                """;
        Assertions.assertEquals(expectedResult, result);
    }
}
