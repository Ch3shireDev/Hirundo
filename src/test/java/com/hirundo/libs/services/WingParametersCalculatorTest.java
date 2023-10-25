package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WingParametersCalculatorTest {
    WingParametersCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new WingParametersCalculator();
    }

    @Test
    public void getPointedness() {
        var r1 = new DbBirdRecord();
        r1.wing = new BigDecimal("60.0");
        r1.d2 = 2;
        r1.d3 = 3;
        r1.d4 = 4;
        r1.d5 = 0;
        r1.d6 = 6;
        r1.d7 = 7;
        r1.d8 = 8;

        var pointedness = calculator.getPointednessFactor(r1);

        assertEquals(new BigDecimal("0.500"), pointedness);
    }

    @Test
    public void getSymmetry() {
        var r1 = new DbBirdRecord();

        r1.wing = new BigDecimal("60.0");
        r1.d2 = 2;
        r1.d3 = 3;
        r1.d4 = 4;
        r1.d5 = 0;
        r1.d6 = 6;
        r1.d7 = 7;
        r1.d8 = 8;

        var symmetry = calculator.getSymmetryFactor(r1);

        assertEquals(new BigDecimal("0.400"), symmetry);
    }
}
