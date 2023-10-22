package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.BirdAge;
import com.hirundo.libs.data_structures.DbBirdRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PopulationFilterTest {

    PopulationFilter filter;
    PopulationFilterConfig config;

    @BeforeEach
    void setUp() {
        config = PopulationFilterConfig.getAllFalse();
        filter = new PopulationFilter(config);
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

        config.FilterSpecies = true;
        var population = filter.getPopulation(record, records);

        assertNotEquals(0, population.size());
        assertEquals(2, population.size());
    }

    @Test
    void populationShouldBeJuvenileOrInfantile() {
        var record = new DbBirdRecord();

        var r1 = new DbBirdRecord();
        r1.age = BirdAge.Infantile;

        var r2 = new DbBirdRecord();
        r2.age = BirdAge.Adult;

        var r3 = new DbBirdRecord();
        r3.age = BirdAge.Juvenile;

        var records = List.of(r1, r2, r3);

        config.FilterAge = true;
        var population = filter.getPopulation(record, records);

        assertNotEquals(0, population.size());
        assertEquals(2, population.size());
        assertEquals(BirdAge.Infantile, population.get(0).age);
        assertEquals(BirdAge.Juvenile, population.get(1).age);
    }

    @Test
    void populationShouldBeFromTheSameYear(){
        var record = new DbBirdRecord();
        record.date = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

        var r1 = new DbBirdRecord();
        r1.date = LocalDateTime.of(2020, 1, 2, 0, 0, 0);

        var r2 = new DbBirdRecord();
        r2.date = LocalDateTime.of(2021, 1, 3, 0, 0, 0);

        var r3 = new DbBirdRecord();
        r3.date = LocalDateTime.of(2020, 2, 4, 0, 0, 0);

        var r4 = new DbBirdRecord();
        r4.date = LocalDateTime.of(2020, 3, 5, 0, 0, 0);

        var records = List.of(r1, r2, r3, r4);

        config.FilterYear = true;
        var population = filter.getPopulation(record, records);

        assertNotEquals(0, population.size());
        assertEquals(3, population.size());
        assertEquals(LocalDateTime.of(2020, 1, 2, 0, 0, 0), population.get(0).date);
        assertEquals(LocalDateTime.of(2020, 2, 4, 0, 0, 0), population.get(1).date);
        assertEquals(LocalDateTime.of(2020, 3, 5, 0, 0, 0), population.get(2).date);
    }

    @Test
    public void countOnlyFirstCaptures(){
        var record = new DbBirdRecord();

        var r1 = new DbBirdRecord();
        r1.ring = "AB001";
        r1.date = LocalDateTime.of(2020, 1, 1, 0, 0, 0);

        var r2 = new DbBirdRecord();
        r2.ring = "AB001";
        r2.date = LocalDateTime.of(2020, 1, 2, 0, 0, 0);

        var r3 = new DbBirdRecord();
        r3.ring = "AB002";
        r3.date = LocalDateTime.of(2020, 1, 3, 0, 0, 0);

        var r4 = new DbBirdRecord();
        r4.ring = "AB001";
        r4.date = LocalDateTime.of(2021, 1, 4, 0, 0, 0);

        var records = List.of(r1, r2, r3, r4);

        config.FilterFirstCaptures = true;
        var population = filter.getPopulation(record, records);

        assertNotEquals(0, population.size());
        assertEquals(2, population.size());
        assertEquals("AB001", population.get(0).ring);
        assertEquals("AB002", population.get(1).ring);
        assertEquals(LocalDateTime.of(2020, 1, 1, 0, 0, 0), population.get(0).date);
        assertEquals(LocalDateTime.of(2020, 1, 3, 0, 0, 0), population.get(1).date);
    }
}