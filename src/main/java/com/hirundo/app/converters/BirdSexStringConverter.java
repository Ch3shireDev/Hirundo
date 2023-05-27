package com.hirundo.app.converters;

import com.hirundo.libs.data_structures.BirdSex;

public class BirdSexStringConverter extends javafx.util.StringConverter<com.hirundo.libs.data_structures.BirdSex> {
    @Override
    public String toString(BirdSex birdSex) {
        if (null == birdSex) return "";
        switch (birdSex) {

            case Undefined -> {
                return "";
            }
            case Male -> {
                return "Samiec";
            }
            case Any -> {
                return "Dowolna";
            }
            case Female -> {
                return "Samica";
            }
            default -> {
                throw new IllegalStateException("Unexpected value: " + birdSex);
            }
        }
    }

    @Override
    public BirdSex fromString(String s) {
        return null;
    }
}
