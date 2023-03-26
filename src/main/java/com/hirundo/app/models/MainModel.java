package com.hirundo.app.models;

import com.hirundo.libs.services.IFileDataLoader;
import javafx.stage.FileChooser;

import java.io.File;

public class MainModel {

    private final IFileDataLoader dataLoader;
    private final IFileChooser fileChooser;
    String selectedFileName;


    public MainModel(IFileDataLoader dataLoader, IFileChooser fileChooser) {
        this.dataLoader = dataLoader;
        this.fileChooser = fileChooser;
    }

    public void loadData() {
        dataLoader.setFileName(selectedFileName);
        dataLoader.loadData();
    }

    public String selectFileName() {
        var file = fileChooser.selectFileName();
        if(file != null) {
            selectedFileName = file;
        }
        return selectedFileName;
    }

    public String getSelectedFileName() {
        return selectedFileName;
    }

    public void setSelectedFileName(final String selectedFileName) {
        this.selectedFileName = selectedFileName;
    }
}

