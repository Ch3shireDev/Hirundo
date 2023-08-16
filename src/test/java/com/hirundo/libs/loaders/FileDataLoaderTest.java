package com.hirundo.libs.loaders;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.mockups.MockDbBirdRecordDataLoader;
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
    void loadData() throws Exception {
        loader1.Data.add(new DbBirdRecord());
        loader1.Data.add(new DbBirdRecord());

        var data = dataLoader.loadData();

        Assertions.assertTrue(loader1.IsLoaded);
        Assertions.assertTrue(loader2.IsLoaded);
        Assertions.assertEquals(2, data.size());
    }
}