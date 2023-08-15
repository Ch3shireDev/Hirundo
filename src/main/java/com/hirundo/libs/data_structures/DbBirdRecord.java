package com.hirundo.libs.data_structures;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbBirdRecord {
    public Integer id;
    public String ring;
    public String speciesCode;
    public String speciesNameLat;
    public String speciesNameEng;
    public BirdSex sex;
    public Integer D2;
    public Integer D3;
    public Integer D4;
    public Integer D5;
    public Integer D6;
    public Integer D7;
    public Integer D8;
    public LocalDateTime date;
    public Season season;
    BigDecimal weight;
    String age;
    Integer fat;
    BigDecimal wing;
    Integer tail;


    public static DbBirdRecord from(OldDbBirdRecord birdRecord) {
        var result = new DbBirdRecord();
        result.speciesCode = birdRecord.Spec;
        result.speciesNameEng = null;
        result.speciesNameLat = null;
        result.sex = toSex(birdRecord.Sex);
        result.D2 = asInt(birdRecord.D2);
        result.D3 = asInt(birdRecord.D3);
        result.D4 = asInt(birdRecord.D4);
        result.D5 = asInt(birdRecord.D5);
        result.D6 = asInt(birdRecord.D6);
        result.D7 = asInt(birdRecord.D7);
        result.D8 = asInt(birdRecord.D8);
        result.ring = birdRecord.Ring;
        result.date = birdRecord.Date;
        result.season = asSeason(birdRecord.Seas);
        result.id = birdRecord.IDR_Podab;
        result.weight = asBigDecimal(birdRecord.Mass);
        result.age = birdRecord.Age;
        result.fat = asInt(birdRecord.Fat);
        result.wing = asBigDecimal(birdRecord.Wing);
        result.tail = asInt(birdRecord.Tail);
        return result;
    }

    public static DbBirdRecord from(NewDbBirdRecord birdRecord) {
        var result = new DbBirdRecord();
        result.id = DbBirdRecord.asInt((birdRecord.IDR_Podab));
        result.speciesCode = birdRecord.SpeciesCode;
        result.speciesNameEng = birdRecord.EnglishName;
        result.speciesNameLat = birdRecord.LatinName;
        result.sex = DbBirdRecord.toSex(birdRecord.Sex);
        result.D2 = DbBirdRecord.asInt(birdRecord.D2);
        result.D3 = DbBirdRecord.asInt(birdRecord.D3);
        result.D4 = DbBirdRecord.asInt(birdRecord.D4);
        result.D5 = DbBirdRecord.asInt(birdRecord.D5);
        result.D6 = DbBirdRecord.asInt(birdRecord.D6);
        result.D7 = DbBirdRecord.asInt(birdRecord.D7);
        result.D8 = DbBirdRecord.asInt(birdRecord.D8);
        result.ring = birdRecord.Ring;
        result.date = DbBirdRecord.asDate(birdRecord.Date2);
        result.season = DbBirdRecord.asSeason(birdRecord.Seas);
        result.weight = DbBirdRecord.asBigDecimal(birdRecord.Weight);
        result.age = birdRecord.Age;
        result.fat = DbBirdRecord.asInt(birdRecord.Fat);
        result.wing = DbBirdRecord.asBigDecimal(birdRecord.Wing);
        result.tail = DbBirdRecord.asInt(birdRecord.Tail);

        return result;
    }

    static LocalDateTime asDate(String value) {
        if (null == value) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(value, formatter);
        return date.atStartOfDay();
    }

    static Integer asInt(Double value) {
        if (null == value) return null;
        return value.intValue();
    }

    static Integer asInt(Float value) {
        if (null == value) return null;
        return value.intValue();
    }

    static BirdSex toSex(String sexStr) {
        if ("F".equals(sexStr)) return BirdSex.Female;
        if ("M".equals(sexStr)) return BirdSex.Male;
        return BirdSex.Undefined;
    }

    static Season asSeason(String seasonStr) {
        if ("A".equals(seasonStr)) return Season.Autumn;
        if ("S".equals(seasonStr)) return Season.Spring;
        return Season.Undefined;
    }

    static BigDecimal asBigDecimal(Double value) {
        if (null == value) return null;
        return BigDecimal.valueOf(value);
    }

    static BigDecimal asBigDecimal(Float value) {
        if (null == value) return null;
        return BigDecimal.valueOf(value);
    }

    public String getSpeciesCode() {
        return speciesCode;
    }

    public String getSpeciesNameEng() {
        return speciesNameEng;
    }

    public String getSpeciesNameLat() {
        return speciesNameLat;
    }

    public BirdSex getSex() {
        return sex;
    }

    public String getRing() {
        return ring;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getYear() {
        return date.getYear();
    }

    public Season getSeason() {
        return season;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public String getAge() {
        return age;
    }

    public Integer getFat() {
        return fat;
    }

    public BigDecimal getWing() {
        return wing;
    }

    public Integer getTail() {
        return tail;
    }

    public Integer getD2() {
        return D2;
    }

    public Integer getD3() {
        return D3;
    }

    public Integer getD4() {
        return D4;
    }

    public Integer getD5() {
        return D5;
    }

    public Integer getD6() {
        return D6;
    }

    public Integer getD7() {
        return D7;
    }

    public Integer getD8() {
        return D8;
    }

}

