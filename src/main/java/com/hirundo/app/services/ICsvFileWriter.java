package com.hirundo.app.services;

public interface ICsvFileWriter {
    void writeToFile(String filepath, String data) throws Exception;
}
