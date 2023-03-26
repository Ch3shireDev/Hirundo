package com.hirundo.app.view_models;

import com.hirundo.app.models.MainModel;

import java.io.File;

public class MainViewModel {
    public MainViewModel(MainModel model) {
        this.model = model;
    }
    private final MainModel model;

    public void loadData(File file) {
        model.loadData();
    }
}
