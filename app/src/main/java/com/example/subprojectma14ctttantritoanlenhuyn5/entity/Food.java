package com.example.subprojectma14ctttantritoanlenhuyn5.entity;

import java.io.Serializable;

public class Food implements Serializable {
    private String imvFood;
    private String name;
    private String description;
    private Double price;

    public Food(String imvFood, String name, String description, Double price) {
        this.imvFood = imvFood;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getImvFood() {
        return imvFood;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
}
