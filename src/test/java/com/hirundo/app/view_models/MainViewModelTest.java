package com.hirundo.app.view_models;

import com.hirundo.app.models.MainModel;
import mockups.MockFileChooser;
import mockups.MockFileDataLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainViewModelTest {
MockFileChooser fileChooser;
    MockFileDataLoader dataLoader;
    MainModel model;
    MainViewModel viewModel;

    @BeforeEach
    void setUp() {
        fileChooser = new MockFileChooser();
        dataLoader = new MockFileDataLoader();
        model = new MainModel(dataLoader, fileChooser);
        viewModel = new MainViewModel(model);
    }

    @Test
    void loadData() throws Exception{
        dataLoader.IsLoaded = false;

        viewModel.loadData();

        assertTrue(dataLoader.IsLoaded);
    }

    @Test
    void selectFileName() {
        fileChooser.FileName = "file.mdb";

        final String selectedFileName = viewModel.selectFileName();

        assertEquals("file.mdb", selectedFileName);
        assertEquals("file.mdb", model.getSelectedFileName());
    }

    @Test
    void selectFileAndLoad() throws Exception{
        final String fileName = "file.mdb";
        fileChooser.FileName = fileName;
        final File file = new File(fileName);

        viewModel.selectFileName();
        viewModel.loadData();

        assertTrue(dataLoader.IsLoaded);
        assertEquals("file.mdb", dataLoader.FileName);
    }
}

