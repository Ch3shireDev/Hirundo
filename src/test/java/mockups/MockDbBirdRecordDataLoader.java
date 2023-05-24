package mockups;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.services.IDbBirdRecordDataLoader;

import java.util.List;

public class MockDbBirdRecordDataLoader implements IDbBirdRecordDataLoader {
    public Boolean IsLoaded;
    public List<DbBirdRecord> Data = new java.util.ArrayList<>();
    @Override
    public List<DbBirdRecord> loadData(String tableName) throws Exception {
        IsLoaded = true;
        return Data;
    }
}
