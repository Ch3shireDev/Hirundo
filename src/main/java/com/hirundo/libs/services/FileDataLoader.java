package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public class FileDataLoader implements IFileDataLoader {
    private final IDbBirdRecordDataLoader[] birdDataLoaders;

    public FileDataLoader(IDbBirdRecordDataLoader... birdDataLoaders) {
        this.birdDataLoaders = birdDataLoaders;
    }

    @Override
    public List<DbBirdRecord> loadData() throws Exception {

        var totalData = new java.util.ArrayList<DbBirdRecord>();

        for (IDbBirdRecordDataLoader birdDataLoader : birdDataLoaders) {
            var data = birdDataLoader.loadData();
            totalData.addAll(data);
        }

        return totalData;
    }

}

