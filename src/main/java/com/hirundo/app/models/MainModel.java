package com.hirundo.app.models;

import com.hirundo.app.services.IFileChooser;
import com.hirundo.libs.services.IFileDataLoader;

public class MainModel {

    private final IFileDataLoader dataLoader;
    private final IFileChooser fileChooser;
    String selectedFileName;


    public MainModel(IFileDataLoader dataLoader, IFileChooser fileChooser) {
        this.dataLoader = dataLoader;
        this.fileChooser = fileChooser;
    }

    public void loadData() throws Exception {
        dataLoader.setFileName(selectedFileName);
//        dataLoader.setOldRecordsTableName("IdrZesz1");
//        dataLoader.setNewRecordsTableName1("Tab_Ring_Podab");
//        dataLoader.setNewRecordsTableName2("AB 2017_18_19_20_21S");
        var data = dataLoader.loadData("IdrZesz1");
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

    public void setSelectedFileName(final String selectedFileName) {
        this.selectedFileName = selectedFileName;
    }
}

