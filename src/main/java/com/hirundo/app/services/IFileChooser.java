package com.hirundo.app.services;

public interface IFileChooser {
    String selectFileToOpen();

    String selectFileToSave(String exampleFilename);
}
