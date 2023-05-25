package com.hirundo.libs.services;

public interface IBirdRecordDataLoaderBuilder {
    IBirdRecordDataLoaderBuilder withFilename(String filename);

    IBirdRecordDataLoaderBuilder withTableName(String tableName);

    IFileDataLoader build();
}
