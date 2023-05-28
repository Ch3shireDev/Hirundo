package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.DbBirdRecord;
import com.hirundo.libs.data_structures.NewDbBirdRecord;
import com.hirundo.libs.data_structures.OldDbBirdRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpeciesFilterTest {
    SpeciesFilter model;

    @BeforeEach
    void setUp() {
        model = new SpeciesFilter();
    }


    @Test
    void getSingleSpecies() {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "AAA.BBB";
        record1.EnglishName = "Aaabin Bbbir";
        record1.LatinName = "Aaarus Bbbirix";

        var record2 = new OldDbBirdRecord();
        record2.Spec = "AAA.BBB";

        var data = List.of(new DbBirdRecord(record2), new DbBirdRecord(record1));

        var speciesList = model.getSpeciesList(data);

        assertEquals(1, speciesList.size());
        assertEquals("AAA.BBB",
                     speciesList
                             .get(0)
                             .speciesCode());
        assertEquals("Aaabin Bbbir",
                     speciesList
                             .get(0)
                             .speciesNameEng());
        assertEquals("Aaarus Bbbirix",
                     speciesList
                             .get(0)
                             .speciesNameLat());
    }

    @Test
    void getSpeciesNullData() {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "AAA.BBB";
        record1.EnglishName = "Aaabin Bbbir";
        record1.LatinName = "Aaarus Bbbirix";

        var record2 = new OldDbBirdRecord();
        record2.Spec = null;

        var record3 = new NewDbBirdRecord();
        record3.SpeciesCode = "-";

        var record4 = new NewDbBirdRecord();
        record4.SpeciesCode = "?";

        var record5 = new NewDbBirdRecord();
        record5.SpeciesCode = "";

        var data = List.of(new DbBirdRecord(record1),
                           new DbBirdRecord(record2),
                           new DbBirdRecord(record3),
                           new DbBirdRecord(record4),
                           new DbBirdRecord(record5));

        var speciesList = model.getSpeciesList(data);

        assertEquals(1, speciesList.size());
    }

    @Test
    void getMultipleSpecies() {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "AAA.BBB";
        record1.EnglishName = "Aaabin Bbbir";
        record1.LatinName = "Aaarus Bbbirix";

        var record2 = new OldDbBirdRecord();
        record2.Spec = "BBB.CCC";

        var record3 = new NewDbBirdRecord();
        record3.SpeciesCode = "CCC.DDD";
        record3.EnglishName = "Cccin Dddir";
        record3.LatinName = "Cccus Dddirix";

        var data = List.of(new DbBirdRecord(record1), new DbBirdRecord(record2), new DbBirdRecord(record3));

        var speciesList = model.getSpeciesList(data);

        assertEquals(3, speciesList.size());
        assertEquals("AAA.BBB",
                     speciesList
                             .get(0)
                             .speciesCode());
        assertEquals("Aaabin Bbbir",
                     speciesList
                             .get(0)
                             .speciesNameEng());
        assertEquals("Aaarus Bbbirix",
                     speciesList
                             .get(0)
                             .speciesNameLat());
        assertEquals("BBB.CCC",
                     speciesList
                             .get(1)
                             .speciesCode());
        assertEquals("CCC.DDD",
                     speciesList
                             .get(2)
                             .speciesCode());
        assertEquals("Cccin Dddir",
                     speciesList
                             .get(2)
                             .speciesNameEng());
        assertEquals("Cccus Dddirix",
                     speciesList
                             .get(2)
                             .speciesNameLat());
    }

}