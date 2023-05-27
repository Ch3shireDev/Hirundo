package com.hirundo.app.services;

import javafx.stage.FileChooser;

import java.io.File;

public class DialogFileChooser implements IFileChooser {
    File openDirectory = new File(".");
    File saveDirectory = new File(".");

    @Override
    public String selectFileToOpen() {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(openDirectory);
        final var file = fileChooser.showOpenDialog(null);
        if (null != file) {
            openDirectory = file.getParentFile();
            return file.getAbsolutePath();
        }
        return null;
    }

    @Override
    public String selectFileToSave(String exampleFilename) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialFileName(exampleFilename);
        fileChooser.setInitialDirectory(saveDirectory);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV", "*.csv"));
        final var file = fileChooser.showSaveDialog(null);
        if (null != file) {
            saveDirectory = file.getParentFile();
            return file.getAbsolutePath();
        }
        return null;
    }

}
