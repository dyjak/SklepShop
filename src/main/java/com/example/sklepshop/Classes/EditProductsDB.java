package com.example.sklepshop.Classes;

import com.example.sklepshop.App;
import com.example.sklepshop.entities.Product;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class EditProductsDB {


    static Product selectedProduct = new Product();


    //PRODUCT DATABASE EDIT MANAGER
    public void loadProductDBmanager(Parent editDBRoot)
    {
        try {
            //HIBERNATE (DZIAŁA!!!!!!!!)
            Configuration configuration = new Configuration();
            configuration.setProperty("hibernate.connection.url", "jdbc:sqlite:" + App.dbPath);
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
            final Session session = sessionFactory.openSession();


            AtomicReference<List<Product>> product_x = new AtomicReference<>(session.createSQLQuery("SELECT * FROM products").addEntity(Product.class).list());
            //List<Product> product_x = session.createSQLQuery("SELECT * FROM products").addEntity(Product.class).list();

            //Items
            Label labelSelectedProductID = (Label) editDBRoot.lookup("#labelSelectedProductID");
            Label labelSelectedProductExpanded = (Label) editDBRoot.lookup("#labelSelectedProductExpanded");
            Button btnDeleteProduct = (Button) editDBRoot.lookup("#btnDeleteProduct");
            Button btnEditProduct = (Button) editDBRoot.lookup("#btnEditProduct");
            Button btnAddProduct = (Button) editDBRoot.lookup("#btnAddProduct");

            //Adding to table
            TableView tableView = (TableView) editDBRoot.lookup("#tableView") ;

            loadEditDbTable(product_x.get(), tableView);

            //Selecting product to action
            tableView.setRowFactory(tv -> {
                TableRow<Product> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    selectedProduct = row.getItem();
                    labelSelectedProductID.setText("Edit product (id: "+ selectedProduct.getProduct_id()+")");
                    labelSelectedProductExpanded.setText(selectedProduct.toString());
                    btnDeleteProduct.setDisable(false);
                    btnEditProduct.setDisable(false);
                    //System.out.println(selectedProduct.toString());
                });
                return row;
            });



            //Actions on db

            //DELETING///////////////////////////////////////////////////////////////////////////////
            btnDeleteProduct.setOnAction(e -> {

                //DialogAlert
                Alert alertDeleteConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                alertDeleteConfirm .setTitle("Confirmation");
                alertDeleteConfirm .setContentText("Are you sure you want to delete this product (id:"+selectedProduct.getProduct_id()+") from database?");
                alertDeleteConfirm.getDialogPane().setPrefWidth(1000);
                Alert alertSucces = new Alert(Alert.AlertType.INFORMATION);
                alertSucces.setTitle("Successfully removed!");
                alertSucces.setContentText(""); //dynamic

                Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
                if (result.get() == ButtonType.OK) {
                    //query execute
                    session.clear();
                    session.beginTransaction();
                    SQLQuery query = session.createSQLQuery(
                            "delete from products where product_id="+ selectedProduct.getProduct_id());
                    query.executeUpdate();
                    session.getTransaction().commit();

                    //trying to remove
                    //                    for(Product product_i : product_x.get()) HIBERNATE DOESNT WORK ON OBJECTS RN
                    //                        if(product_i!=null)
                    //                            if(product_i.getProduct_id() == selectedProductID.get())
                    //                            {
                    //                                System.out.println("DELETING: "+selectedProductID+" "+product_i.toString());
                    //                                session.clear();
                    //                                session.beginTransaction();
                    //                                session.delete(product_i);
                    //                                session.getTransaction().commit();
                    //                            }

                    alertSucces.setContentText("Product (id:"+ selectedProduct.getProduct_id()+") successfully deleted!");
                    alertSucces.showAndWait();
                    //refreshing
                    product_x.set(session.createSQLQuery("SELECT * FROM products").addEntity(Product.class).list());
                    loadEditDbTable(product_x.get(), tableView);
                }
            });

            //EDITING///////////////////////////////////////////////////////////////////////////////
            btnEditProduct.setOnAction(e -> {

                //AlertDialog
                Alert alertCommitConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                alertCommitConfirm .setTitle("Confirmation");
                alertCommitConfirm.getDialogPane().setPrefWidth(1000);
                Alert alertSucces = new Alert(Alert.AlertType.INFORMATION);
                alertSucces.setTitle("Success!");
                alertSucces.setContentText(""); //dynamic

                Dialog<Product> dialogEditForm = new Dialog<>();
                    dialogEditForm.setTitle("Editing product (id+"+selectedProduct.getProduct_id()+")");
                        dialogEditForm.setResizable(true);
                    dialogEditForm.setHeaderText("Edit components");

                Label labelName = new Label("Name: ");
                Label labelCategory = new Label("Category: ");
                Label labelPrice = new Label("Price: ");
                TextField textFieldName = new TextField(selectedProduct.getName());
                    textFieldName.setPrefWidth(200);
                ChoiceBox choiceBoxCategory = new ChoiceBox(
                        FXCollections.observableArrayList(
                        "vagetables_fruits", "meat", "grain products", "diary", "fats", "sweets", "drinks", "snacks", "others"));
                    choiceBoxCategory.setValue(selectedProduct.getCategory());
                TextField textFieldPrice = new TextField(""+ selectedProduct.getPrice());
                //forcing double values
                textFieldPrice.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        // Wartości numeryczne z przecinkiem lub kropką
                        if (!newValue.matches("\\d*([\\.,]\\d{0,2})?")) {
                            textFieldPrice.setText(oldValue);
                            if(textFieldPrice.getText().contains(","))
                                textFieldPrice.setText(textFieldPrice.getText().replace(",", "."));
                        }
                    }
                });

                GridPane grid = new GridPane();
                    grid.add(labelName, 1, 1);
                    grid.add(textFieldName, 2, 1);
                    grid.add(labelCategory, 1, 2);
                    grid.add(choiceBoxCategory, 2, 2);
                    grid.add(labelPrice, 1, 3);
                    grid.add(textFieldPrice, 2, 3);
                dialogEditForm.getDialogPane().setContent(grid);
                grid.setAlignment(Pos.CENTER);

                ButtonType buttonTypeOk = new ButtonType("Commit", ButtonBar.ButtonData.OK_DONE);
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialogEditForm.getDialogPane().getButtonTypes().add(buttonTypeOk);
                dialogEditForm.getDialogPane().getButtonTypes().add(buttonTypeCancel);
                dialogEditForm.setResultConverter(new Callback<ButtonType, Product>() {

                    public Product call(ButtonType b) {

                        if (b == buttonTypeOk) {
                            if(textFieldName.getText()!=""&&textFieldPrice.getText()!=""&&choiceBoxCategory.getValue()!=null)
                            {
                                //confirmation text
                                String confirmationInfoText = "Are you sure you want to apply changes to this product?"
                                        + "\nName: \t"+selectedProduct.getName() + "\t -> \t" + textFieldName.getText()
                                        + "\nCategory: \t"+selectedProduct.getCategory() + "\t -> \t" + choiceBoxCategory.getValue().toString()
                                        + "\nPrice: \t"+selectedProduct.getPrice() + "\t -> \t" + textFieldPrice.getText()
                                        +"\n ARE YOU SURE?";
                                alertCommitConfirm.setContentText(confirmationInfoText);


                                String theQuery = "UPDATE products " +
                                        "SET name = '"+textFieldName.getText()+"' "+
                                        ", category = '"+choiceBoxCategory.getValue()+"' "+
                                        ", price = "+textFieldPrice.getText()+
                                        " WHERE product_id = " + selectedProduct.getProduct_id();
                                System.out.println(theQuery);

                                Optional<ButtonType> result = alertCommitConfirm.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    {
                                        session.clear();
                                        session.beginTransaction();
                                        SQLQuery query = session.createSQLQuery(theQuery);
                                        query.executeUpdate();
                                        session.getTransaction().commit();

                                        product_x.set(session.createSQLQuery("SELECT * FROM products").addEntity(Product.class).list());
                                        loadEditDbTable(product_x.get(), tableView);
                                        //System.out.println(selectedProduct.get() == null); //????????
                                    }
                                }
                            }
                        }
                        return null;
                    }
                });

                if (dialogEditForm.showAndWait().isPresent())
                {
                    alertSucces.showAndWait();
                }
            });


            //ADDING///////////////////////////////////////////////////////////////////////////////
            btnAddProduct.setOnAction(event -> {
                //AlertDialog
                Alert alertCommitConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                alertCommitConfirm .setTitle("Confirmation");
                alertCommitConfirm.getDialogPane().setPrefWidth(1000);
                Alert alertSucces = new Alert(Alert.AlertType.INFORMATION);
                alertSucces.setTitle("Success!");
                alertSucces.setContentText(""); //dynamic

                Dialog<Product> dialogAddForm = new Dialog<>();
                dialogAddForm.setTitle("Adding a new product");
                dialogAddForm.setResizable(true);
                dialogAddForm.setHeaderText("Insert data");

                Label labelName = new Label("Name: ");
                Label labelCategory = new Label("Category: ");
                Label labelPrice = new Label("Price: ");
                TextField textFieldName = new TextField("");
                textFieldName.setPrefWidth(200);
                ChoiceBox choiceBoxCategory = new ChoiceBox(
                        FXCollections.observableArrayList(
                                "vagetables_fruits", "meat", "grain products", "diary", "fats", "sweets", "drinks", "snacks", "others"));
                choiceBoxCategory.setValue("vagetables_fruits");
                TextField textFieldPrice = new TextField("");
                //forcing double values
                textFieldPrice.textProperty().addListener(new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                        // Wartości numeryczne z przecinkiem lub kropką
                        if (!newValue.matches("\\d*([\\.,]\\d{0,2})?")) {
                            textFieldPrice.setText(oldValue);
                            if(textFieldPrice.getText().contains(","))
                                textFieldPrice.setText(textFieldPrice.getText().replace(",", "."));
                        }
                    }
                });

                GridPane grid = new GridPane();
                grid.add(labelName, 1, 1);
                grid.add(textFieldName, 2, 1);
                grid.add(labelCategory, 1, 2);
                grid.add(choiceBoxCategory, 2, 2);
                grid.add(labelPrice, 1, 3);
                grid.add(textFieldPrice, 2, 3);
                dialogAddForm.getDialogPane().setContent(grid);
                grid.setAlignment(Pos.CENTER);

                ButtonType buttonTypeOk = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
                ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                dialogAddForm.getDialogPane().getButtonTypes().add(buttonTypeOk);
                dialogAddForm.getDialogPane().getButtonTypes().add(buttonTypeCancel);
                dialogAddForm.setResultConverter(new Callback<ButtonType, Product>() {

                    public Product call(ButtonType b) {

                        if (b == buttonTypeOk) {
                            if(textFieldName.getText()!=""&&textFieldPrice.getText()!=""&&choiceBoxCategory.getValue()!=null)
                            {

                                Product newProduct = new Product();
                                long newID=0;
                                    if(product_x.get().size()>0) newID=product_x.get().get(product_x.get().size()-1).getProduct_id()+1;
                                newProduct.setProduct_id(newID);
                                newProduct.setCategory(choiceBoxCategory.getValue().toString());
                                newProduct.setName(textFieldName.getText());
                                newProduct.setPrice(Double.valueOf(textFieldPrice.getText()));
                                //System.out.println(newProduct.toString());

                                //confirmation
                                String confirmationInfoText = "Are you sure you want to add this product?"
                                        + "\nID: \t"+newProduct.getProduct_id()
                                        + "\nName: \t"+newProduct.getName()
                                        + "\nCategory: \t"+newProduct.getCategory()
                                        + "\nPrice: \t"+newProduct.getPrice()
                                        +"\n ARE YOU SURE?";
                                alertCommitConfirm.setContentText(confirmationInfoText);


                                String theQuery = "INSERT INTO products (product_id, category, name, price) " +
                                        "VALUES ("+newProduct.getProduct_id()+", '"+newProduct.getCategory()+"', '"
                                        +newProduct.getName()+ "', " + newProduct.getPrice()+")";
                                System.out.println(theQuery);

                                Optional<ButtonType> result = alertCommitConfirm.showAndWait();
                                if (result.get() == ButtonType.OK) {
                                    {
                                        session.clear();
                                        session.beginTransaction();
                                        SQLQuery query = session.createSQLQuery(theQuery);
                                        query.executeUpdate();
                                        session.getTransaction().commit();

                                        product_x.set(session.createSQLQuery("SELECT * FROM products").addEntity(Product.class).list());
                                        loadEditDbTable(product_x.get(), tableView);

                                    }
                                }
                            }
                        }
                        return null;
                    }
                });

                if (dialogAddForm.showAndWait().isPresent())
                {
                    alertSucces.showAndWait();
                }
            });

        //wszystko działa perfekt

        } catch(Exception ex) {
            System.out.println("Loading database error");
        }
    }




    public static void loadEditDbTable(List<Product> product_x, TableView tableView)
    {
        while(!tableView.getItems().isEmpty())
        {
            tableView.getItems().clear();
            tableView.getColumns().clear();
        }
        TableColumn tabColID = new TableColumn("ID");
        TableColumn tabColName = new TableColumn("Name");
        TableColumn tabColCategory = new TableColumn("Category");
        TableColumn tabColPrice = new TableColumn("Price");
        tabColID.setCellValueFactory(new PropertyValueFactory<>("product_id"));
        tabColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tabColCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        tabColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tabColID.setPrefWidth(100);
        tabColName.setPrefWidth(620);
        tabColCategory.setPrefWidth(250);
        tabColPrice.setPrefWidth(200);
        tableView.getColumns().removeAll(tabColID, tabColName, tabColCategory, tabColPrice);
        tableView.getColumns().addAll(tabColID, tabColName, tabColCategory, tabColPrice);
        tableView.getItems().addAll(product_x);
    }






}
