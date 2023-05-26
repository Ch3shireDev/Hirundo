package com.hirundo.app.models;

import com.hirundo.app.services.IFileChooser;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.services.IBirdRecordDataLoaderBuilder;

import java.util.List;

public class MainModel {
    private final IBirdRecordDataLoaderBuilder builder;
    private final IFileChooser fileChooser;
    String selectedFileName;
    private String oldTableName;
    private String newTableName;


    public MainModel(IBirdRecordDataLoaderBuilder builder, IFileChooser fileChooser) {
        this.builder = builder;
        this.fileChooser = fileChooser;
    }

    public void loadData() throws Exception {

        var dataLoader = builder.withFilename(selectedFileName).withOldTableName(oldTableName).withNewTableName(newTableName).build();

        List<DbBirdRecord> data = dataLoader.loadData();

    }

    public String selectFileName() {
        var file = fileChooser.selectFileName();
        if (null != file) {
            selectedFileName = file;
        }
        return selectedFileName;
    }

    public String getSelectedFileName() {
        return selectedFileName;
    }

    public void setSelectedFileName(String selectedFileName) {
        this.selectedFileName = selectedFileName;
    }

    public void setNewTableName(String value) {
        this.newTableName = value;
    }

    public void setOldTableName(String value) {
        this.oldTableName = value;
    }
}

