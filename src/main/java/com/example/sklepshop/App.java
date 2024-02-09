package com.example.sklepshop;

import com.example.sklepshop.Classes.EditProductsDB;
import com.example.sklepshop.Classes.Settlements;
import com.example.sklepshop.entities.AddedProduct;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.example.sklepshop.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.sklepshop.*;

import static com.example.sklepshop.Classes.Products_LoadProducts.loadProducts;
import static com.example.sklepshop.Classes.Products_LoadProducts.loadProductsControls;
import static java.lang.System.in;


public class App extends Application
{
    public static String dbPath = "C:/Users/Dyjak/Desktop/products.db";
    public static ArrayList<AddedProduct> addedProduct_x = new ArrayList<>();
    public static DecimalFormat df = new DecimalFormat("#.00");

    //category images
    public static Image categoryIconImageView_1 = new Image(App.class.getResourceAsStream("canvas/category_icons/cat_icon_1.png"));public static Image categoryIconImageView_2 = new Image(App.class.getResourceAsStream("canvas/category_icons/cat_icon_2.png"));public static Image categoryIconImageView_3 = new Image(App.class.getResourceAsStream("canvas/category_icons/cat_icon_3.png"));public static Image categoryIconImageView_4 = new Image(App.class.getResourceAsStream("canvas/category_icons/cat_icon_4.png"));public static Image categoryIconImageView_5 = new Image(App.class.getResourceAsStream("canvas/category_icons/cat_icon_5.png"));public static Image categoryIconImageView_6 = new Image(App.class.getResourceAsStream("canvas/category_icons/cat_icon_6.png"));public static Image categoryIconImageView_7 = new Image(App.class.getResourceAsStream("canvas/category_icons/cat_icon_7.png"));public static Image categoryIconImageView_8 = new Image(App.class.getResourceAsStream("canvas/category_icons/cat_icon_8.png"));public static Image categoryIconImageView_9 = new Image(App.class.getResourceAsStream("canvas/category_icons/cat_icon_9.png"));


