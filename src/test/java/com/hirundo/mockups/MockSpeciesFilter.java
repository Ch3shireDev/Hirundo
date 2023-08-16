package com.hirundo.mockups;

import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.filters.ISpeciesFilter;

import java.util.ArrayList;
import java.util.List;

public class MockSpeciesFilter implements ISpeciesFilter {

    public List<DbBirdRecord> filteredRecords = new ArrayList<>();
    public Boolean isFilterCalled = false;
    public BirdSpecies speciesToFilter;
    public List<DbBirdRecord> records;

    @Override
    public List<DbBirdRecord> filterBySpecies(List<DbBirdRecord> records, BirdSpecies species) {
        isFilterCalled = true;
        speciesToFilter = species;
        this.records = records;
        return filteredRecords;
    }

    @Override
    public List<BirdSpecies> getSpeciesList(List<DbBirdRecord> data) {
        isGetSpeciesListCalled = true;
        return speciesList;
    }

    public Boolean isGetSpeciesListCalled = false;
    public List<BirdSpecies> speciesList = new ArrayList<>();
}
