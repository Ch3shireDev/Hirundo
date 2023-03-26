package com.hirundo.app.view_models;

import com.hirundo.app.models.MainModel;
import mockups.MockFileDataLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainViewModelTest {

    MockFileDataLoader dataLoader;
    MainModel model;
    MainViewModel viewModel;

    @BeforeEach
    void setUp() {
        dataLoader = new MockFileDataLoader();
        model = new MainModel(dataLoader);
        viewModel = new MainViewModel(model);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void loadData() {
        final File file = new File("abc.mdb");

        viewModel.loadData(file);

        assertTrue(dataLoader.IsLoaded);
    }
}

