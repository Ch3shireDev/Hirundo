package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;

public interface IPopulationFilter {
    List<DbBirdRecord> getPopulation(DbBirdRecord record, List<DbBirdRecord> records);
}
