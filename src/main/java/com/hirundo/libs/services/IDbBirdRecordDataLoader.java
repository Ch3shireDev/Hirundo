package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public interface IDbBirdRecordDataLoader extends IBirdRecordDataLoader<DbBirdRecord> {
    @Override
    List<DbBirdRecord> loadData(String tableName) throws Exception;
}
