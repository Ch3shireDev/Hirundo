package com.hirundo.app.view_models;

import com.hirundo.app.models.MainModel;
import mockups.MockDataLoader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class MainViewModelTest {

    MockDataLoader dataLoader;
    MainModel model;
    MainViewModel viewModel;

    @BeforeEach
    void setUp() {
        this.dataLoader = new MockDataLoader();
        this.model = new MainModel(this.dataLoader);
        this.viewModel = new MainViewModel(this.model);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void loadData() {
        final File file = new File("abc.mdb");

        this.viewModel.loadData(file);

        assertTrue(this.dataLoader.loadDataCalled);
    }
}

