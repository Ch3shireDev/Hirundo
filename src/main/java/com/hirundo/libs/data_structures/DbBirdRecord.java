package com.hirundo.libs.data_structures;

public class DbBirdRecord {
    private final OldDbBirdRecord oldDbBirdRecord;
    private final NewDbBirdRecord newDbBirdRecord;

    public DbBirdRecord(NewDbBirdRecord newDbBirdRecord){
        this.newDbBirdRecord = newDbBirdRecord;
        this.oldDbBirdRecord = null;
    }

    public DbBirdRecord(OldDbBirdRecord oldDbBirdRecord){
        this.oldDbBirdRecord = oldDbBirdRecord;
        this.newDbBirdRecord = null;
    }


    public String getSpecies() {
        if (null != oldDbBirdRecord) {
            return oldDbBirdRecord.Spec;
        }
        if (null != newDbBirdRecord) {
            return newDbBirdRecord.SpeciesCode;
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
}

