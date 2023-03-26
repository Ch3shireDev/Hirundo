package com.hirundo.app.models;

import com.hirundo.libs.services.IFileDataLoader;

public class MainModel {

    private final IFileDataLoader dataLoader;

    public MainModel(final IFileDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }
    public void loadData() {
        dataLoader.setFileName("abc.txt");
        dataLoader.loadData();
    }
}

