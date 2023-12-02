package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSex;
import com.hirundo.libs.data_structures.BirdSpecies;

import java.time.LocalDate;

public class ReturnsStatisticsCalculatorParameters {
    public BirdSpecies species;
    public BirdSex selectedSex;
    public Boolean isDateRangeSelected;
    public LocalDate dateRangeStart;
    public LocalDate dateRangeEnd;
}
