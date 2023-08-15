package com.hirundo.mockups;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.services.IFileDataLoader;

import java.util.ArrayList;
import java.util.List;

public class MockFileDataLoader implements IFileDataLoader {

    public Boolean IsLoaded;
    public List<DbBirdRecord> Data = new ArrayList<>();

    @Override
    public List<DbBirdRecord> loadData() {
        IsLoaded = true;
        return Data;
    }

}
