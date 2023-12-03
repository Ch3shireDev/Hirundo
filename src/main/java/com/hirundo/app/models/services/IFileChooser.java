package com.hirundo.app.models.services;

public interface IFileChooser {
    String selectFileToOpen();

    String selectFileToSave(String exampleFilename);
}
