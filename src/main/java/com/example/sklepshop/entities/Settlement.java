package com.example.sklepshop.entities;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Settlement")
public class Settlement {
    @Id
    @Column(name = "settlement_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long settlement_id;

    @Column(name = "product_ids")
    String product_ids;

    @Column(name = "method")
    String method;

    @Column(name = "date")
    String date;

    @Column(name = "total_price")
    double total_price;

    @Override
    public String toString() {
        return "Settlement{" +
                "settlement_id=" + settlement_id +
                ", products_ids='" + product_ids + '\'' +
                ", method='" + method + '\'' +
                ", date=" + date +
                ", total_price=" + total_price +
                '}';
    }

    public Settlement() {

    }


    public void setSettlement_id(long id) {
        this.settlement_id = id;
    }

    public Long getSettlement_id() {
        return settlement_id;
    }


    public String getProducts_ids() {
        return product_ids;
    }

    public void setProducts_ids(String products_ids) {
        this.product_ids = products_ids;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }
}

