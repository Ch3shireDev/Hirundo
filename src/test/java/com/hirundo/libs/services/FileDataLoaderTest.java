package com.hirundo.libs.services;

import mockups.MockNewDbBirdRecordDataLoader;
import mockups.MockOldDbBirdRecordDataLoader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FileDataLoaderTest {
    MockNewDbBirdRecordDataLoader newDbBirdRecordDataLoader;
    MockOldDbBirdRecordDataLoader oldDbBirdRecordDataLoader;
    FileDataLoader dataLoader;

    @BeforeEach
    void setUp() {
        newDbBirdRecordDataLoader = new MockNewDbBirdRecordDataLoader();
        oldDbBirdRecordDataLoader = new MockOldDbBirdRecordDataLoader();
        dataLoader = new FileDataLoader(oldDbBirdRecordDataLoader, newDbBirdRecordDataLoader);
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