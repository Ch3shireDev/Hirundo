package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdAge;
import com.hirundo.libs.data_structures.DbBirdRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class PopulationFilterTest {

    PopulationFilter filter;

    @BeforeEach
    void setUp() {
        filter = new PopulationFilter();
    }

    @Test
    void populationShouldBeOfTheSameSpecies() {
        var record = new DbBirdRecord();
        record.speciesCode = "XXX.YYY";

        var r1 = new DbBirdRecord();
        r1.speciesCode = "XXX.YYY";

        var r2 = new DbBirdRecord();
        r2.speciesCode = "XXX.YYY";

        var r3 = new DbBirdRecord();
        r3.speciesCode = "ZZZ.AAA";

        var records = List.of(r1, r2, r3);

        var population = filter.getPopulation(record, records);

        assertNotEquals(0, population.size());
        assertEquals(2, population.size());
    }

    @Test
    void populationShouldBeJuvenile() {
        var record = new DbBirdRecord();

        var r1 = new DbBirdRecord();
        r1.age = BirdAge.Infantile;
    }
}