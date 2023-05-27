package com.hirundo.libs.data_structures;

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
}

