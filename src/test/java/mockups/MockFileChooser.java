package mockups;

import com.hirundo.app.services.IFileChooser;

public class MockFileChooser implements IFileChooser {
    public String FileName;

    @Override
    public String selectFileName() {
        return FileName;
    }
}
