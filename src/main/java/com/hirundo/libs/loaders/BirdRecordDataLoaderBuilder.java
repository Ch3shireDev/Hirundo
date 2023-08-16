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

        var adapters = new java.util.ArrayList<IDbBirdRecordDataLoader>();

        if (null != oldTableName) {
            var oldBirdDataLoader = new AccessOldDbBirdRecordDataLoader(filename, oldTableName);
            var oldAdapter = new BirdDataLoaderAdapter(oldBirdDataLoader);
            adapters.add(oldAdapter);
        }

        if (null != newTableName) {
            var newBirdDataLoader = new AccessNewDbBirdRecordDataLoader(filename, newTableName);
            var newAdapter = new BirdDataLoaderAdapter(newBirdDataLoader);
            adapters.add(newAdapter);
        }

        return new FileDataLoader(adapters.toArray(IDbBirdRecordDataLoader[]::new));
    }

}
