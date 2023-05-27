package com.hirundo.app.models;

import com.hirundo.libs.data_structures.*;
import mockups.MockBirdRecordDataLoaderBuilder;
import mockups.MockFileChooser;
import mockups.MockFileDataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void writeResults() {
    }

    @Test
    void loadData() {
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
        assertEquals("AAA.BBB", speciesList.get(0).speciesCode());
        assertEquals("Aaabin Bbbir", speciesList.get(0).speciesNameEng());
        assertEquals("Aaarus Bbbirix", speciesList.get(0).speciesNameLat());
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

        fileDataLoader.Data = List.of(new DbBirdRecord(record1), new DbBirdRecord(record2), new DbBirdRecord(record3), new DbBirdRecord(record4), new DbBirdRecord(record5));

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
        assertEquals("AAA.BBB", speciesList.get(0).speciesCode());
        assertEquals("Aaabin Bbbir", speciesList.get(0).speciesNameEng());
        assertEquals("Aaarus Bbbirix", speciesList.get(0).speciesNameLat());
        assertEquals("BBB.CCC", speciesList.get(1).speciesCode());
        assertEquals("CCC.DDD", speciesList.get(2).speciesCode());
        assertEquals("Cccin Dddir", speciesList.get(2).speciesNameEng());
        assertEquals("Cccus Dddirix", speciesList.get(2).speciesNameLat());
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

        var record2 = new OldDbBirdRecord();
        record2.Spec = "AAA.BBB";
        record2.Sex = "M";

        var record3 = new OldDbBirdRecord();
        record3.Spec = "AAA.BBB";
        record3.Sex = "F";

        var record4 = new NewDbBirdRecord();
        record4.SpeciesCode = "BBB.CCC";

        fileDataLoader.Data = List.of(new DbBirdRecord(record1), new DbBirdRecord(record2), new DbBirdRecord(record3), new DbBirdRecord(record4));

        model.loadData();
        model.setSexSelected(BirdSex.Male);
        model.setSpeciesSelected(new BirdSpecies("AAA.BBB", "Aaabin Bbbir", "Aaarus Bbbirix"));
        BirdSpeciesCalculatedData calculatedData = model.getCalculatedData();

        assertEquals(2, calculatedData.recordsCount());
        assertEquals("AAA.BBB", calculatedData.speciesCode());
        assertEquals("Aaabin Bbbir", calculatedData.speciesNameEng());
        assertEquals("Aaarus Bbbirix", calculatedData.speciesNameLat());
        assertEquals("Samiec", calculatedData.selectedSexName());
    }
}

