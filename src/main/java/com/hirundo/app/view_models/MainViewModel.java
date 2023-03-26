package com.hirundo.app.view_models;

import com.hirundo.app.models.MainModel;
import javafx.stage.FileChooser;

import java.io.File;

public class MainViewModel {
    public MainViewModel(MainModel model) {
        this.model = model;
    }
    private final MainModel model;

    public void loadData() {
        model.loadData();
    }

    public String selectFileName() {
        return model.selectFileName();
    }
}
