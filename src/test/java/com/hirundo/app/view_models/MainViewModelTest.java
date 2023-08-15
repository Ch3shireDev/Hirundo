package com.hirundo.app.view_models;

import com.hirundo.app.models.MainModel;
import com.hirundo.mockups.MockBirdRecordDataLoaderBuilder;
import com.hirundo.mockups.MockFileChooser;
import com.hirundo.mockups.MockFileDataLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainViewModelTest {
    MockFileChooser fileChooser;
    MockFileDataLoader dataLoader;
    MainModel model;
    MainViewModel viewModel;
    MockBirdRecordDataLoaderBuilder builder;

    @BeforeEach
    void setUp() {
        fileChooser = new MockFileChooser();
        dataLoader = new MockFileDataLoader();
        builder = new MockBirdRecordDataLoaderBuilder();
        builder.FileDataLoader = dataLoader;
        model = new MainModel(builder, fileChooser);
        viewModel = new MainViewModel(model);
    }

    @Test
    void loadData() throws Exception {
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

}

