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
        if (null != oldDbBirdRecord) {
            return oldDbBirdRecord.Spec;
        }
        if (null != newDbBirdRecord) {
            return newDbBirdRecord.SpeciesCode;
        }
        return null;
    }

    public String getSpeciesNameEng() {
        if (null != oldDbBirdRecord) {
            return null;
        }
        if (null != newDbBirdRecord) {
            return newDbBirdRecord.EnglishName;
        }
        return null;
    }

    public String getSpeciesNameLat() {
        if (null != oldDbBirdRecord) {
            return null;
        }
        if (null != newDbBirdRecord) {
            return newDbBirdRecord.LatinName;
        }
        return null;
    }

    public DbBirdRecordType getRecordType() {
        if (null != oldDbBirdRecord) {
            return DbBirdRecordType.Old;
        }
        if (null != newDbBirdRecord) {
            return DbBirdRecordType.New;
        }
        return DbBirdRecordType.Undefined;
    }

    public BirdSex getSex() {
        if (null != oldDbBirdRecord) {
            if ("F".equals(oldDbBirdRecord.Sex)) return BirdSex.Female;
            if ("M".equals(oldDbBirdRecord.Sex)) return BirdSex.Male;
            return BirdSex.Undefined;
        }
        if (null != newDbBirdRecord) {
            if ("F".equals(newDbBirdRecord.Sex)) return BirdSex.Female;
            if ("M".equals(newDbBirdRecord.Sex)) return BirdSex.Male;
            return BirdSex.Undefined;
        }
        return null;
    }

    public String getRing() {
        if (null != oldDbBirdRecord) {
            return oldDbBirdRecord.Ring;
        }
        if (null != newDbBirdRecord) {
            return newDbBirdRecord.Ring;
        }
        return null;
    }

    public LocalDateTime getDate() {
        if (null != oldDbBirdRecord) {
            return oldDbBirdRecord.Date;
        }
        if (null != newDbBirdRecord) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(newDbBirdRecord.Date2, formatter);
            LocalDateTime dateTime = date.atStartOfDay();
            return dateTime;
        }
        return null;
    }

    public Season getSeason() {
        String season = null;
        if (null != oldDbBirdRecord) {
            season = oldDbBirdRecord.Seas;
        }
        if (null != newDbBirdRecord) {
            season = newDbBirdRecord.Seas;
        }
        if (null == season) return Season.Undefined;
        return switch (season) {
            case "A" -> Season.Autumn;
            case "S" -> Season.Spring;
            default -> Season.Undefined;
        };
    }

    public Integer getId() {
        if (null != oldDbBirdRecord) {
            return oldDbBirdRecord.IDR_Podab;
        }
        if (null != newDbBirdRecord) {
            return Math.round((float) (double) newDbBirdRecord.IDR_Podab);
        }
        return null;
    }
}

