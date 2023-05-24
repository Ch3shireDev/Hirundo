package com.hirundo.libs.services;

import mockups.MockDbBirdRecordDataLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileDataLoaderTest {
    MockDbBirdRecordDataLoader loader1;
    MockDbBirdRecordDataLoader loader2;
    FileDataLoader dataLoader;

    @BeforeEach
    void setUp() {
        loader1 = new MockDbBirdRecordDataLoader();
        loader2 = new MockDbBirdRecordDataLoader();
        dataLoader = new FileDataLoader(loader1, loader2);
    }

    @Test
    void loadDataWithoutFilename() {
        Assertions.assertThrows(Exception.class, () -> dataLoader.loadData("example table"));
    }

    @Test
    void loadDataWithFilenameWithoutTables() throws Exception {
        dataLoader.setFileName("file.mdb");

        Assertions.assertThrows(Exception.class, () -> dataLoader.loadData(null));
    }
}