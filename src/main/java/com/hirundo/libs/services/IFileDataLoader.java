package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public interface IFileDataLoader {
    void setFileName(String fileName);
    List<DbBirdRecord> loadData(String tableName) throws Exception;
}
