package com.hirundo.libs.mappers;

import com.hirundo.libs.data_structures.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

class ReturningBirdsDataCsvRecordMapperTest {

    ReturningBirdsDataCsvRecordMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ReturningBirdsDataCsvRecordMapper();
    }

    @Test
    void getCsvReturningBirdsData() {
        var newRecord = new NewDbBirdRecord();
        newRecord.Date2 = "2020-01-02";
        newRecord.Age = "J";
        newRecord.Sex = "M";
        newRecord.D2 = 1.0;
        newRecord.D3 = 2.0;
        newRecord.D4 = 3.0;
        newRecord.D5 = 4.0;
        newRecord.D6 = 5.0;
        newRecord.D7 = 6.0;
        newRecord.D8 = 7.0;

        newRecord.Fat = 5.0;
        newRecord.Wing = 456.1230000000000000001;
        newRecord.Tail = 789.0;

        var record = DbBirdRecord.from(newRecord);

        var returning = new ReturningBirdsData();
        returning.RingNumber = "223344";
        returning.Species = "XXX.YYY";
        returning.FirstDateSeen = LocalDateTime.of(2020, 1, 2, 0, 0);
        returning.FirstSeasonSeen = Season.Autumn;
        returning.LastDateSeen = LocalDateTime.of(2021, 11, 2, 0, 0);
        returning.LastSeasonSeen = Season.Autumn;

        returning.Weight = new BigDecimal("123.000");

        returning.Records = List.of(record);

        returning.Wing = new BigDecimal("456.123");
        returning.Tail = new BigDecimal("789");

        returning.Fat = 5;

        var list = List.of(returning);
        var csvRecord = mapper.getCsvReturningBirdsData(list);

        Assertions.assertEquals(1, csvRecord.size());
        Assertions.assertEquals("223344", csvRecord.get(0).RingNumber);
        Assertions.assertEquals("XXX.YYY", csvRecord.get(0).Species);
        Assertions.assertEquals(LocalDateTime.of(2020, 1, 2, 0, 0), csvRecord.get(0).FirstDateSeen);
        Assertions.assertEquals(Season.Autumn.toString(), csvRecord.get(0).FirstSeasonSeen);
        Assertions.assertEquals(LocalDateTime.of(2021, 11, 2, 0, 0), csvRecord.get(0).LastDateSeen);
        Assertions.assertEquals(Season.Autumn.toString(), csvRecord.get(0).LastSeasonSeen);
        Assertions.assertEquals(new BigDecimal("123.000"), csvRecord.get(0).Weight);

        Assertions.assertEquals(5, csvRecord.get(0).Fat);


        Assertions.assertEquals(new BigDecimal("456.123"), csvRecord.get(0).Wing);
        Assertions.assertEquals(new BigDecimal("789.000"), csvRecord.get(0).Tail);
        Assertions.assertEquals("J", csvRecord.get(0).Age);
        Assertions.assertEquals("M", csvRecord.get(0).Sex);
        Assertions.assertEquals(1, csvRecord.get(0).D2);
        Assertions.assertEquals(2, csvRecord.get(0).D3);
        Assertions.assertEquals(3, csvRecord.get(0).D4);
        Assertions.assertEquals(4, csvRecord.get(0).D5);
        Assertions.assertEquals(5, csvRecord.get(0).D6);
        Assertions.assertEquals(6, csvRecord.get(0).D7);
        Assertions.assertEquals(7, csvRecord.get(0).D8);
    }

    @Test
    public void SerializeBirdData() {
        var record = new DbBirdRecord();
        record.ring = "LA94007";
        record.speciesCode = "REG.REG";
        record.date = LocalDateTime.of(1982, 10, 12, 0, 0, 0);
        record.sex = BirdSex.Male;
        record.age = "I";
        record.weight = BigDecimal.valueOf(50.5);
        record.wing = BigDecimal.valueOf(50.5);
        record.tail = BigDecimal.valueOf(60);
        record.fat = 3;
        record.d2 = 1;
        record.d3 = 2;
        record.d4 = 3;
        record.d5 = 4;
        record.d6 = 5;
        record.d7 = 6;
        record.d8 = 7;

        var data = new ReturningBirdsData();
        data.RingNumber = "LA94007";
        data.Species = "REG.REG";
        data.FirstDateSeen = LocalDateTime.of(1982, 10, 12, 0, 0);
        data.LastDateSeen = LocalDateTime.of(1983, 4, 1, 0, 0);
        data.FirstSeasonSeen = Season.Autumn;
        data.LastSeasonSeen = Season.Spring;
        data.Weight = new BigDecimal("50.500");
        data.Records = List.of(record);
        data.Wing = new BigDecimal("50.500");
        data.Tail = new BigDecimal("60");

        data.Population = 5;

        data.Fat = 3;
        data.FatMedian = new BigDecimal("4.5");
        data.FatUpperQuartile = new BigDecimal("4.7");
        data.FatLowerQuartile = new BigDecimal("4.3");

        var birds = mapper.getCsvReturningBirdsData(List.of(data));

        Assertions.assertEquals(1, birds.size());
        Assertions.assertEquals("LA94007", birds.get(0).RingNumber);
        Assertions.assertEquals("REG.REG", birds.get(0).Species);
        Assertions.assertEquals(LocalDateTime.of(1982, 10, 12, 0, 0), birds.get(0).FirstDateSeen);
        Assertions.assertEquals(LocalDateTime.of(1983, 4, 1, 0, 0), birds.get(0).LastDateSeen);
        Assertions.assertEquals(Season.Autumn.toString(), birds.get(0).FirstSeasonSeen);
        Assertions.assertEquals(Season.Spring.toString(), birds.get(0).LastSeasonSeen);
        Assertions.assertEquals(LocalDateTime.of(1982, 10, 12, 0, 0), birds.get(0).RecordDate);
        Assertions.assertEquals("M", birds.get(0).Sex);
        Assertions.assertEquals("I", birds.get(0).Age);
        Assertions.assertEquals(new BigDecimal("50.500"), birds.get(0).Weight);
        Assertions.assertEquals(3, birds.get(0).Fat);
        Assertions.assertEquals(new BigDecimal("50.500"), birds.get(0).Wing);
        Assertions.assertEquals(new BigDecimal("60.000"), birds.get(0).Tail);
        Assertions.assertEquals(1, birds.get(0).D2);
        Assertions.assertEquals(2, birds.get(0).D3);
        Assertions.assertEquals(3, birds.get(0).D4);
        Assertions.assertEquals(4, birds.get(0).D5);
        Assertions.assertEquals(5, birds.get(0).D6);
        Assertions.assertEquals(6, birds.get(0).D7);
        Assertions.assertEquals(7, birds.get(0).D8);
        Assertions.assertEquals(5, birds.get(0).Population);
        Assertions.assertEquals(3, birds.get(0).Fat);
        Assertions.assertEquals(new BigDecimal("4.5"), birds.get(0).FatPopulationMedian);
        Assertions.assertEquals(new BigDecimal("4.7"), birds.get(0).FatPopulationUpperQuartile);
        Assertions.assertEquals(new BigDecimal("4.3"), birds.get(0).FatPopulationLowerQuartile);
    }

    @Test
    public void mapperTranslatesPointednessAndSymmetry() {

        var data = new ReturningBirdsData();
        data.Pointedness = new BigDecimal("10.123");
        data.Symmetry = new BigDecimal("20.123");
        data.Records = List.of(new DbBirdRecord());

        var birds = mapper.getCsvReturningBirdsData(List.of(data));

        Assertions.assertEquals(new BigDecimal("10.123"), birds.get(0).Pointedness);
        Assertions.assertEquals(new BigDecimal("20.123"), birds.get(0).Symmetry);
    }

    @Test
    public void mapperShouldGiveMeanAndStandardDeviationValues() {
        var data = new ReturningBirdsData();

        data.Records = List.of(new DbBirdRecord());

        data.Weight = new BigDecimal("50.123");
        data.WeightMean = new BigDecimal("60");
        data.WeightStandardDeviation = new BigDecimal("10");

        data.Wing = new BigDecimal("30");
        data.WingMean = new BigDecimal("40");
        data.WingStandardDeviation = new BigDecimal("10");

        data.Tail = new BigDecimal("60");
        data.TailMean = new BigDecimal("70");
        data.TailStandardDeviation = new BigDecimal("10");

        data.Pointedness = new BigDecimal("10.123");
        data.PointednessMean = new BigDecimal("20");
        data.PointednessStandardDeviation = new BigDecimal("10");

        data.Symmetry = new BigDecimal("20.123");
        data.SymmetryMean = new BigDecimal("30");
        data.SymmetryStandardDeviation = new BigDecimal("10");

        var birds = mapper.getCsvReturningBirdsData(List.of(data));

        Assertions.assertEquals(new BigDecimal("50.123"), birds.get(0).Weight);
        Assertions.assertEquals(new BigDecimal("60.000"), birds.get(0).WeightPopulationMean);
        Assertions.assertEquals(new BigDecimal("10.000"), birds.get(0).WeightPopulationStandardDeviation);

        Assertions.assertEquals(new BigDecimal("30.000"), birds.get(0).Wing);
        Assertions.assertEquals(new BigDecimal("40.000"), birds.get(0).WingPopulationMean);
        Assertions.assertEquals(new BigDecimal("10.000"), birds.get(0).WingPopulationStandardDeviation);

        Assertions.assertEquals(new BigDecimal("60.000"), birds.get(0).Tail);
        Assertions.assertEquals(new BigDecimal("70.000"), birds.get(0).TailPopulationMean);
        Assertions.assertEquals(new BigDecimal("10.000"), birds.get(0).TailPopulationStandardDeviation);

        Assertions.assertEquals(new BigDecimal("10.123"), birds.get(0).Pointedness);
        Assertions.assertEquals(new BigDecimal("20.000"), birds.get(0).PointednessPopulationMean);
        Assertions.assertEquals(new BigDecimal("10.000"), birds.get(0).PointednessPopulationStandardDeviation);

        Assertions.assertEquals(new BigDecimal("20.123"), birds.get(0).Symmetry);
        Assertions.assertEquals(new BigDecimal("30.000"), birds.get(0).SymmetryPopulationMean);
        Assertions.assertEquals(new BigDecimal("10.000"), birds.get(0).SymmetryPopulationStandardDeviation);
    }

    @Test
    public void mapperReturnsDataFromOnlyFirstBird() {
        var r1 = new DbBirdRecord();
        r1.date = LocalDateTime.of(1982, 10, 12, 0, 0, 0);

        var r2 = new DbBirdRecord();
        r2.date = LocalDateTime.of(1983, 10, 12, 0, 0, 0);

        var data = new ReturningBirdsData();
        data.Records = List.of(r1, r2);

        var birds = mapper.getCsvReturningBirdsData(List.of(data));

        Assertions.assertEquals(1, birds.size());
        Assertions.assertEquals(LocalDateTime.of(1982, 10, 12, 0, 0, 0), birds.get(0).RecordDate);
    }
}