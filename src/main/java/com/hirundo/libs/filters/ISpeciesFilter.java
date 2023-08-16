package com.hirundo.libs.filters;

import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public interface ISpeciesFilter {
    List<DbBirdRecord> filterBySpecies(List<DbBirdRecord> records, BirdSpecies species);
    List<BirdSpecies> getSpeciesList(List<DbBirdRecord> data);
}
