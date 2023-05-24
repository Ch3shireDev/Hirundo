package com.hirundo.app.view_models;

import com.hirundo.app.models.MainModel;

public class MainViewModel {
    private final MainModel model;

    public MainViewModel(MainModel model) {
        this.model = model;
    }

    public void loadData() throws Exception{
        model.loadData();
    }

    public String selectFileName() {
        return model.selectFileName();
    }
}
