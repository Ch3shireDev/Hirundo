package com.hirundo.libs.services;

import com.hirundo.libs.data_structures.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReturnsStatisticsCalculatorTest {
    ReturnsStatisticsCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new ReturnsStatisticsCalculator();
    }

    @Test
    void getCalculatedData() throws Exception {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "AAA.BBB";
        record1.EnglishName = "Aaabin Bbbir";
        record1.LatinName = "Aaarus Bbbirix";
        record1.Sex = "M";
        record1.Ring = "1234";

        var record2 = new OldDbBirdRecord();
        record2.Spec = "AAA.BBB";
        record2.Sex = "M";
        record2.Ring = "1235";

        var record3 = new OldDbBirdRecord();
        record3.Spec = "AAA.BBB";
        record3.Sex = "F";
        record3.Ring = "1236";

        var record4 = new NewDbBirdRecord();
        record4.SpeciesCode = "BBB.CCC";
        record4.Ring = "1237";

        var data = List.of(new DbBirdRecord(record1),
                           new DbBirdRecord(record2),
                           new DbBirdRecord(record3),
                           new DbBirdRecord(record4));

        var sex = BirdSex.Male;
        var species = new BirdSpecies("AAA.BBB", "Aaabin Bbbir", "Aaarus Bbbirix");

        BirdSpeciesCalculatedData calculatedData = calculator.getCalculatedData(data, species, sex);

        assertEquals(2, calculatedData.recordsCount());
        assertEquals("AAA.BBB", calculatedData.speciesCode());
        assertEquals("Aaabin Bbbir", calculatedData.speciesNameEng());
        assertEquals("Aaarus Bbbirix", calculatedData.speciesNameLat());
        assertEquals("Samiec", calculatedData.selectedSexName());
        assertEquals(0, calculatedData.returnsCount());
    }

    @Test
    void getCalculatedDataAnySex() throws Exception {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "AAA.BBB";
        record1.Sex = "M";
        record1.Ring = "1234";

        var record2 = new OldDbBirdRecord();
        record2.Spec = "AAA.BBB";
        record2.Sex = "M";
        record2.Ring = "1235";

        var record3 = new OldDbBirdRecord();
        record3.Spec = "AAA.BBB";
        record3.Sex = "F";
        record3.Ring = "1236";

        var record4 = new NewDbBirdRecord();
        record4.SpeciesCode = "BBB.CCC";
        record4.Ring = "1237";

        var data = List.of(new DbBirdRecord(record1),
                           new DbBirdRecord(record2),
                           new DbBirdRecord(record3),
                           new DbBirdRecord(record4));
        var sex = BirdSex.Any;
        var species = new BirdSpecies("AAA.BBB", "Aaabin Bbbir", "Aaarus Bbbirix");

        BirdSpeciesCalculatedData calculatedData = calculator.getCalculatedData(data, species, sex);

        assertEquals(3, calculatedData.recordsCount());
        assertEquals("AAA.BBB", calculatedData.speciesCode());
        assertEquals("Dowolna", calculatedData.selectedSexName());
        assertEquals(0, calculatedData.returnsCount());
    }

    @Test
    public void getCalculatedDataReturningBirds() throws Exception {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "XXX.YYY";
        record1.Ring = "1234";
        record1.Date2 = "2019-11-01";
        record1.Seas = "A";

        var record2 = new OldDbBirdRecord();
        record2.Spec = "XXX.YYY";
        record2.Ring = "1235";
        record2.Date = LocalDateTime.of(2019, 11, 1, 0, 0);
        record2.Seas = "A";

        var record3 = new OldDbBirdRecord();
        record3.Spec = "XXX.YYY";
        record3.Ring = "1234";
        record3.Date = LocalDateTime.of(2020, 3, 1, 0, 0);
        record3.Seas = "S";

        var record4 = new NewDbBirdRecord();
        record4.SpeciesCode = "XXX.YYY";
        record4.Ring = "1237";
        record4.Date2 = "2020-03-02";
        record4.Seas = "S";

        var data = List.of(new DbBirdRecord(record1),
                           new DbBirdRecord(record2),
                           new DbBirdRecord(record3),
                           new DbBirdRecord(record4));
        var sex = BirdSex.Any;
        var species = new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix");

        BirdSpeciesCalculatedData calculatedData = calculator.getCalculatedData(data, species, sex);

        assertEquals(4, calculatedData.recordsCount());
        assertEquals("XXX.YYY", calculatedData.speciesCode());
        assertEquals("Dowolna", calculatedData.selectedSexName());
        assertEquals(1, calculatedData.returnsCount());
        assertEquals(4, calculatedData.recordsCount());
    }
}