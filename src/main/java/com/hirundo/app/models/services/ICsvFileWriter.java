package com.hirundo.app.models.services;

public interface ICsvFileWriter {
    void writeToFile(String filepath, String data) throws Exception;
}
