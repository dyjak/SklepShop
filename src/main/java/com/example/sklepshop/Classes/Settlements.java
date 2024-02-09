package com.example.sklepshop.Classes;

import com.example.sklepshop.entities.Product;
import com.example.sklepshop.entities.Settlement;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.LongType;

import java.util.List;
import java.util.Optional;

import static com.example.sklepshop.App.dbPath;

public class Settlements {

    public static void loadSettlements(FXMLLoader loader) {
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
        configuration.addAnnotatedClass(com.example.sklepshop.entities.Settlement.class);
        configuration.addAnnotatedClass(com.example.sklepshop.entities.Product.class);

        //Hibernate Connection Debug
        //                    List<Product> product_x = session.createSQLQuery("SELECT * FROM products").addEntity(Product.class).list();
        //                    for (Product product_i : product_x) {
        //                        System.out.println(product_i.toString());
        //                    }
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();


        //QUERY
        String theQuery = "SELECT * FROM settlements";
        String theQuery2 = "SELECT * FROM products";
        //System.out.println(theQuery);
        //Settlements
        List<Settlement> settlement_x = session.createSQLQuery(theQuery).addEntity(Settlement.class).list();
        List<Product> product_x = session.createSQLQuery(theQuery2).addEntity(Product.class).list();

        VBox settlementsMainBox = (VBox) loader.getNamespace().get("settlementsBox");
        VBox.setVgrow(settlementsMainBox, Priority.ALWAYS);
            ObservableList<Node> settlementsMainBoxRoot = settlementsMainBox.getChildren();
            settlementsMainBoxRoot.clear();

        for (Settlement settlement_i : settlement_x) {
            System.out.println(settlement_i.toString());

                String title=
                        "Settlement ID: "+settlement_i.getSettlement_id()
                        +"          Paid: "+settlement_i.getTotal_price()
                        +"          Method: "+settlement_i.getMethod()
                        +"          Date: "+settlement_i.getDate();
                Label labelTitle = new Label(title);
                    labelTitle.setAlignment(Pos.CENTER);
                HBox infoBox = new HBox(labelTitle);

                VBox receiptBox = new VBox();
                String[] productId = settlement_i.getProducts_ids().split(" ");
                for(int i=0; i<productId.length; i++)
                {
//                  Product thisProduct = (Product) session.createQuery("SELECT FROM products WHERE product_id "+productId[i], Product.class).uniqueResult(); //doesnt work
                    Product thisProduct = null;
                    for(Product product_i : product_x)
                    {
                        if(product_i.getProduct_id() == Long.parseLong(productId[i]))
                            thisProduct=product_i;
                    }
                    Label productStringLabel;
                    if(thisProduct==null)
                        productStringLabel = new Label("unknown product");
                    else
                        productStringLabel = new Label(thisProduct.toString());
                    productStringLabel.setStyle("-fx-font-size: 15px;");
                    receiptBox.getChildren().add(productStringLabel);
                    productStringLabel.setAlignment(Pos.CENTER);
                }

            VBox settlementBox = new VBox(infoBox, receiptBox);
                settlementBox.setAlignment(Pos.CENTER);
                settlementBox.setPadding(new Insets(20, 20, 20, 20));
            ScrollPane scrollPane = new ScrollPane(settlementBox);
                scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
                scrollPane.setPrefHeight(200);
                settlementBox.setPrefWidth(1100);
                scrollPane.setStyle("-fx-border-insets: 10px 10px 10px 10px;");
            settlementsMainBoxRoot.add(scrollPane);
        }
    }






    public static void addSettlement(Settlement newSettlement) {
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
        configuration.addAnnotatedClass(com.example.sklepshop.entities.Settlement.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        String theQuery = "SELECT * FROM settlements";
        System.out.println(theQuery);

        //SettlementList
        List<Settlement> settlement_x = session.createSQLQuery(theQuery).addEntity(Settlement.class).list();


        if(settlement_x.size()>0)
        {
            long newID = settlement_x.get((settlement_x.size() - 1)).getSettlement_id() + 1;
            newSettlement.setSettlement_id(newID);
        }

        String theInsertQuery = "INSERT INTO settlements (settlement_id, product_ids, method, date, total_price) " +
                "VALUES (" + newSettlement.getSettlement_id() + ", '" + newSettlement.getProducts_ids() + "', '"
                + newSettlement.getMethod() + "', '" + newSettlement.getDate() + "', " + newSettlement.getTotal_price() + ")";
        System.out.println(theInsertQuery);

        session.clear();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery(theInsertQuery);
        query.executeUpdate();
        session.getTransaction().commit();


    }
}
