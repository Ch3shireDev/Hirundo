package com.hirundo.app.view_models;

import com.hirundo.app.models.FileSaveResult;
import com.hirundo.app.models.MainModel;
import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.data_structures.BirdSpeciesCalculatedData;

import java.time.LocalDate;
import java.util.List;

// TODO: Pozbyć się ViewModel, w ewidentny sposób nie jest potrzebny.
public class MainViewModel {
    private final MainModel model;

    public MainViewModel(MainModel model) {
        this.model = model;
    }

    public void loadData() throws Exception {
        model.loadData();
    }

    public String getSelectedFileName() {
        return model.getSelectedFileName();
    }

    public String selectFileName() {
        return model.selectFileName();
    }

    public FileSaveResult writeResultsForSelectedSpecies() throws Exception {
        return model.writeResultsForSelectedSpecies();
    }

    public FileSaveResult writeResultsForAllSpecies() throws Exception {
        return model.writeResultsForAllSpecies();
    }

    public void setNewTableName(String value) {
        model.setNewTableName(value);
    }

    public void setOldTableName(String value) {
        model.setOldTableName(value);
    }

    public List<BirdSpecies> getSpeciesList() {
        return model.getSpeciesList();
    }

    public BirdSpeciesCalculatedData getCalculatedData() throws Exception {
        return model.getCalculatedData();
    }

    public Integer getRecordsCount() {
        return model.getRecordsCount();
    }

    public void setSpeciesSelected(BirdSpecies species) {
        model.setSpeciesSelected(species);
    }

    public void setSexSelected(BirdSex sex) {
        model.setSexSelected(sex);
    }

    public void setIsDateRangeSelected(boolean selected) {
        model.setIsDateRangeSelected(selected);
    }

    public void setStartDate(LocalDate startDate) {
        model.setDateRangeStart(startDate);
    }

    public void setEndDate(LocalDate endDate) {
        model.setDateRangeEnd(endDate);
    }
}
