package com.hirundo.app.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public MainController() {
        super();
//        records = FXCollections.observableArrayList("aaa", "bbb", "ccc");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
//
//    private final StringProperty twoWayInput = new SimpleStringProperty("default value");
//
//    @FXML
//    private final ObservableList<String> records;
//
//    private String name;
//
//    @FXML
//    public String getName() {
//        return name;
//    }
//
//    @FXML
//    public void setName(String value) {
//        name = value;
//    }
//
//    @FXML
//    public ObservableList<String> getRecords() {
//        return records;
//    }
//
//    @FXML
//    protected void onAddNewRecord() {
//        addNewRecord();
////        listView.scrollTo(records.size()-1);
//    }
//
//
//    public void addNewRecord() {
//        if (name == null || name.isBlank()) return;
//        records.add(name.trim());
//        System.out.println("New Record added");
//    }
}