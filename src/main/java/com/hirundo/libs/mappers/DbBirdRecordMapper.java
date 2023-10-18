package com.hirundo.libs.mappers;

import com.hirundo.libs.data_structures.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbBirdRecordMapper {

    public DbBirdRecord map(OldDbBirdRecord birdRecord) {
        var result = new DbBirdRecord();
        result.speciesCode = birdRecord.Spec;
        result.speciesNameEng = null;
        result.speciesNameLat = null;
        result.sex = toSex(birdRecord.Sex);
        result.d2 = asInt(birdRecord.D2);
        result.d3 = asInt(birdRecord.D3);
        result.d4 = asInt(birdRecord.D4);
        result.d5 = asInt(birdRecord.D5);
        result.d6 = asInt(birdRecord.D6);
        result.d7 = asInt(birdRecord.D7);
        result.d8 = asInt(birdRecord.D8);
        result.ring = birdRecord.Ring;
        result.date = birdRecord.Date;
        result.season = asSeason(birdRecord.Seas);
        result.id = birdRecord.IDR_Podab;
        result.weight = asBigDecimal(birdRecord.Mass);
        result.age = birdRecord.Age;
        result.fat = asInt(birdRecord.Fat);
        result.wing = asBigDecimal(birdRecord.Wing);
        result.tail = asBigDecimal(birdRecord.Tail);
        return result;
    }

    public DbBirdRecord map(NewDbBirdRecord birdRecord) {
        var result = new DbBirdRecord();
        result.id = asInt((birdRecord.IDR_Podab));
        result.speciesCode = birdRecord.SpeciesCode;
        result.speciesNameEng = birdRecord.EnglishName;
        result.speciesNameLat = birdRecord.LatinName;
        result.sex = toSex(birdRecord.Sex);
        result.d2 = asInt(birdRecord.D2);
        result.d3 = asInt(birdRecord.D3);
        result.d4 = asInt(birdRecord.D4);
        result.d5 = asInt(birdRecord.D5);
        result.d6 = asInt(birdRecord.D6);
        result.d7 = asInt(birdRecord.D7);
        result.d8 = asInt(birdRecord.D8);
        result.ring = birdRecord.Ring;
        result.date = asDate(birdRecord.Date2);
        result.season = asSeason(birdRecord.Seas);
        result.weight = asBigDecimal(birdRecord.Weight);
        result.age = birdRecord.Age;
        result.fat = asInt(birdRecord.Fat);
        result.wing = asBigDecimal(birdRecord.Wing);
        result.tail = asBigDecimal(birdRecord.Tail);

        return result;
    }

    LocalDateTime asDate(String value) {
        if (null == value) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(value, formatter);
        return date.atStartOfDay();
    }

    Integer asInt(Double value) {
        if (null == value) return null;
        return value.intValue();
    }

    Integer asInt(Float value) {
        if (null == value) return null;
        return value.intValue();
    }

    BirdSex toSex(String sexStr) {
        if ("F".equals(sexStr)) return BirdSex.Female;
        if ("M".equals(sexStr)) return BirdSex.Male;
        return BirdSex.Undefined;
    }

    Season asSeason(String seasonStr) {
        if ("A".equals(seasonStr)) return Season.Autumn;
        if ("S".equals(seasonStr)) return Season.Spring;
        return Season.Undefined;
    }

    BigDecimal asBigDecimal(Double value) {
        if (null == value) return null;
        return BigDecimal.valueOf(value);
    }

    BigDecimal asBigDecimal(Float value) {
        if (null == value) return null;
        return BigDecimal.valueOf(value);
    }
}
