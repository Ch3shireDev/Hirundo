package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public interface IFileDataLoader {
    List<DbBirdRecord> loadData() throws Exception;
}

