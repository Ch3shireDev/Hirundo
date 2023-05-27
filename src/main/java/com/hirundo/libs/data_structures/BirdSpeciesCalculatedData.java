package com.hirundo.libs.data_structures;

public record BirdSpeciesCalculatedData(
        String speciesCode,
        String speciesNameEng,
        String speciesNameLat,
        String selectedSexName,
        Integer recordsCount,
        Integer returnsCount) {
}
