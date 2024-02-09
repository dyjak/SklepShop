package com.example.sklepshop.entities;


import javax.persistence.*;

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @Column(name = "product_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;

    @Column(name = "category")
    String category;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    double price;

    @Override
    public String toString() {
        return "Product: " +
                "id = " + product_id +
                ",   category = " + category +
                ",   name = " + name +
                ",   price = " + price;
    }

    public Product(String name) {
        this.name = name;
    }

    public Product() {

    }

    public Long getProduct_id() { return product_id; }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) { this.category = category; }


    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) { this.price = price; }
}

