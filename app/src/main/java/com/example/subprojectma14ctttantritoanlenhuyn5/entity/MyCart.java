package com.example.subprojectma14ctttantritoanlenhuyn5.entity;

import java.io.Serializable;

public class MyCart implements Serializable {

    private int id;
    private String imvFood;
    private String name;
    private Double price;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MyCart(int id, String imvFood, String name, Double price, int quantity) {
        this.imvFood = imvFood;
        this.name = name;
        this.price = price;
        this.id = id;
        this.quantity = quantity;

    }

    public MyCart( String imvFood, String name, Double price) {
        this.imvFood = imvFood;
        this.name = name;
        this.price = price;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImvFood() {
        return imvFood;
    }

    public void setImvFood(String imvFood) {
        this.imvFood = imvFood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }




}
