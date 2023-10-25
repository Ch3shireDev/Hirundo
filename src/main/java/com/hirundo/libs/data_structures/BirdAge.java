package com.hirundo.libs.data_structures;

public enum BirdAge {
    Infantile, Juvenile, Adult, Other, Undefined;

    public static BirdAge stringToEnum(String age) {
        if (null == age) return BirdAge.Undefined;

        if (age.isEmpty()) return BirdAge.Undefined;

        return switch (age) {
            case "I" -> BirdAge.Infantile;
            case "J" -> BirdAge.Juvenile;
            case "A" -> BirdAge.Adult;
            default -> BirdAge.Other;
        };
    }
}
