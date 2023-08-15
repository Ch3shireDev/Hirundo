package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class CsvReturningBirdsDataSerializerTest {

    CsvSerializer<CsvReturningBirdsData> serializer2;

    @BeforeEach
    void setUp() {
        serializer2 = new CsvSerializer<>(CsvReturningBirdsData.class);
    }

    @Test
    public void SerializeBirdData() throws Exception {
        var record = new DbBirdRecord();
        record.ring = "LA94007";
        record.speciesCode = "REG.REG";
        record.date = LocalDateTime.of(1982, 10, 12, 0, 0, 0);
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
        data.RingNumber = "LA94007";
        data.FirstSeasonSeen = Season.Autumn;
        data.LastSeasonSeen = Season.Spring;
        data.Records = List.of(record);

        var mapper = new ReturningBirdsDataCsvRecordMapper();
        var birds = mapper.getCsvReturningBirdsData(List.of(data));
        var result = serializer2.serializeToCsv(birds);

        var expectedResult = """
                RingNumber;Species;FirstDateSeen;LastDateSeen;FirstSeasonSeen;LastSeasonSeen;RecordDate;Sex;Age;Weight;Fat;Wing;Tail;D2;D3;D4;D5;D6;D7;D8\r
                LA94007;REG.REG;1982-10-12;1983-04-01;Autumn;Spring;1982-10-12;M;I;50.500;3;50.500;60;1;2;3;4;5;6;7\r
                                """;
        Assertions.assertEquals(expectedResult, result);
    }
}
