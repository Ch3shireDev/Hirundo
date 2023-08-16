package com.hirundo.libs.serializers;

import com.hirundo.libs.data_structures.CsvReturningBirdsData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

public class CsvReturningBirdsDataSerializerTest {

    CsvReturningBirdsDataSerializer serializer;

    @BeforeEach
    void setUp() {
        serializer = new CsvReturningBirdsDataSerializer();
    }

    @Test
    public void SerializeBirdData() throws Exception {
        var bird = new CsvReturningBirdsData();
        bird.RingNumber = "LA94007";
        bird.Species = "REG.REG";
        bird.FirstDateSeen = LocalDateTime.of(1982, 10, 12, 0, 0);
        bird.LastDateSeen = LocalDateTime.of(1983, 4, 1, 0, 0);
        bird.FirstSeasonSeen = "Autumn";
        bird.LastSeasonSeen = "Spring";
        bird.RecordDate = LocalDateTime.of(1982, 10, 12, 0, 0);
        bird.Sex = "M";
        bird.Age = "I";
        bird.Weight = BigDecimal.valueOf(50.500).setScale(3, RoundingMode.HALF_UP);
        bird.Fat = 3;
        bird.Wing = BigDecimal.valueOf(50.500).setScale(3, RoundingMode.HALF_UP);
        bird.Tail = 60;
        bird.D2 = 1;
        bird.D3 = 2;
        bird.D4 = 3;
        bird.D5 = 4;
        bird.D6 = 5;
        bird.D7 = 6;
        bird.D8 = 7;

        var result = serializer.serializeToCsv(List.of(bird));

        var expectedResult = """
                SEP=;\r
                RingNumber;Species;FirstDateSeen;LastDateSeen;FirstSeasonSeen;LastSeasonSeen;RecordDate;Sex;Age;Weight;Fat;Wing;Tail;D2;D3;D4;D5;D6;D7;D8\r
                LA94007;REG.REG;1982-10-12;1983-04-01;Autumn;Spring;1982-10-12;M;I;50.500;3;50.500;60;1;2;3;4;5;6;7\r
                                """;
        Assertions.assertEquals(expectedResult, result);
    }
}
