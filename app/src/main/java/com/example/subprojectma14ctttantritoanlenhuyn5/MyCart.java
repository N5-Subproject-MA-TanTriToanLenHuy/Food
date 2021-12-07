package com.example.subprojectma14ctttantritoanlenhuyn5;

import java.io.Serializable;

public class MyCart implements Serializable {

    private String imvFood;
    private String name;
    private Double price;

    public MyCart(String imvFood, String name, Double price) {
        this.imvFood = imvFood;
        this.name = name;
        this.price = price;
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
