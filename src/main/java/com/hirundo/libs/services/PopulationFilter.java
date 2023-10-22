package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public class PopulationFilter {
    public List<DbBirdRecord> getPopulation(DbBirdRecord record, List<DbBirdRecord> records) {
        return List.of(records.get(0), records.get(1));
    }
}
