package com.hirundo.libs.services;

public interface IBirdRecordDataLoaderBuilder {
    IBirdRecordDataLoaderBuilder withFilename(String filename);

    IBirdRecordDataLoaderBuilder withOldTableName(String oldTableName);
    IBirdRecordDataLoaderBuilder withNewTableName(String newTableName);

    IFileDataLoader build();

}
