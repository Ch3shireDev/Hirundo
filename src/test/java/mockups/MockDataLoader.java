package mockups;

import com.hirundo.libs.services.IDataLoader;

public class MockDataLoader implements IDataLoader {
    public boolean loadDataCalled;

    @Override
    public void loadData(final String filename) {
        this.loadDataCalled = true;
    }
}
