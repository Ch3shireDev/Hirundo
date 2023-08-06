package com.hirundo.libs.data_structures;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DbBirdRecord {
    private final OldDbBirdRecord oldDbBirdRecord;
    private final NewDbBirdRecord newDbBirdRecord;

    public DbBirdRecord(NewDbBirdRecord newDbBirdRecord) {
        this.newDbBirdRecord = newDbBirdRecord;
        this.oldDbBirdRecord = null;
    }

    public DbBirdRecord(OldDbBirdRecord oldDbBirdRecord) {
        this.oldDbBirdRecord = oldDbBirdRecord;
        this.newDbBirdRecord = null;
    }


    public String getSpeciesCode() {
        if (oldDbBirdRecord != null) {
            return oldDbBirdRecord.Spec;
        }
        if (newDbBirdRecord != null) {
            return newDbBirdRecord.SpeciesCode;
        }
        return null;
    }

    public String getSpeciesNameEng() {
        if (oldDbBirdRecord != null) {
            return null;
        }
        if (newDbBirdRecord != null) {
            return newDbBirdRecord.EnglishName;
        }
        return null;
    }

    public String getSpeciesNameLat() {
        if (oldDbBirdRecord != null) {
            return null;
        }
        if (newDbBirdRecord != null) {
            return newDbBirdRecord.LatinName;
        }
        return null;
    }

    public DbBirdRecordType getRecordType() {
        if (oldDbBirdRecord != null) {
            return DbBirdRecordType.Old;
        }
        if (newDbBirdRecord != null) {
            return DbBirdRecordType.New;
        }
        return DbBirdRecordType.Undefined;
    }

    public BirdSex getSex() {
        if (oldDbBirdRecord != null) {
            if ("F".equals(oldDbBirdRecord.Sex)) return BirdSex.Female;
            if ("M".equals(oldDbBirdRecord.Sex)) return BirdSex.Male;
            return BirdSex.Undefined;
        }
        if (newDbBirdRecord != null) {
            if ("F".equals(newDbBirdRecord.Sex)) return BirdSex.Female;
            if ("M".equals(newDbBirdRecord.Sex)) return BirdSex.Male;
            return BirdSex.Undefined;
        }
        return null;
    }

    public String getRing() {
        if (oldDbBirdRecord != null) {
            return oldDbBirdRecord.Ring;
        }
        if (newDbBirdRecord != null) {
            return newDbBirdRecord.Ring;
        }
        return null;
    }

    public LocalDateTime getDate() {
        if (oldDbBirdRecord != null) {
            return oldDbBirdRecord.Date;
        }
        if (newDbBirdRecord != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(newDbBirdRecord.Date2, formatter);
            return date.atStartOfDay();
        }
        return null;
    }

    public Integer getYear(){
        return getDate().getYear();
    }

    public Season getSeason() {
        String season = null;
        if (oldDbBirdRecord != null) {
            season = oldDbBirdRecord.Seas;
        }
        if (newDbBirdRecord != null) {
            season = newDbBirdRecord.Seas;
        }
        if (season == null) return Season.Undefined;
        return switch (season) {
            case "A" -> Season.Autumn;
            case "S" -> Season.Spring;
            default -> Season.Undefined;
        };
    }

    public Integer getId() {
        if (oldDbBirdRecord != null) {
            return oldDbBirdRecord.IDR_Podab;
        }
        if (newDbBirdRecord != null) {
            return Math.round((float) (double) newDbBirdRecord.IDR_Podab);
        }
        return null;
    }

    public Double getWeight(){
        if (oldDbBirdRecord != null) {
            return Double.valueOf(oldDbBirdRecord.Mass);
        }
        if (newDbBirdRecord != null) {
            return newDbBirdRecord.Weight;
        }
        return null;
    }

    public String getAge() {
        if (oldDbBirdRecord != null) {
            return oldDbBirdRecord.Age;
        }
        if (newDbBirdRecord != null) {
            return newDbBirdRecord.Age;
        }
        return null;
    }
}

