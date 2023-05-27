package com.hirundo.app.view_models;

import com.hirundo.app.models.MainModel;
import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.data_structures.BirdSpeciesCalculatedData;

import java.util.ArrayList;
import java.util.List;

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

    public void setFileName(String value) {
        this.model.setSelectedFileName(value);
    }

    public List<BirdSpecies> getSpeciesList() {
        return new ArrayList<>(List.of(new BirdSpecies("A", "B", "C"), new BirdSpecies("D", "E", "F"), new BirdSpecies("G", "H", "I")));
    }

    public BirdSpeciesCalculatedData getCalculatedData() {
        return new BirdSpeciesCalculatedData("A", "B", "C", 1, 2);
    }
}
