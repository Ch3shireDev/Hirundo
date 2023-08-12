package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.*;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class CsvSerializerTest {
    CsvSerializer<MockCsvBirdData> serializer;
    CsvSerializer<BirdSpecies.CsvReturningBirdsData> serializer2;

    @BeforeEach
    void setUp() {
        serializer = new CsvSerializer<>(MockCsvBirdData.class);
        serializer2 = new CsvSerializer<>(BirdSpecies.CsvReturningBirdsData.class);
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
        var newRecord = new NewDbBirdRecord();

        newRecord.RingNo = "LA94007";
        newRecord.SpeciesCode = "REG.REG";
        newRecord.Date2 = "1982-10-12";
        newRecord.Sex = "M";
        newRecord.Age = "I";
        newRecord.Weight = 50.5000000000001;
        newRecord.Wing = 50.50000000000001;
        newRecord.Tail = 60.50000000000001;
        newRecord.Fat = 3.0;
        newRecord.D2 = 1.0;
        newRecord.D3 = 2.0;
        newRecord.D4 = 3.0;
        newRecord.D5 = 4.0;
        newRecord.D6 = 5.0;
        newRecord.D7 = 6.0;
        newRecord.D8 = 7.0;

        var record = new DbBirdRecord(newRecord);
        var data = new ReturningBirdsData();
        data.Species = "REG.REG";
        data.FirstDateSeen = LocalDateTime.of(1982, 10, 12, 0, 0);
        data.LastDateSeen = LocalDateTime.of(1983, 4, 1, 0, 0);
        data.RingNumber ="LA94007";
        data.FirstSeasonSeen = Season.Autumn;
        data.LastSeasonSeen = Season.Spring;
        data.Records = List.of(record);

        var csv1 = BirdSpecies.CsvReturningBirdsData.from(data, record);

        var birds = List.of(csv1);

        var result = serializer2.serializeToCsv(birds);

        var expectedResult = """
RingNumber;Species;FirstDateSeen;LastDateSeen;FirstSeasonSeen;LastSeasonSeen;RecordDate;Sex;Age;Weight;Fat;Wing;Tail;D2;D3;D4;D5;D6;D7;D8\r
LA94007;REG.REG;1982-10-12;1983-04-01;Autumn;Spring;1982-10-12;M;I;50.500;3;50.500;60;1;2;3;4;5;6;7\r
                """;
        Assertions.assertEquals(expectedResult, result);
    }
}
