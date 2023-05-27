package com.hirundo.libs.data_structures;

public record BirdSpeciesCalculatedData(
        String speciesCode,
        String speciesNameEng,
        String speciesNameLatin,
        Integer recordsCount,
        Integer returnsCount) {
}
