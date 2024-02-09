package com.example.sklepshop.Classes;

import com.example.sklepshop.entities.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.text.DecimalFormat;
import java.util.List;

import static com.example.sklepshop.App.*;
import static com.example.sklepshop.Classes.Products_Management.addToCart;
import static com.example.sklepshop.Classes.Products_Management.settle;


public class Products_LoadProducts {

    static String selectedPaymentMethod = "other";

    public static void loadProducts(FXMLLoader loader) {
    //    try {
            //HIBERNATE (DZIAŁA!!!!!!!!)
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:" + dbPath);
            configuration.setProperty("hibernate.connection.username", "root");
            configuration.setProperty("hibernate.connection.password", "ZAQ!2wsx");
            configuration.setProperty("hibernate.show_sql", "true");
            configuration.setProperty("hibernate.connection.driver_class", "org.sqlite.JDBC");
            configuration.setProperty("hibernate.dialect", "org.sqlite.hibernate.dialect.SQLiteDialect");
            configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            configuration.addAnnotatedClass(com.example.sklepshop.entities.Product.class);

            //Hibernate Connection Debug
            //                    List<Product> product_x = session.createSQLQuery("SELECT * FROM products").addEntity(Product.class).list();
            //                    for (Product product_i : product_x) {
            //                        System.out.println(product_i.toString());
            //                    }
            SessionFactory sessionFactory = configuration.buildSessionFactory();
            Session session = sessionFactory.openSession();




            //Items
            FlowPane productsPane = (FlowPane) loader.getNamespace().get("productsPane");

            //CheckBoxes
            CheckBox checkBoxCat1 = (CheckBox) loader.getNamespace().get("checkBoxCat1");
            CheckBox checkBoxCat2 = (CheckBox) loader.getNamespace().get("checkBoxCat2");
            CheckBox checkBoxCat3 = (CheckBox) loader.getNamespace().get("checkBoxCat3");
            CheckBox checkBoxCat4 = (CheckBox) loader.getNamespace().get("checkBoxCat4");
            CheckBox checkBoxCat5 = (CheckBox) loader.getNamespace().get("checkBoxCat5");
            CheckBox checkBoxCat6 = (CheckBox) loader.getNamespace().get("checkBoxCat6");
            CheckBox checkBoxCat7 = (CheckBox) loader.getNamespace().get("checkBoxCat7");
            CheckBox checkBoxCat8 = (CheckBox) loader.getNamespace().get("checkBoxCat8");
            CheckBox checkBoxCat9 = (CheckBox) loader.getNamespace().get("checkBoxCat9");

            //RadioButtons
            RadioButton radioButtonSort1 = (RadioButton) loader.getNamespace().get("radioButton1");
            RadioButton radioButtonSort2 = (RadioButton) loader.getNamespace().get("radioButton2");
            RadioButton radioButtonSort3 = (RadioButton) loader.getNamespace().get("radioButton3");
            RadioButton radioButtonSort4 = (RadioButton) loader.getNamespace().get("radioButton4");

            //SearchField
            TextField searchField = (TextField) loader.getNamespace().get("searchField");



            //ProductsPane
            productsPane.getChildren().clear();
            ObservableList<Node> productsPaneTable = productsPane.getChildren();



            //QUERY
            String theQuery = "SELECT * FROM products";
            //categoriesCheck(checkBoxes)
            if( (checkBoxCat1.isSelected()) || (checkBoxCat2.isSelected()) || (checkBoxCat3.isSelected()) || (checkBoxCat4.isSelected()) || (checkBoxCat5.isSelected()) || (checkBoxCat6.isSelected()) || (checkBoxCat7.isSelected()) || (checkBoxCat8.isSelected()) || (checkBoxCat9.isSelected()) )
            {
                theQuery+=" WHERE (category LIKE '";
                if(checkBoxCat1.isSelected()) theQuery+="vegetables_fruits' OR category LIKE '";
                if(checkBoxCat2.isSelected()) theQuery+="diary' OR category LIKE '";
                if(checkBoxCat3.isSelected()) theQuery+="grain' OR category LIKE '";
                if(checkBoxCat4.isSelected()) theQuery+="meat' OR category LIKE '";
                if(checkBoxCat5.isSelected()) theQuery+="fats' OR category LIKE '";
                if(checkBoxCat6.isSelected()) theQuery+="sweets' OR category LIKE '";
                if(checkBoxCat7.isSelected()) theQuery+="drinks' OR category LIKE '";
                if(checkBoxCat8.isSelected()) theQuery+="snacks' OR category LIKE '";
                if(checkBoxCat9.isSelected()) theQuery+="others'";

                if (theQuery.endsWith("OR category LIKE '")) {
                    theQuery = theQuery.substring(0, theQuery.length() - 18);
                }
                //nameCheck(searchField)
                theQuery+= ") AND name LIKE '%" + searchField.getText() + "%'";
            }
            else
            {
                theQuery+= " WHERE name LIKE '%" + searchField.getText()
                        + "%' OR category LIKE '%" + searchField.getText()
                        + "%' OR product_id LIKE '"+searchField.getText()+"'";
            }

            //ORDER (sort radioButtons)
            if( radioButtonSort1.isSelected() || radioButtonSort2.isSelected() || radioButtonSort3.isSelected() || radioButtonSort4.isSelected() )
            {
                if(radioButtonSort1.isSelected()) theQuery += " ORDER BY name ASC";
                if(radioButtonSort2.isSelected()) theQuery += " ORDER BY name DESC";
                if(radioButtonSort3.isSelected()) theQuery += " ORDER BY price ASC";
                if(radioButtonSort4.isSelected()) theQuery += " ORDER BY price DESC";
            }

            System.out.println(theQuery);


            //Products
            List<Product> product_x = session.createSQLQuery(theQuery).addEntity(Product.class).list();




            //Category Icons
//?????????


            //Generating products pane
            for (Product product_i : product_x) {

                Label label_id = new Label("ID: " + product_i.getProduct_id());
                Label label_category = new Label("Category: " + product_i.getCategory());
                Label label_name = new Label("" + product_i.getName());
                String priceFormatted=df.format(product_i.getPrice());
                Label label_price = new Label(priceFormatted+ "PLN");
                label_id.getStyleClass().clear(); label_id.getStyleClass().add("productLabels");
                label_category.getStyleClass().clear(); label_category.getStyleClass().add("productLabels");
                label_name.getStyleClass().clear(); label_name.getStyleClass().add("productLabels");
                label_price.getStyleClass().clear(); label_price.getStyleClass().add("productLabels");

                ImageView category_icon = new ImageView();
                switch(product_i.getCategory())
                {
                    case "vegetables_fruits" : category_icon = new ImageView(categoryIconImageView_1); break;
                    case "diary" : category_icon= new ImageView(categoryIconImageView_2); break;
                    case "grain" : category_icon= new ImageView(categoryIconImageView_3); break;
                    case "meat" : category_icon= new ImageView(categoryIconImageView_4); break;
                    case "fats" : category_icon= new ImageView(categoryIconImageView_5); break;
                    case "sweets" : category_icon= new ImageView(categoryIconImageView_6); break;
                    case "drinks" : category_icon= new ImageView(categoryIconImageView_7); break;
                    case "snacks" : category_icon= new ImageView(categoryIconImageView_8); break;
                    default : category_icon= new ImageView(categoryIconImageView_9);
                }
                category_icon.setFitWidth(50);
                category_icon.setFitHeight(50);
                //Adding product
                TextField amountField = new TextField();
                amountField.setPrefSize(50 ,40);
                amountField.setFont(new Font(15));
                amountField.setText(""+1);
                amountField.textProperty().addListener(new ChangeListener<String>()
                        //forcing numeric values
                {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue,
                                        String newValue) {
                        if (!newValue.matches("\\d*"))
                            amountField.setText(newValue.replaceAll("[^\\d]", ""));
                        if (amountField.getText().length() > 2)
                            amountField.setText("");
                    }
                });
                Button amountPlus = new Button();
                Button amountMinus = new Button();
                amountPlus.setStyle("-fx-border-width: 0; -fx-background-color:green;"); amountPlus.setFont(new Font(9));
                amountMinus.setStyle("-fx-border-width: 0; -fx-background-color:red;"); amountMinus.setFont(new Font(9));
                amountPlus.setOnAction(e -> {
                    int nowAmount = Integer.valueOf(amountField.getText()) + 1;
                    amountField.setText(""+nowAmount);
                });
                amountMinus.setOnAction(e -> {
                    int nowAmount = Integer.valueOf(amountField.getText()) - 1;
                    amountField.setText(""+nowAmount);
                });
                VBox amountFieldControls = new VBox(amountPlus, amountMinus);
                amountFieldControls.setPrefSize(40,110);
                amountFieldControls.setAlignment(Pos.CENTER);

                HBox amountBox = new HBox(amountField, amountFieldControls);
                amountBox.setAlignment(Pos.CENTER);

                Button buttonAddProduct = new Button("Add"); //Adding a product to cart
                    buttonAddProduct.setMaxSize(110,40);
                    buttonAddProduct.setFont(new Font(15));

                buttonAddProduct.setOnAction(e -> {
                    System.out.println(label_name.getText() + " * " + amountField.getText());
                    //Adding to tablebox
                    addToCart(product_i, Integer.valueOf(amountField.getText()), loader);

                    amountField.setText(""+1);
                });
                buttonAddProduct.setStyle("-fx-background-color: #fed332; -fx-text-fill: #494848");

                HBox actionBox = new HBox(amountBox, buttonAddProduct);
                actionBox.setAlignment(Pos.CENTER);

                //Product Box
                VBox productBox = new VBox(
                        label_id,
                        label_category,
                        label_name,
                        label_price,
                        category_icon,
                        actionBox           );
                productBox.getStyleClass().clear(); productBox.getStyleClass().add("productBox");
                productBox.setPrefWidth(219);
                productBox.setPrefHeight(219);

                label_price.setAlignment(Pos.BOTTOM_CENTER);

                productsPaneTable.add(productBox);
                //System.out.println(product_i.toString());

                //Settle
                Button settleButton = (Button) loader.getNamespace().get("settleButton");
                settleButton.setOnAction(e -> {
                    settle(loader);
                });
//            }
//
//
//
//        } catch(Exception ex) {
//            System.out.println("Loading database error");
//            System.out.println(ex.toString());
//            FlowPane productsPane = (FlowPane) loader.getNamespace().get("productsPane");
//            productsPane.getChildren().clear();
//            ObservableList<Node> productsPaneTable = productsPane.getChildren();
//            Label errorLabel = new Label("Some issues with loading your database. Try again!");
//            productsPaneTable.add(errorLabel);
//            errorLabel.setFont(new Font(36)); errorLabel.setAlignment(Pos.CENTER); errorLabel.setTextFill(Color.RED); errorLabel.setPadding(new Insets(200,0,200,50));
        }
    }







    public static void loadProductsControls(Parent productsRoot, FXMLLoader productsLoader)
    {
        //Scene products CONTROLS
        FlowPane productsPane = (FlowPane) productsRoot.lookup("#productsPane");

        //ON ACTIONS
        //CheckBoxes OnAction
        for (int i = 1; i <= 9; i++)
        {
            CheckBox currentCheckBox = (CheckBox) productsRoot.lookup("#checkBoxCat" + i);

            if (currentCheckBox != null) {
                currentCheckBox.setOnAction(e -> {
                    loadProducts(productsLoader); });
            }
        }

        //SearchField OnAction
        TextField searchField = (TextField) productsRoot.lookup("#searchField");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            loadProducts(productsLoader);
        });

        //RadioButtons OnAction
        //sort
        for (int i = 1; i <= 4; i++)
        {
            RadioButton currentRadioButton = (RadioButton) productsRoot.lookup("#radioButton" + i);
            if (currentRadioButton != null) {
                currentRadioButton.setOnAction(e -> {
                    for (int j = 1; j <= 4; j++)
                    {
                        RadioButton currentRadioButtonToDisable = (RadioButton) productsRoot.lookup("#radioButton" + j);
                        if(currentRadioButton!=currentRadioButtonToDisable)
                            currentRadioButtonToDisable.setSelected(false);
                    }
                    loadProducts(productsLoader); });
            }
        }

        //payment
        RadioButton radioPaymentCard = (RadioButton) productsRoot.lookup("#radioPaymentCard");
        RadioButton radioPaymentCash = (RadioButton) productsRoot.lookup("#radioPaymentCash");
        radioPaymentCard.setOnAction(e ->{radioPaymentCash.setSelected(false); selectedPaymentMethod="card";});
        radioPaymentCash.setOnAction(e ->{radioPaymentCard.setSelected(false); selectedPaymentMethod="cash";});
    }








}
