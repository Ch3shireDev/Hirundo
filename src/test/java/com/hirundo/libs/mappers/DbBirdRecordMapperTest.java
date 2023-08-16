package com.hirundo.libs.mappers;

import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.data_structures.OldDbBirdRecord;
import com.hirundo.libs.data_structures.Season;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

class DbBirdRecordMapperTest {

    DbBirdRecordMapper mapper;

    @BeforeEach
    void setUp(){
        mapper = new DbBirdRecordMapper();
    }

    @Test
    void fromNewRecord() {
        var record1 = new NewDbBirdRecord();
        record1.IDR_Podab = 123.0;
        record1.SpeciesCode = "AAA.BBB";
        record1.EnglishName = "Aarin Babin";
        record1.LatinName = "Aarex Babex";
        record1.Age = "I";
        record1.Sex = "F";
        record1.Ring ="111222";
        record1.Date2 = "2020-01-02";
        record1.Seas = "A";
        record1.Weight = 2.5;
        record1.Fat = 3.0;
        record1.Wing = 120.5;
        record1.Tail = 220.0;
        record1.D2 = 2.0;
        record1.D3 = 3.0;
        record1.D4 = 4.0;
        record1.D5 = 5.0;
        record1.D6 = 6.0;
        record1.D7 = 7.0;
        record1.D8 = 8.0;

        var record = mapper.map(record1);

        Assertions.assertEquals(123, record.getId());
        Assertions.assertEquals("AAA.BBB", record.getSpeciesCode());
        Assertions.assertEquals("Aarin Babin", record.getSpeciesNameEng());
        Assertions.assertEquals("Aarex Babex", record.getSpeciesNameLat());
        Assertions.assertEquals("I", record.getAge());
        Assertions.assertEquals(BirdSex.Female, record.getSex());
        Assertions.assertEquals("111222", record.getRing());
        Assertions.assertEquals(LocalDateTime.of(2020,1,2,0,0,0), record.getDate());
        Assertions.assertEquals(2020, record.getYear());
        Assertions.assertEquals(Season.Autumn, record.getSeason());
        Assertions.assertEquals(BigDecimal.valueOf( 2.5), record.getWeight());
        Assertions.assertEquals(3, record.getFat());
        Assertions.assertEquals(BigDecimal.valueOf(120.5), record.getWing());
        Assertions.assertEquals(220, record.getTail());
        Assertions.assertEquals(2, record.getD2());
        Assertions.assertEquals(3, record.getD3());
        Assertions.assertEquals(4, record.getD4());
        Assertions.assertEquals(5, record.getD5());
        Assertions.assertEquals(6, record.getD6());
        Assertions.assertEquals(7, record.getD7());
        Assertions.assertEquals(8, record.getD8());
    }
    @Test
    void fromOldRecord() {
        var record1 = new OldDbBirdRecord();
        record1.IDR_Podab = 123;
        record1.Spec = "AAA.BBB";
        record1.Age = "I";
        record1.Sex = "F";
        record1.Ring ="111222";
        record1.Date = LocalDateTime.of(2020,1,2,0,0,0);
        record1.Seas = "A";
        record1.Mass = 2.5f;
        record1.Fat = 3.0f;
        record1.Wing = 120.5f;
        record1.Tail = 220.0f;
        record1.D2 = 2.0f;
        record1.D3 = 3.0f;
        record1.D4 = 4.0f;
        record1.D5 = 5.0f;
        record1.D6 = 6.0f;
        record1.D7 = 7.0f;
        record1.D8 = 8.0f;

        var record = mapper.map(record1);
        Assertions.assertEquals(123, record.getId());
        Assertions.assertEquals("AAA.BBB", record.getSpeciesCode());
        Assertions.assertEquals("I", record.getAge());
        Assertions.assertEquals(BirdSex.Female, record.getSex());
        Assertions.assertEquals("111222", record.getRing());
        Assertions.assertEquals(LocalDateTime.of(2020,1,2,0,0,0), record.getDate());
        Assertions.assertEquals(2020, record.getYear());
        Assertions.assertEquals(Season.Autumn, record.getSeason());
        Assertions.assertEquals(BigDecimal.valueOf( 2.5), record.getWeight());
        Assertions.assertEquals(3, record.getFat());
        Assertions.assertEquals(BigDecimal.valueOf(120.5), record.getWing());
        Assertions.assertEquals(220, record.getTail());
        Assertions.assertEquals(2, record.getD2());
        Assertions.assertEquals(3, record.getD3());
        Assertions.assertEquals(4, record.getD4());
        Assertions.assertEquals(5, record.getD5());
        Assertions.assertEquals(6, record.getD6());
        Assertions.assertEquals(7, record.getD7());
        Assertions.assertEquals(8, record.getD8());
    }

}