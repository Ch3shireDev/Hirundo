package com.hirundo.libs.services;

public class PopulationFilterConfig {
    public boolean FilterSpecies;
    public boolean FilterAge;
    public boolean FilterYear;
    public boolean FilterFirstCaptures;

    public static PopulationFilterConfig getAllTrue(){
        var config = new PopulationFilterConfig();
        config.FilterSpecies = true;
        config.FilterAge = true;
        config.FilterYear = true;
        config.FilterFirstCaptures = true;
        return config;
    }

    public static PopulationFilterConfig getAllFalse(){
        var config = new PopulationFilterConfig();
        config.FilterSpecies = false;
        config.FilterAge = false;
        config.FilterYear = false;
        config.FilterFirstCaptures = false;
        return config;
    }
}
