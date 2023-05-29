package com.hirundo.app.models;

public interface ICsvFileWriter {
    void writeToFile(String filepath, String data) throws Exception;
}
