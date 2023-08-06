package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.data_structures.ReturningBirdsData;
import com.hirundo.libs.data_structures.Season;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReturningBirdsDataCsvRecordMapperTest {

    ReturningBirdsDataCsvRecordMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new ReturningBirdsDataCsvRecordMapper();
    }

    @Test
    void getCsvReturningBirdsData() {
            var newRecord = new NewDbBirdRecord();
            newRecord.Date2 = "2020-01-02";


            var record = new DbBirdRecord(newRecord);

        var returning = new ReturningBirdsData();
        returning.RingNumber = "223344";
        returning.Species = "XXX.YYY";
        returning.FirstDateSeen = LocalDateTime.of(2020, 1, 2, 0, 0);
        returning.FirstSeasonSeen = Season.Autumn;
        returning.LastDateSeen = LocalDateTime.of(2021, 11, 2, 0, 0);
        returning.LastSeasonSeen = Season.Autumn;
        returning.Weight = 123.0;
        returning.Fat = 5.0;
        returning.Wing = 456.0;
        returning.Tail = 789.0;
        returning.Age = "J";
        returning.Sex = "M";
        returning.D2 = 1.0;
        returning.D3 = 2.0;
        returning.D4 = 3.0;
        returning.D5 = 4.0;
        returning.D6 = 5.0;
        returning.D7 = 6.0;
        returning.D8 = 7.0;
        returning.Records = List.of(record);

        var list = List.of(returning);
        var csvRecord = mapper.getCsvReturningBirdsData(list);

        assertEquals(1, csvRecord.size());
        assertEquals("223344", csvRecord.get(0).RingNumber);
        assertEquals("XXX.YYY", csvRecord.get(0).Species);
        assertEquals(LocalDateTime.of(2020, 1, 2, 0, 0), csvRecord.get(0).FirstDateSeen);
        assertEquals(Season.Autumn.toString(), csvRecord.get(0).FirstSeasonSeen);
        assertEquals(LocalDateTime.of(2021, 11, 2, 0, 0), csvRecord.get(0).LastDateSeen);
        assertEquals(Season.Autumn.toString(), csvRecord.get(0).LastSeasonSeen);
        assertEquals(123.0, csvRecord.get(0).Weight);
        assertEquals(5.0, csvRecord.get(0).Fat);
        assertEquals(456.0, csvRecord.get(0).Wing);
        assertEquals(789.0, csvRecord.get(0).Tail);
        assertEquals("J", csvRecord.get(0).Age);
        assertEquals("M", csvRecord.get(0).Sex);
        assertEquals(1.0, csvRecord.get(0).D2);
        assertEquals(2.0, csvRecord.get(0).D3);
        assertEquals(3.0, csvRecord.get(0).D4);
        assertEquals(4.0, csvRecord.get(0).D5);
        assertEquals(5.0, csvRecord.get(0).D6);
        assertEquals(6.0, csvRecord.get(0).D7);
        assertEquals(7.0, csvRecord.get(0).D8);
    }
}