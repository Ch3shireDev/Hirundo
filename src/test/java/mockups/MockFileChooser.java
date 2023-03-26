package mockups;

import com.hirundo.app.models.IFileChooser;

public class MockFileChooser implements IFileChooser {
    public String FileName;

    @Override
    public String selectFileName() {
        return FileName;
    }
}
