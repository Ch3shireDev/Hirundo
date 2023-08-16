package com.hirundo.mockups;

import com.hirundo.libs.loaders.IBirdRecordDataLoaderBuilder;
import com.hirundo.libs.loaders.IFileDataLoader;

public class MockBirdRecordDataLoaderBuilder implements IBirdRecordDataLoaderBuilder {
    public String Filename;
    public String NewTableName;
    public String OldTableName;
    public IFileDataLoader FileDataLoader;

    @Override
    public IBirdRecordDataLoaderBuilder withFilename(String filename) {
        Filename = filename;
        return this;
    }

    @Override
    public IBirdRecordDataLoaderBuilder withOldTableName(String oldTableName) {
        OldTableName = oldTableName;
        return this;
    }

    @Override
    public IBirdRecordDataLoaderBuilder withNewTableName(String newTableName) {
        NewTableName = newTableName;
        return this;
    }

    @Override
    public IFileDataLoader build() {
        return FileDataLoader;
    }
}
