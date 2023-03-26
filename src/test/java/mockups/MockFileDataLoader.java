package mockups;

import com.hirundo.libs.services.IFileDataLoader;

public class MockFileDataLoader implements IFileDataLoader {
    @Override
    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String FileName;
    public Boolean IsLoaded;

    @Override
    public void loadData() {
        IsLoaded = true;
    }
}
