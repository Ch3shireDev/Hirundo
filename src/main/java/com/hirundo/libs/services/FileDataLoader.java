package com.hirundo.libs.services;


import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public class FileDataLoader implements IFileDataLoader {
    private final IDbBirdRecordDataLoader[] birdDataLoaders;
    private String fileName;

    public FileDataLoader(IDbBirdRecordDataLoader... birdDataLoaders) {
        this.birdDataLoaders = birdDataLoaders;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<DbBirdRecord> loadData(String tableName) throws Exception {

        if (null == fileName || fileName.isEmpty()) {
            throw new Exception("File name is not set");
        }

        if (null == tableName || tableName.isEmpty()) {
            throw new Exception("Table name is not set");
        }

        return null;

    }

}

