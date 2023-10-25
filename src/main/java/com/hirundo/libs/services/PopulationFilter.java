package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdAge;
import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.List;
import java.util.stream.Collectors;

public class PopulationFilter implements IPopulationFilter {
    PopulationFilterConfig config;

    public PopulationFilter() {
        this.config = PopulationFilterConfig.getAllTrue();
    }

    public PopulationFilter(PopulationFilterConfig config) {
        this.config = config;
    }

    public List<DbBirdRecord> getPopulation(DbBirdRecord record, List<DbBirdRecord> records) {
        var result = records
                .stream()
                .filter(r -> filterSpecies(record, r))
                .filter(r -> filterYear(record, r))
                .filter(this::filterAge)
                .toList();

        return filterFirstCaptures(result);
    }

    private List<DbBirdRecord> filterFirstCaptures(List<DbBirdRecord> result) {
        if (!config.FilterFirstCaptures) {
            return result;
        }

        var merge = result
                .stream()
                .collect(Collectors.toMap(r -> r.ring, r -> r, (r1, r2) -> r1.date.isBefore(r2.date) ? r1 : r2));

        result = merge
                .values()
                .stream()
                .toList();
        return result;
    }

    private boolean filterSpecies(DbBirdRecord record, DbBirdRecord r) {
        if (!config.FilterSpecies) return true;
        return record.speciesCode.equals(r.speciesCode);
    }

    private boolean filterAge(DbBirdRecord r) {
        if (!config.FilterAge) return true;
        return BirdAge.Infantile == r.age || BirdAge.Juvenile == r.age;
    }

    private boolean filterYear(DbBirdRecord record, DbBirdRecord r) {
        if (!config.FilterYear) return true;
        if (null == record.date || null == r.date) return false;
        return record.date.getYear() == r.date.getYear();
    }
}
