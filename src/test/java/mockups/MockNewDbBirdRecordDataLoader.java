package mockups;

import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.services.INewDbBirdRecordDataLoader;

import java.util.ArrayList;
import java.util.List;

public class MockNewDbBirdRecordDataLoader implements INewDbBirdRecordDataLoader {
    public boolean loadDataCalled;
    public List<NewDbBirdRecord> Data = new ArrayList<>();

    @Override
    public List<NewDbBirdRecord> loadData() {
        loadDataCalled = true;
        return Data;
    }
}
