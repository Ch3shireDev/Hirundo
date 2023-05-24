package mockups;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.services.IFileDataLoader;

import java.util.List;

public class MockFileDataLoader implements IFileDataLoader {
    public String FileName;
    public Boolean IsLoaded;
    public String OldTableName;
    public String NewTableName1;
    private String NewTableName2;

    @Override
    public void setFileName(String fileName) {
        FileName = fileName;
    }

    @Override
    public List<DbBirdRecord> loadData(String tableName) throws Exception {
        IsLoaded = true;
        return null;
    }

}
