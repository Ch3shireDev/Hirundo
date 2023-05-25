package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.data_structures.OldDbBirdRecord;
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
    void loadData() throws Exception {
        loader1.Data.add(new DbBirdRecord(new NewDbBirdRecord()));
        loader2.Data.add(new DbBirdRecord(new OldDbBirdRecord()));

        var data = dataLoader.loadData();

        Assertions.assertTrue(loader1.IsLoaded);
        Assertions.assertTrue(loader2.IsLoaded);
        Assertions.assertEquals(2, data.size());
    }
}