package com.example.sklepshop.Controllers;

import com.example.sklepshop.Model.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class sceneMainController {

    @FXML
    private AnchorPane anchorMainScene;

    @FXML
    private CheckBox checkBoxCat1;

    @FXML
    private CheckBox checkBoxCat2;

    @FXML
    private CheckBox checkBoxCat3;

    @FXML
    private CheckBox checkBoxCat4;

    @FXML
    private CheckBox checkBoxCat5;

    @FXML
    private CheckBox checkBoxCat6;

    @FXML
    private CheckBox checkBoxCat7;

    @FXML
    private CheckBox checkBoxCat8;

    @FXML
    private CheckBox checkBoxCat9;

    @FXML
    private MenuItem menuLoadDatabase;

    @FXML
    private FlowPane productsPane;

    @FXML
    private RadioButton radioButton1;

    @FXML
    private RadioButton radioButton2;

    @FXML
    private RadioButton radioButton3;

    @FXML
    private RadioButton radioButton4;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchField;

    @FXML
    private TitledPane titledPaneCanetories;

//    @FXML
//    void onSwitchToLoadDatabase() throws IOException {
//        new SceneSwitch(anchorMainScene, "loadDatabase.fxml");
//    }

}

