package com.hirundo.app.models.mockups;

import com.hirundo.app.models.ICsvFileWriter;

public class MockCsvFileSaver implements ICsvFileWriter {
    public String FileName;
    public boolean isSaveToFileCalled;
    public String fileData;

    @Override
    public void writeToFile(String filepath, String data) {
        FileName = filepath;
        fileData = data;
        isSaveToFileCalled = true;
    }
}
