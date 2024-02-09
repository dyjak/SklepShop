package com.example.sklepshop.Classes;

import com.example.sklepshop.entities.AddedProduct;
import com.example.sklepshop.entities.Product;
import com.example.sklepshop.entities.Settlement;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.converter.LocalDateTimeStringConverter;
import javafx.util.converter.LocalTimeStringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.sklepshop.App.addedProduct_x;
import static com.example.sklepshop.App.df;

public class Products_Management {
    public static void addToCart(Product product_i, int amount, FXMLLoader loader)
    {
        VBox vBoxAddedProducts = (VBox) loader.getNamespace().get("vBoxAddedProductsName");
        VBox vBoxAddedProductsFinalPrice = (VBox) loader.getNamespace().get("vBoxAddedProductsFinalPrice");
        VBox vBoxAddedProductsDelete = (VBox) loader.getNamespace().get("vBoxAddedProductsDelete");
        ObservableList<Node> vBoxAddedProductsList = vBoxAddedProducts.getChildren();
        ObservableList<Node> vBoxAddedProductsFinalPriceList = vBoxAddedProductsFinalPrice.getChildren();
        ObservableList<Node> vBoxAddedProductsDeleteList = vBoxAddedProductsDelete.getChildren();
        Label finalizePriceLabel = (Label) loader.getNamespace().get("finalizePriceLabel");


        AddedProduct addedProduct = new AddedProduct(product_i.getProduct_id(), product_i.getName(), product_i.getPrice(), amount); //Integer.valueOf(amountField.getText())
        boolean existsInCart = false;
        for (AddedProduct addedProduct_i : addedProduct_x)
        {
            if(addedProduct.getId() == addedProduct_i.getId())
            {
                addedProduct_i.setAmount(addedProduct_i.getAmount() + addedProduct.getAmount());
                addedProduct_i.setFinalPrice(addedProduct_i.getAmount()*addedProduct_i.getPrice());
                existsInCart=true; break;
            }
        } if(!existsInCart && amount>0) addedProduct_x.add(addedProduct);

        double finalizePrice = 0.0;
        vBoxAddedProductsList.clear();
        vBoxAddedProductsFinalPriceList.clear();
        vBoxAddedProductsDeleteList.clear();
        for (AddedProduct addedProduct_i : addedProduct_x)
        {
            //System.out.println(addedProduct_i);
            Label addedNameLabel = new Label(""+addedProduct_i.getName()+" (id:"+addedProduct_i.getId()+") * "+addedProduct_i.getAmount()+"\t");
                HBox addedNameBox = new HBox(addedNameLabel);
                addedNameBox.setPrefHeight(40);
                addedNameBox.getStyleClass().add("cart-boxes");
            Label addedFinalPriceLabel = new Label(""+df.format(addedProduct_i.getFinalPrice())+"PLN");
                HBox addedPriceBox = new HBox(addedFinalPriceLabel);
                addedPriceBox.setPrefHeight(40);
                addedPriceBox.getStyleClass().add("cart-boxes");
            Button addedButtonDelete = new Button("-");
                addedButtonDelete.setFont(new Font(10));
                addedButtonDelete.setPrefSize(40,40);
                addedButtonDelete.getStyleClass().add("cart-boxes");

            vBoxAddedProductsList.add(addedNameBox);
            vBoxAddedProductsFinalPriceList.add(addedPriceBox);
            vBoxAddedProductsDeleteList.add(addedButtonDelete);

            finalizePrice+=addedProduct_i.getFinalPrice();


            addedButtonDelete.setOnAction(ev ->{
                addedProduct_x.remove(addedProduct_i);

                //call back to load over
                addToCart(product_i,0, loader); //SMART😎
            });
        }
        if(finalizePrice==0.0) finalizePriceLabel.setText("0.00PLN");
        else finalizePriceLabel.setText(df.format(finalizePrice)+"PLN");

    }









    public static void settle(FXMLLoader loader)
    {
        if(addedProduct_x.isEmpty()) System.out.println("No products added!");
        else {



            VBox vBoxAddedProducts = (VBox) loader.getNamespace().get("vBoxAddedProductsName");
            VBox vBoxAddedProductsFinalPrice = (VBox) loader.getNamespace().get("vBoxAddedProductsFinalPrice");
            VBox vBoxAddedProductsDelete = (VBox) loader.getNamespace().get("vBoxAddedProductsDelete");
            ObservableList<Node> vBoxAddedProductsList = vBoxAddedProducts.getChildren();
            ObservableList<Node> vBoxAddedProductsFinalPriceList = vBoxAddedProductsFinalPrice.getChildren();
            ObservableList<Node> vBoxAddedProductsDeleteList = vBoxAddedProductsDelete.getChildren();
            vBoxAddedProductsList.clear();
            vBoxAddedProductsFinalPriceList.clear();
            vBoxAddedProductsDeleteList.clear();
            Label finalizePriceLabel = (Label) loader.getNamespace().get("finalizePriceLabel");
            finalizePriceLabel.setText("0.00PLN");

            //CREATING NEW SETTLEMENT/////////////////////////////////////////////////////////////////

            long settlement_id=0;
            String products_ids="";
            String method=Products_LoadProducts.selectedPaymentMethod;
                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            String dateTime = currentDateTime.format(formatter);
            double totalPrice=0.0;
            for (AddedProduct addedProduct_i : addedProduct_x)
            {
                for(int i=0; i<addedProduct_i.getAmount(); i++) products_ids+=addedProduct_i.getId()+" ";
                totalPrice+=addedProduct_i.getFinalPrice();
            }

            Settlement newSettlement = new Settlement();
                newSettlement.setSettlement_id(settlement_id);
                newSettlement.setProducts_ids(products_ids);
                newSettlement.setMethod(method);
                newSettlement.setDate(dateTime);
                newSettlement.setTotal_price(totalPrice);
             Settlements.addSettlement(newSettlement);

            //clearing
            addedProduct_x.clear();
        }
    }
}
