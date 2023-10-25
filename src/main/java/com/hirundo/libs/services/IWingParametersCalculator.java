package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;

import java.math.BigDecimal;

public interface IWingParametersCalculator {
    BigDecimal getPointednessFactor(DbBirdRecord record);
    BigDecimal getSymmetryFactor(DbBirdRecord record);
}
