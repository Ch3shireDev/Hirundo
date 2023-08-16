package com.hirundo.libs.loaders;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public interface IFileDataLoader {
    List<DbBirdRecord> loadData() throws Exception;
}

