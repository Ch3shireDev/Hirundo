package com.hirundo.libs.services;


import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.data_structures.OldDbBirdRecord;

import java.util.List;

public class FileDataLoader implements IFileDataLoader {

    private final IBirdRecordDataLoader<OldDbBirdRecord> oldDbBirdRecordDataLoader;
    private final IBirdRecordDataLoader<NewDbBirdRecord> newDbBirdRecordDataLoader;
    private String fileName;
    private String OldTableName;
    private String NewTableName;

    public FileDataLoader(IBirdRecordDataLoader<OldDbBirdRecord> oldDbBirdRecordDataLoader, IBirdRecordDataLoader<NewDbBirdRecord> newDbBirdRecordDataLoader) {
        this.oldDbBirdRecordDataLoader = oldDbBirdRecordDataLoader;
        this.newDbBirdRecordDataLoader = newDbBirdRecordDataLoader;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<DbBirdRecord> loadData(String tableName) throws Exception {
        if (null == oldDbBirdRecordDataLoader || null == newDbBirdRecordDataLoader) {
            throw new Exception("Data loaders are not initialized");
        }
        if (null == fileName || fileName.isEmpty()) {
            throw new Exception("File name is not set");
        }

        if (null == tableName || tableName.isEmpty()) {
            throw new Exception("Table name is not set");
        }
        return null;

    }

}

