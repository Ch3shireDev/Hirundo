package com.hirundo.app.models;

import com.hirundo.libs.services.IDataLoader;

public class MainModel {

    private final IDataLoader dataLoader;

    public MainModel(final IDataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }
    public void loadData() {
        final var filename = "abc.txt";
        dataLoader.loadData(filename);
    }
}

