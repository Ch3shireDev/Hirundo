package mockups;

import com.hirundo.libs.data_structures.OldDbBirdRecord;
import com.hirundo.libs.services.IBirdRecordDataLoader;

import java.util.ArrayList;
import java.util.List;

public class MockOldDbBirdRecordDataLoader implements IBirdRecordDataLoader<OldDbBirdRecord> {
    public List<OldDbBirdRecord> Data = new ArrayList<>();
    public boolean IsLoaded;
    public String FileName;
    public String TableName;

    @Override
    public List<OldDbBirdRecord> loadData(String tableName) {
        IsLoaded = true;
        TableName = tableName;
        return Data;
    }

    @Override
    public void setFileName(String fileName) {
        FileName = fileName;
    }

}
