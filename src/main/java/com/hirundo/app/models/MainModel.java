package com.hirundo.app.models;

import com.hirundo.libs.services.INewDbBirdRecordDataLoader;

public class MainModel {

    private final INewDbBirdRecordDataLoader dataLoader;

    public MainModel(INewDbBirdRecordDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }
    public void loadData() {
//        final var filename = "abc.txt";
        dataLoader.loadData();
    }
}

