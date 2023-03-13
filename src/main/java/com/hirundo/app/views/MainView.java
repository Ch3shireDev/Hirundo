package com.hirundo.app.views;

import com.hirundo.app.view_models.MainViewModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainView implements Initializable {
    private Parent parent;

    public MainView() {
        super();
//        records = FXCollections.observableArrayList("aaa", "bbb", "ccc");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setViewModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    MainViewModel viewModel;

    public void setText(String abc) {
        text = new SimpleStringProperty(abc);
    }

    @FXML
    public StringProperty getText() {
        return text;
    }

    StringProperty text = new SimpleStringProperty("");

    public Parent getParent() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainView.fxml"),
                null,
                null,
                (x) -> this);

        return fxmlLoader.load();
    }

    public void setParent(Parent parent) {
        this.parent = parent;
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