package com.hirundo.libs.data_structures;

import java.math.BigDecimal;
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

    public Integer getYear() {
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

    public BigDecimal getWeight() {
        if (oldDbBirdRecord != null) {
            if (oldDbBirdRecord.Mass == null) return null;
            return BigDecimal.valueOf(oldDbBirdRecord.Mass);
        }
        if (newDbBirdRecord != null) {
            if (newDbBirdRecord.Weight == null) return null;
            return BigDecimal.valueOf(newDbBirdRecord.Weight);
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

    public Integer getFat() {
        if (oldDbBirdRecord != null) {
            if (oldDbBirdRecord.Fat == null) return null;
            return oldDbBirdRecord.Fat.intValue();
        }
        if (newDbBirdRecord != null) {
            if (newDbBirdRecord.Fat == null) return null;
            return newDbBirdRecord.Fat.intValue();
        }
        return null;
    }

    public BigDecimal getWing() {
        if (oldDbBirdRecord != null) {
            if (oldDbBirdRecord.Wing == null) return null;
            return BigDecimal.valueOf(oldDbBirdRecord.Wing);
        }
        if (newDbBirdRecord != null) {
            if (newDbBirdRecord.Wing == null) return null;
            return BigDecimal.valueOf(newDbBirdRecord.Wing);
        }
        return null;
    }

    public Integer getTail() {
        if (oldDbBirdRecord != null) {
            if (oldDbBirdRecord.Tail == null) return null;
            return oldDbBirdRecord.Tail.intValue();
        }
        if (newDbBirdRecord != null) {
            if (newDbBirdRecord.Tail == null) return null;
            return newDbBirdRecord.Tail.intValue();
        }
        return null;
    }

    public Integer getD2() {
        if (oldDbBirdRecord != null && oldDbBirdRecord.D2 != null) {
            return Math.round(oldDbBirdRecord.D2);
        }
        if (newDbBirdRecord != null && newDbBirdRecord.D2 != null) {
            return newDbBirdRecord.D2.intValue();
        }
        return null;
    }

    public Integer getD3() {
        if (oldDbBirdRecord != null && oldDbBirdRecord.D3 != null) {
            return Math.round(oldDbBirdRecord.D3);
        }
        if (newDbBirdRecord != null && newDbBirdRecord.D3 != null) {
            return newDbBirdRecord.D3.intValue();
        }
        return null;
    }

    public Integer getD4() {
        if (oldDbBirdRecord != null && oldDbBirdRecord.D4 != null) {
            return Math.round(oldDbBirdRecord.D4);
        }
        if (newDbBirdRecord != null && newDbBirdRecord.D4 != null) {
            return newDbBirdRecord.D4.intValue();
        }
        return null;
    }

    public Integer getD5() {
        if (oldDbBirdRecord != null && oldDbBirdRecord.D5 != null) {
            return Math.round(oldDbBirdRecord.D5);
        }
        if (newDbBirdRecord != null && newDbBirdRecord.D5 != null) {
            return newDbBirdRecord.D5.intValue();
        }
        return null;
    }

    public Integer getD6() {
        if (oldDbBirdRecord != null && oldDbBirdRecord.D6 != null) {
            return Math.round(oldDbBirdRecord.D6);
        }
        if (newDbBirdRecord != null && newDbBirdRecord.D6 != null) {
            return newDbBirdRecord.D6.intValue();
        }
        return null;
    }

    public Integer getD7() {
        if (oldDbBirdRecord != null && oldDbBirdRecord.D7 != null) {
            return Math.round(oldDbBirdRecord.D7);
        }
        if (newDbBirdRecord != null && newDbBirdRecord.D7 != null) {
            return newDbBirdRecord.D7.intValue();
        }
        return null;
    }

    public Integer getD8() {
        if (oldDbBirdRecord != null && oldDbBirdRecord.D8 != null) {
            return Math.round(oldDbBirdRecord.D8);
        }
        if (newDbBirdRecord != null && newDbBirdRecord.D8 != null) {
            return newDbBirdRecord.D8.intValue();
        }
        return null;
    }
}

