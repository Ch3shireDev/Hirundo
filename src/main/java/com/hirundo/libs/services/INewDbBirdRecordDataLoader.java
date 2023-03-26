package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.NewDbBirdRecord;

import java.util.List;

public interface INewDbBirdRecordDataLoader {
    List<NewDbBirdRecord> loadData();
}
