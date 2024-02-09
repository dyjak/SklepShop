package com.example.sklepshop.Controllers;
import com.example.sklepshop.Model.SceneSwitch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class sceneLoadDatabaseController {


        @FXML
        private AnchorPane anchorLoadDataBaseScene;

        @FXML
        void onSwitchToMain() throws IOException {
                new SceneSwitch(anchorLoadDataBaseScene, "productstyle.fxml");
        }

    }


