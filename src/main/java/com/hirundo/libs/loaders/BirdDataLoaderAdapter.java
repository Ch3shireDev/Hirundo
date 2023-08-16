package com.hirundo.libs.loaders;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.data_structures.OldDbBirdRecord;

import java.util.List;

public class BirdDataLoaderAdapter implements IDbBirdRecordDataLoader {
    private final IBirdRecordDataLoader<?> fileDataLoader;

    public BirdDataLoaderAdapter(IBirdRecordDataLoader<?> fileDataLoader) {
        this.fileDataLoader = fileDataLoader;
    }

    @Override
    public List<DbBirdRecord> loadData() throws Exception {
        var data = fileDataLoader.loadData();
        if (data.get(0) instanceof OldDbBirdRecord) {
            return data.stream().map(x -> DbBirdRecord.from((OldDbBirdRecord) x)).toList();
        }
        if (data.get(0) instanceof NewDbBirdRecord) {
            return data.stream().map(x -> DbBirdRecord.from((NewDbBirdRecord) x)).toList();
        }
        throw new Exception("Unknown data type");
    }
}
