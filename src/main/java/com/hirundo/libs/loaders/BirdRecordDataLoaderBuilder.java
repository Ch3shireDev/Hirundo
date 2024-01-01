package com.hirundo.libs.loaders;

public class BirdRecordDataLoaderBuilder implements IBirdRecordDataLoaderBuilder {
    String filename;
    private String oldTableName;
    private String newTableName;

    public BirdRecordDataLoaderBuilder withFilename(String filename) {
        this.filename = filename;
        return this;
    }

    public BirdRecordDataLoaderBuilder withOldTableName(String oldTableName) {
        this.oldTableName = oldTableName;
        return this;
    }

    public BirdRecordDataLoaderBuilder withNewTableName(String newTableName) {
        this.newTableName = newTableName;
        return this;
    }

    public IFileDataLoader build() {
        if (null == filename) {
            throw new IllegalStateException("Filename is not set");
        }

        return new HeapEfficientFileDataLoader(filename, oldTableName, newTableName);
    }

}
