package com.hirundo.libs.filters;

import com.hirundo.libs.data_structures.BirdSpecies;
import com.hirundo.libs.data_structures.DbBirdRecord;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class SpeciesFilter implements ISpeciesFilter {
    public List<DbBirdRecord> filterBySpecies(List<DbBirdRecord> records, BirdSpecies species) {
        if (null == species) return records;
        return records.stream().filter(record -> isEqual(record, species)).toList();
    }

    boolean isEqual(DbBirdRecord record, BirdSpecies species) {
        return record.speciesCode != null && !record.speciesCode.isBlank() && record.speciesCode.equals(species.speciesCode());
    }

    public List<BirdSpecies> getSpeciesList(List<DbBirdRecord> data) {
        if (0 == data.size()) {
            return new ArrayList<>();
        }

        var species = data.stream().map(this::asSpecies).toList();

        var distinctSpecies = new HashMap<String, BirdSpecies>();

        for (var birdSpecies : species) {
            var key = birdSpecies.speciesCode();
            if (isEmpty(birdSpecies)) continue;
            if (!distinctSpecies.containsKey(key)) {
                distinctSpecies.put(key, birdSpecies);
            } else {
                var existingSpecies = distinctSpecies.get(key);
                if (missingNames(existingSpecies) && !missingNames(birdSpecies)) {
                    distinctSpecies.put(key, birdSpecies);
                }
            }
        }

        return distinctSpecies.values().stream().sorted(Comparator.comparing(BirdSpecies::speciesCode)).toList();
    }

    private Boolean isEmpty(BirdSpecies birdSpecies) {
        var key = birdSpecies.speciesCode();
        return (null == key || key.isBlank() || "-".equals(key) || "?".equals(key));
    }

    private BirdSpecies asSpecies(DbBirdRecord record) {
        return new BirdSpecies(record.getSpeciesCode(), record.getSpeciesNameEng(), record.getSpeciesNameLat());
    }

    private Boolean missingNames(BirdSpecies species) {
        var nameEng = species.speciesNameEng();
        var nameLat = species.speciesNameLat();
        return null == nameEng || null == nameLat || nameEng.isBlank() || nameLat.isBlank();
    }

}
