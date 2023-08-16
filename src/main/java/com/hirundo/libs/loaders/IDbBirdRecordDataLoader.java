package com.hirundo.libs.loaders;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public interface IDbBirdRecordDataLoader extends IBirdRecordDataLoader<DbBirdRecord> {
    @Override
    List<DbBirdRecord> loadData() throws Exception;
}
