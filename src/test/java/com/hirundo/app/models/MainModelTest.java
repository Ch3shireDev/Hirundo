package com.hirundo.app.models;

import com.hirundo.libs.data_structures.*;
import com.hirundo.libs.services.CsvReturningBirdsData;
import mockups.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainModelTest {
    MainModel model;
    MockBirdRecordDataLoaderBuilder builder;
    MockFileChooser fileChooser;
    MockFileDataLoader fileDataLoader;

    @BeforeEach
    void setUp() {
        fileDataLoader = new MockFileDataLoader();
        builder = new MockBirdRecordDataLoaderBuilder();
        builder.FileDataLoader = fileDataLoader;
        fileChooser = new MockFileChooser();
        model = new MainModel(builder, fileChooser);
    }

    @Test
    void getSingleSpecies() throws Exception {
        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "AAA.BBB";
        record1.EnglishName = "Aaabin Bbbir";
        record1.LatinName = "Aaarus Bbbirix";

        var record2 = new OldDbBirdRecord();
        record2.Spec = "AAA.BBB";

        fileDataLoader.Data = List.of(new DbBirdRecord(record2), new DbBirdRecord(record1));

        model.loadData();
        var speciesList = model.getSpeciesList();

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
    void getSpeciesNullData() throws Exception {
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

        fileDataLoader.Data = List.of(new DbBirdRecord(record1),
                                      new DbBirdRecord(record2),
                                      new DbBirdRecord(record3),
                                      new DbBirdRecord(record4),
                                      new DbBirdRecord(record5));

        model.loadData();
        var speciesList = model.getSpeciesList();

        assertEquals(1, speciesList.size());
    }

    @Test
    void getMultipleSpecies() throws Exception {
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

        fileDataLoader.Data = List.of(new DbBirdRecord(record1), new DbBirdRecord(record2), new DbBirdRecord(record3));

        model.loadData();
        var speciesList = model.getSpeciesList();

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

    @Test
    void getRecordsCount() throws Exception {
        var record1 = new NewDbBirdRecord();
        var record2 = new OldDbBirdRecord();
        var record3 = new NewDbBirdRecord();

        fileDataLoader.Data = List.of(new DbBirdRecord(record1), new DbBirdRecord(record2), new DbBirdRecord(record3));

        model.loadData();
        var recordsCount = model.getRecordsCount();

        assertEquals(3, recordsCount);
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

        fileDataLoader.Data = List.of(new DbBirdRecord(record1),
                                      new DbBirdRecord(record2),
                                      new DbBirdRecord(record3),
                                      new DbBirdRecord(record4));

        model.loadData();
        model.setSexSelected(BirdSex.Male);
        model.setSpeciesSelected(new BirdSpecies("AAA.BBB", "Aaabin Bbbir", "Aaarus Bbbirix"));
        BirdSpeciesCalculatedData calculatedData = model.getCalculatedData();

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

        fileDataLoader.Data = List.of(new DbBirdRecord(record1),
                                      new DbBirdRecord(record2),
                                      new DbBirdRecord(record3),
                                      new DbBirdRecord(record4));

        model.loadData();
        model.setSexSelected(BirdSex.Any);
        model.setSpeciesSelected(new BirdSpecies("AAA.BBB", "Aaabin Bbbir", "Aaarus Bbbirix"));
        BirdSpeciesCalculatedData calculatedData = model.getCalculatedData();

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

        fileDataLoader.Data = List.of(new DbBirdRecord(record1),
                                      new DbBirdRecord(record2),
                                      new DbBirdRecord(record3),
                                      new DbBirdRecord(record4));

        model.loadData();
        model.setSexSelected(BirdSex.Any);
        model.setSpeciesSelected(new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix"));
        BirdSpeciesCalculatedData calculatedData = model.getCalculatedData();

        assertEquals(4, calculatedData.recordsCount());
        assertEquals("XXX.YYY", calculatedData.speciesCode());
        assertEquals("Dowolna", calculatedData.selectedSexName());
        assertEquals(1, calculatedData.returnsCount());
        assertEquals(4, calculatedData.recordsCount());
    }

    @Test
    public void returnsCountSameSeasonTest() throws Exception {

        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "XXX.YYY";
        record1.Ring = "1234";
        record1.Date2 = "2019-11-01";
        record1.Seas = "A";

        var record2 = new OldDbBirdRecord();
        record2.Spec = "XXX.YYY";
        record2.Ring = "1234";
        record2.Date = LocalDateTime.of(2020, 1, 1, 0, 0);
        record2.Seas = "A";


        fileDataLoader.Data = List.of(new DbBirdRecord(record1), new DbBirdRecord(record2));

        model.loadData();
        model.setSexSelected(BirdSex.Any);
        model.setSpeciesSelected(new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix"));
        BirdSpeciesCalculatedData calculatedData = model.getCalculatedData();

        assertEquals(2, calculatedData.recordsCount());
        assertEquals(0, calculatedData.returnsCount());
    }

    @Test
    public void returnsCountDifferentSeasonTest() throws Exception {

        var record1 = new NewDbBirdRecord();
        record1.SpeciesCode = "XXX.YYY";
        record1.Ring = "1234";
        record1.Date2 = "2019-11-01";
        record1.Seas = "A";

        var r1 = new DbBirdRecord(record1);

        var record2 = new OldDbBirdRecord();
        record2.Spec = "XXX.YYY";
        record2.Ring = "1234";
        record2.Date = LocalDateTime.of(2020, 3, 1, 0, 0);
        record2.Seas = "S";
        var r2 = new DbBirdRecord(record2);

        var record3 = new OldDbBirdRecord();
        record3.Spec = "XXX.YYY";
        var r3 = new DbBirdRecord(record3);

        fileDataLoader.Data = List.of(r1, r2, r3);

        model.loadData();
        model.setSexSelected(BirdSex.Any);
        model.setSpeciesSelected(new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix"));
        BirdSpeciesCalculatedData calculatedData = model.getCalculatedData();

        assertEquals(3, calculatedData.recordsCount());
        assertEquals(1, calculatedData.returnsCount());
    }


    @Test
    public void writeResultsForSelectedSpeciesTest() throws Exception {
        var speciesFilter = new MockSpeciesFilter();
        speciesFilter.filteredRecords = List.of(new DbBirdRecord(new NewDbBirdRecord()),
                                                new DbBirdRecord(new NewDbBirdRecord()));

        model.speciesFilter = speciesFilter;

        var returningBirdsSummarizer = new MockReturningBirdsSummarizer();
        returningBirdsSummarizer.summary = List.of(new ReturningBirdsData());
        model.returningBirdsSummarizer = returningBirdsSummarizer;

        var mapper = new MockReturningBirdsDataCsvRecordMapper();

        model.mapper = mapper;

        var serializer = new MockCsvSerializer<CsvReturningBirdsData>();

        model.serializer = serializer;

        model.setSpeciesSelected(new BirdSpecies("XXX.YYY", "Aaabin Bbbir", "Aaarus Bbbirix"));

        model.writeResultsForSelectedSpecies();

        // 1. Z danych jest wyodrębniany dany gatunek.
        assertTrue(speciesFilter.isFilterCalled);
        assertEquals("XXX.YYY", speciesFilter.speciesToFilter.speciesCode());
        assertEquals("Aaabin Bbbir", speciesFilter.speciesToFilter.speciesNameEng());
        assertEquals("Aaarus Bbbirix", speciesFilter.speciesToFilter.speciesNameLat());
        // 2. Tworzone jest podsumowanie przez serwis.
        assertTrue(returningBirdsSummarizer.isCreateSummaryCalled);
        assertTrue(mapper.isMapperCalled);
        // 3. Podsumowanie jest przetwarzane do CSV przez serializer.
        assertTrue(serializer.isSerializeCalled);
        // 4. Od użytkownika jest pobierana nazwa pliku.
        // 5. Podsumowanie jest zapisywane do pliku.
    }
}

