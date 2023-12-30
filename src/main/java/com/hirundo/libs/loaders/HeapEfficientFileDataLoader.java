package com.hirundo.libs.loaders;

import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.data_structures.OldDbBirdRecord;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.hirundo.libs.loaders.AccessNewDbBirdRecordDataLoader.getNewDbBirdRecord;
import static com.hirundo.libs.loaders.AccessOldDbBirdRecordDataLoader.getOldDbBirdRecord;

public class HeapEfficientFileDataLoader implements IFileDataLoader {
    String filename;
    String oldTableName;
    String newTableName;

    public HeapEfficientFileDataLoader(String filename, String oldTableName, String newTableName) {
        this.filename = filename;
        this.oldTableName = oldTableName;
        this.newTableName = newTableName;
    }

    @Override
    public List<DbBirdRecord> loadData() throws Exception {
        var totalData = new java.util.ArrayList<DbBirdRecord>(2000000);

        try (var db = DatabaseBuilder.open(new File(filename))) {
            var oldTable = db.getTable(oldTableName);
            for (Row row : oldTable) {
                OldDbBirdRecord oldRecord = getOldDbBirdRecord(row);
                var record = DbBirdRecord.from(oldRecord);
                totalData.add(record);
            }

            var newTable = db.getTable(newTableName);
            for (Row row:newTable){
                NewDbBirdRecord newRecord = getNewDbBirdRecord(row);
                var record = DbBirdRecord.from(newRecord);
                totalData.add(record);
            }

        } catch (IOException e) {
            throw new RuntimeException("Błąd ładowania starej bazy danych. " + e.getMessage());
        }

        return totalData;
    }
}
