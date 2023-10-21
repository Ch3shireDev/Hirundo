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

        var data = List.of(DbBirdRecord.from(record1),
                           DbBirdRecord.from(record2),
                           DbBirdRecord.from(record3),
                           DbBirdRecord.from(record4));

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

        var data = List.of(DbBirdRecord.from(record1),
                           DbBirdRecord.from(record2),
                           DbBirdRecord.from(record3),
                           DbBirdRecord.from(record4));
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
        record1.Age = "I";

        var record2 = new OldDbBirdRecord();
        record2.Spec = "XXX.YYY";
        record2.Ring = "1235";
        record2.Date = LocalDateTime.of(2019, 11, 1, 0, 0);
        record2.Seas = "A";
        record2.Age = "I";

        var record3 = new OldDbBirdRecord();
        record3.Spec = "XXX.YYY";
        record3.Ring = "1234";
        record3.Date = LocalDateTime.of(2020, 3, 1, 0, 0);
        record3.Seas = "S";
        record3.Age = "I";

        var record4 = new NewDbBirdRecord();
        record4.SpeciesCode = "XXX.YYY";
        record4.Ring = "1237";
        record4.Date2 = "2020-03-02";
        record4.Seas = "S";
        record4.Age = "I";

        var data = List.of(DbBirdRecord.from(record1),
                           DbBirdRecord.from(record2),
                           DbBirdRecord.from(record3),
                           DbBirdRecord.from(record4));

        var sex = BirdSex.Any;
        var species = new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix");

        BirdSpeciesCalculatedData calculatedData = calculator.getCalculatedData(data, species, sex);

        assertEquals(4, calculatedData.recordsCount());
        assertEquals("XXX.YYY", calculatedData.speciesCode());
        assertEquals("Dowolna", calculatedData.selectedSexName());
        assertEquals(1, calculatedData.returnsCount());
        assertEquals(4, calculatedData.recordsCount());
    }

    @Test
    public void ignoreBirdsThatWereCaughtFirstAtSpring() throws Exception {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "XXX.YYY";
        record1.Ring = "1234";
        record1.Date2 = "2019-03-01";
        record1.Seas = "S";

        var record2 = new NewDbBirdRecord();
        record2.SpeciesCode = "XXX.YYY";
        record2.Ring = "1234";
        record2.Date2 = "2019-11-01";
        record2.Seas = "A";

        var data = List.of(DbBirdRecord.from(record1),
                           DbBirdRecord.from(record2));

        var sex = BirdSex.Any;
        var species = new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix");

        BirdSpeciesCalculatedData calculatedData = calculator.getCalculatedData(data, species, sex);

        assertEquals(2, calculatedData.recordsCount());
        assertEquals("XXX.YYY", calculatedData.speciesCode());
        assertEquals("Dowolna", calculatedData.selectedSexName());
        assertEquals(0, calculatedData.returnsCount());
    }

    @Test
    public void dontIgnoreBirdThatWasCaughtTwoTimesInOneSeason() throws Exception {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "XXX.YYY";
        record1.Ring = "1234";
        record1.Date2 = "2019-11-01";
        record1.Seas = "A";
        record1.Age = "I";

        var record2 = new NewDbBirdRecord();
        record2.SpeciesCode = "XXX.YYY";
        record2.Ring = "1234";
        record2.Date2 = "2019-11-02";
        record2.Seas = "A";
        record2.Age = "I";

        var record3 = new NewDbBirdRecord();
        record3.SpeciesCode = "XXX.YYY";
        record3.Ring = "1234";
        record3.Date2 = "2020-03-01";
        record3.Seas = "S";
        record3.Age = "I";

        var data = List.of(DbBirdRecord.from(record1),
                           DbBirdRecord.from(record2),
                           DbBirdRecord.from(record3)
        );

        var sex = BirdSex.Any;
        var species = new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix");

        BirdSpeciesCalculatedData calculatedData = calculator.getCalculatedData(data, species, sex);

        assertEquals(3, calculatedData.recordsCount());
        assertEquals("XXX.YYY", calculatedData.speciesCode());
        assertEquals("Dowolna", calculatedData.selectedSexName());
        assertEquals(1, calculatedData.returnsCount());
    }

    @Test
    public void dontIgnoreBirdsThatWereCaughtInTwoAutumnsInTheRow() throws Exception {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "XXX.YYY";
        record1.Ring = "1234";
        record1.Date2 = "2019-11-01";
        record1.Seas = "A";
        record1.Age = "I";

        var record2 = new NewDbBirdRecord();
        record2.SpeciesCode = "XXX.YYY";
        record2.Ring = "1234";
        record2.Date2 = "2020-10-30";
        record2.Seas = "A";
        record2.Age = "I";

        var data = List.of(DbBirdRecord.from(record1),
                           DbBirdRecord.from(record2)
        );

        var sex = BirdSex.Any;
        var species = new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix");

        BirdSpeciesCalculatedData calculatedData = calculator.getCalculatedData(data, species, sex);

        assertEquals(2, calculatedData.recordsCount());
        assertEquals("XXX.YYY", calculatedData.speciesCode());
        assertEquals("Dowolna", calculatedData.selectedSexName());
        assertEquals(1, calculatedData.returnsCount());
    }

    @Test
    public void reversedOrderShouldNotBeAnIssue() throws Exception {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "XXX.YYY";
        record1.Ring = "1234";
        record1.Date2 = "2020-03-01";
        record1.Seas = "S";
        record1.Age = "I";

        var record2 = new NewDbBirdRecord();
        record2.SpeciesCode = "XXX.YYY";
        record2.Ring = "1234";
        record2.Date2 = "2019-11-01";
        record2.Seas = "A";
        record2.Age = "I";

        var data = List.of(DbBirdRecord.from(record1),
                           DbBirdRecord.from(record2)
        );

        var sex = BirdSex.Any;
        var species = new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix");

        BirdSpeciesCalculatedData calculatedData = calculator.getCalculatedData(data, species, sex);

        assertEquals(2, calculatedData.recordsCount());
        assertEquals("XXX.YYY", calculatedData.speciesCode());
        assertEquals("Dowolna", calculatedData.selectedSexName());
        assertEquals(1, calculatedData.returnsCount());
    }

    @Test
    public void birdShouldBeJuvenileOrYounger() throws Exception {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "XXX.YYY";
        record1.Ring = "1234";
        record1.Date2 = "2019-11-01";
        record1.Seas = "A";
        record1.Age = "J";

        var record2 = new NewDbBirdRecord();
        record2.SpeciesCode = "XXX.YYY";
        record2.Ring = "1234";
        record2.Date2 = "2020-03-01";
        record2.Seas = "S";

        var record3 = new NewDbBirdRecord();
        record3.SpeciesCode = "XXX.YYY";
        record3.Ring = "1235";
        record3.Date2 = "2019-11-01";
        record3.Seas = "A";
        record3.Age = "I";

        var record4 = new NewDbBirdRecord();
        record4.SpeciesCode = "XXX.YYY";
        record4.Ring = "1235";
        record4.Date2 = "2020-03-01";
        record4.Seas = "S";

        var record5 = new NewDbBirdRecord();
        record5.SpeciesCode = "XXX.YYY";
        record5.Ring = "1236";
        record5.Date2 = "2019-11-01";
        record5.Seas = "A";
        record5.Age = "A";

        var record6 = new NewDbBirdRecord();
        record6.SpeciesCode = "XXX.YYY";
        record6.Ring = "1236";
        record6.Date2 = "2020-03-01";
        record6.Seas = "S";

        var data = List.of(
                DbBirdRecord.from(record1),
                DbBirdRecord.from(record2),
                DbBirdRecord.from(record3),
                DbBirdRecord.from(record4),
                DbBirdRecord.from(record5),
                DbBirdRecord.from(record6)
        );

        var sex = BirdSex.Any;
        var species = new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix");

        BirdSpeciesCalculatedData calculatedData = calculator.getCalculatedData(data, species, sex);

        assertEquals(6, calculatedData.recordsCount());
        assertEquals("XXX.YYY", calculatedData.speciesCode());
        assertEquals("Dowolna", calculatedData.selectedSexName());
        assertEquals(2, calculatedData.returnsCount());
    }

    @Test
    public void nullSpeciesCodeShouldNotReturnAnError() throws Exception {
        var record = new DbBirdRecord();
        var selectedSpecies = new BirdSpecies("ABC", "Aaabin Bbbir", "Aaarus Bbbirix");

        var result = calculator.getCalculatedData(List.of(record), selectedSpecies, BirdSex.Any);

        assertEquals(0, result.recordsCount());
    }
}