package com.hirundo.app.converters;

import com.hirundo.libs.data_structures.BirdSpecies;
import javafx.util.StringConverter;

public class BirdSpeciesStringConverter extends StringConverter<BirdSpecies> {
    @Override
    public String toString(BirdSpecies object) {
        if (null == object) return null;
        if (null != object.speciesNameEng() && !object.speciesNameEng().isBlank()) {
            return String.format("%s (%s)", object.speciesCode(), object.speciesNameEng());
        }
        if(null != object.speciesNameLat() && !object.speciesNameLat().isBlank())
        {
            return String.format("%s (%s)", object.speciesCode(), object.speciesNameLat());
        }
        return object.speciesCode();
    }

    @Override
    public BirdSpecies fromString(String string) {
        return null;
    }
}
