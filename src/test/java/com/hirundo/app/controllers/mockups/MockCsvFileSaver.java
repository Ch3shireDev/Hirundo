package com.hirundo.app.controllers.mockups;

import com.hirundo.app.models.services.ICsvFileWriter;

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
