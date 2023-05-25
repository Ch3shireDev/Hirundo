package com.hirundo.libs.services;

public class BirdRecordDataLoaderBuilder implements IBirdRecordDataLoaderBuilder{

    String filename;
    private String tableName;

    public BirdRecordDataLoaderBuilder withFilename(String filename) {
        filename = filename;
        return this;
    }
    public BirdRecordDataLoaderBuilder withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public IFileDataLoader build() {

        var oldBirdDataLoader = new AccessOldDbBirdRecordDataLoader(filename, tableName);
        var oldAdapter = new BirdDataLoaderAdapter(oldBirdDataLoader);
        var newBirdDataLoader = new AccessNewDbBirdRecordDataLoader(filename, tableName);
        var newAdapter = new BirdDataLoaderAdapter(newBirdDataLoader);

        var fileDataLoader = new FileDataLoader(oldAdapter, newAdapter);

        return fileDataLoader;
    }

}
