package com.hirundo.app.services;

import javafx.stage.FileChooser;

public class DialogFileChooser implements IFileChooser {
    @Override
    public String selectFileName() {
        final FileChooser fileChooser = new FileChooser();
        final var file = fileChooser.showOpenDialog(null);
        if (null != file) {
            return file.getAbsolutePath();
        }
        return null;
    }

}
