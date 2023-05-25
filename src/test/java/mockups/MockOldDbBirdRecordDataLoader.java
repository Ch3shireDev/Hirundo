package mockups;

import com.hirundo.libs.data_structures.OldDbBirdRecord;
import com.hirundo.libs.services.IBirdRecordDataLoader;

import java.util.ArrayList;
import java.util.List;

public class MockOldDbBirdRecordDataLoader implements IBirdRecordDataLoader<OldDbBirdRecord> {
    public List<OldDbBirdRecord> Data = new ArrayList<>();
    public boolean IsLoaded;

    @Override
    public List<OldDbBirdRecord> loadData() {
        IsLoaded = true;
        return Data;
    }

}
