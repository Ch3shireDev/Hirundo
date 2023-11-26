package com.hirundo.libs.data_structures;

public enum BirdAge {
    Infantile, Juvenile, Adult, Other, Undefined;

    public static BirdAge stringToEnum(String age) {
        if (null == age) return Undefined;

        if (age.isEmpty()) return Undefined;

        return switch (age) {
            case "I" -> Infantile;
            case "J" -> Juvenile;
            case "A" -> Adult;
            default -> Other;
        };
    }
}
