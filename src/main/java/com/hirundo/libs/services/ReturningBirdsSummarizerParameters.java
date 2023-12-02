package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdSex;

import java.time.LocalDate;

public class ReturningBirdsSummarizerParameters {
    public LocalDate dateRangeStart;
    public LocalDate dateRangeEnd;
    public BirdSex sex;
    public String speciesCode;
    public boolean useDateRange;
}
