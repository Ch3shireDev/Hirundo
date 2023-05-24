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
}
