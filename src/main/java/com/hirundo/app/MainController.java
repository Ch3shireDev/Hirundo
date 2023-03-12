package com.hirundo.app;

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        tfText.textProperty().bindBidirectional(twoWayInputProperty());
////        lblLabel.textProperty().bind(twoWayInputProperty());
////        Bindings.bindBidirectional(twoWayInputProperty(), tfText2.textProperty());

    }

    private final StringProperty twoWayInput = new SimpleStringProperty("default value");

    public String getTwoWayInput()
    {
        return twoWayInput.get();
    }

    public StringProperty twoWayInputProperty()
    {
        return twoWayInput;
    }

    public void setTwoWayInput(String twoWayInput)
    {
        this.twoWayInput.set(twoWayInput);
    }
    @FXML
    private TextField tfText;
    @FXML
    private final ObservableList<String> records;

    private String name;

    @FXML
    public String getName() {
        return name;
    }

    @FXML
    public void setName(String value) {
        name = value;
    }

    @FXML
    public ObservableList<String> getRecords() {
        return records;
    }

    public MainController() {

        records = FXCollections.observableArrayList("aaa", "bbb", "ccc");

    }

    @FXML
    protected void onAddNewRecord() {
        addNewRecord();
//        listView.scrollTo(records.size()-1);
    }


    public void addNewRecord() {
        if (name == null || name.isBlank()) return;
        records.add(name.trim());
        System.out.println("New Record added");
    }
}