    @Override
    public void start(Stage stage) throws Exception {


            //FXML///////////////////////////////////////////////////////////////////////////////////////////////////////////////
            FXMLLoader mainLoader = new FXMLLoader((getClass().getResource("fxmls/loadDBstyle.fxml")));
            Parent mainRoot = mainLoader.load(); //choosingDB

            FXMLLoader productsLoader = new FXMLLoader((getClass().getResource("fxmls/productstyle.fxml")));
            Parent productsRoot = productsLoader.load();

            FXMLLoader settlementsLoader = new FXMLLoader((getClass().getResource("fxmls/settlementstyle.fxml")));
            Parent settlementsRoot = settlementsLoader.load();

            FXMLLoader editDBLoader = new FXMLLoader((getClass().getResource("fxmls/editDBstyle.fxml")));
            Parent editDBRoot = editDBLoader.load();
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////






        //Items

        //fileDBchooser////////////////////////////////////////////////////////////
        VBox boxFileChooser = (VBox) mainRoot.lookup("#boxFileChooser");
        ObservableList<Node> boxFileChooserTable = boxFileChooser.getChildren();
        FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Database Files", "*.db") );
        Label labelFilePath = new Label(dbPath);
            labelFilePath.setFont(new Font(10));
        Button buttonChooseFile = new Button("Browse for file ...");
            buttonChooseFile.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-text-width: 10px;");
            buttonChooseFile.setFont(new Font(12));
            buttonChooseFile.setOnAction(e -> {
                File selectedFile = fileChooser.showOpenDialog(stage);
                dbPath = selectedFile.getPath();
                labelFilePath.setText(dbPath);
                labelFilePath.setStyle("-fx-text-fill: white;");
            });
        boxFileChooserTable.add(buttonChooseFile);
        boxFileChooserTable.add(labelFilePath);

        /////////////////////////////////////////////////////////////////////////////





        //mayB some animations
//        TextField searchField = (TextField) loader.getNamespace().get("searchField");
//        searchField.setText("SSSSS");
//        FadeTransition ft = new FadeTransition(Duration.millis(3000), searchField);
//        ft.setFromValue(1.0);
//        ft.setToValue(0.1);
//        ft.setCycleCount(Timeline.INDEFINITE);
//        ft.setAutoReverse(true);
//        ft.play();









        //STAGE////////////////////////////////////////////////////
        Scene mainScene = new Scene(mainRoot, 1280, 720);
        Scene productsScene = new Scene(productsRoot, 1280, 720);
        Scene settlementsScene = new Scene(settlementsRoot, 1280, 720);
        Scene editDatabaseScene = new Scene(editDBRoot, 1280, 720);

        stage.setScene(mainScene);
        stage.setTitle("SklepShop");
        stage.setResizable(false);
        stage.show();
        ////////////////////////////////////////////////////////////







        //SWITCHING BETWEEN SCENES ////////////////////////////////////////////////////////////////////

                //Switching to loadDBPanel
                    Button[] switchToloadDBPanelButtons = {
                            (Button) productsRoot.lookup("#switchToLoadDBScene"),
                            (Button) settlementsRoot.lookup("#switchToLoadDBScene"),
                            (Button) editDBRoot.lookup("#switchToLoadDBScene")
                    };
                    for(Button b : switchToloadDBPanelButtons)
                        b.setOnAction(event ->{ goToSceneLoadDB(stage, mainScene); });

                 //Switching to products scene
                    Button[] switchToProductsSceneButtons = {
                                    (Button) mainRoot.lookup("#switchToProductsScene"),
//                                    (Button) mainRoot.lookup("#switchToProductsSceneApache"),
                                    (Button) settlementsRoot.lookup("#switchToProductsScene"),
                                    (Button) editDBRoot.lookup("#switchToProductsScene")
                    };
                    for(Button b : switchToProductsSceneButtons)
                        b.setOnAction(event ->{ goToSceneProducts(stage, productsScene, productsLoader, productsRoot); });
//                    switchToProductsSceneButtons[1].setOnAction(event ->{dbPath="127.0.0.1";
//                        goToSceneProducts(stage, productsScene, productsLoader, productsRoot);});


                        //Switching to EditDB scene
                Button[] switchToEditDBsSceneButtons = {
                        (Button) settlementsRoot.lookup("#switchToEditDBScene"),
                        (Button) productsRoot.lookup("#switchToEditDBScene")
                };
                for(Button b : switchToEditDBsSceneButtons)
                    b.setOnAction(event ->{ goToSceneEditDB(stage, editDatabaseScene, editDBRoot); });


                //Switching to settlements scene
                Button[] switchToSettlementSceneButtons = {
                        (Button) productsRoot.lookup("#switchToSettlemetsScene"),
                        (Button) editDBRoot.lookup("#switchToSettlemetsScene")
                };
                for(Button b : switchToSettlementSceneButtons)
                    b.setOnAction(event ->{ goToSceneSettlements(stage, settlementsScene, settlementsLoader); });
        ///////////////////////////////////////////////////////////////////////////////////////////////
    }






                public static void main(String[] args) {
        launch(args);
    }












//functions switching between scenes
    public void goToSceneLoadDB(Stage stage, Scene mainScene)
    {
        stage.setScene(mainScene);
    }

    public void goToSceneProducts(Stage stage, Scene productsScene, FXMLLoader productsLoader, Parent productsRoot)
    {
        stage.setScene(productsScene);
        loadProducts(productsLoader);
        loadProductsControls(productsRoot, productsLoader);
    }

    public void goToSceneEditDB(Stage stage, Scene editDatabaseScene, Parent editDBRoot)
    {
        stage.setScene(editDatabaseScene);
        EditProductsDB editProductsDB = new EditProductsDB();
        editProductsDB.loadProductDBmanager(editDBRoot);
    }

    public void goToSceneSettlements(Stage stage, Scene settlementsScene, FXMLLoader loader)
    {
        stage.setScene(settlementsScene);
        Settlements.loadSettlements(loader);
    }
















}



