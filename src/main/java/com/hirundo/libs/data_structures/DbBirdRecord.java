package com.hirundo.libs.data_structures;

import com.hirundo.libs.mappers.DbBirdRecordMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DbBirdRecord {
    public Integer id;
    public String ring;
    public String speciesCode;
    public String speciesNameLat;
    public String speciesNameEng;
    public BirdSex sex;
    public Integer d2;
    public Integer d3;
    public Integer d4;
    public Integer d5;
    public Integer d6;
    public Integer d7;
    public Integer d8;
    public LocalDateTime date;
    public Season season;
    public BigDecimal weight;
    public String age;
    public Integer fat;
    public BigDecimal wing;
    public BigDecimal tail;


    public static DbBirdRecord from(OldDbBirdRecord birdRecord) {
        return new DbBirdRecordMapper().map(birdRecord);
    }

    public static DbBirdRecord from(NewDbBirdRecord birdRecord) {
        return new DbBirdRecordMapper().map(birdRecord);
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

    public BigDecimal getTail() {
        return tail;
    }

    public Integer getD2() {
        return d2;
    }

    public Integer getD3() {
        return d3;
    }

    public Integer getD4() {
        return d4;
    }

    public Integer getD5() {
        return d5;
    }

    public Integer getD6() {
        return d6;
    }

    public Integer getD7() {
        return d7;
    }

    public Integer getD8() {
        return d8;
    }
}

