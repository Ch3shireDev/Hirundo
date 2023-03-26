package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.OldDbBirdRecord;

import java.util.List;

public interface IOldDbBirdRecordDataLoader {
    List<OldDbBirdRecord> loadData();
}
