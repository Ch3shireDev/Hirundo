package com.hirundo.app.view_models;

import com.hirundo.app.models.MainModel;
import javafx.concurrent.Task;

public class MainViewModel {
    private final MainModel model;

    public MainViewModel(MainModel model) {
        this.model = model;
    }

    public void loadData() throws Exception {
        model.loadData();
    }

    public String selectFileName() {
        return model.selectFileName();
    }

    public void setNewTableName(String value) {
        this.model.setNewTableName(value);
    }

    public void setOldTableName(String value) {
        this.model.setOldTableName(value);
    }

    public void setSelectedFileName(String value) {
        this.model.setSelectedFileName(value);
    }
}
