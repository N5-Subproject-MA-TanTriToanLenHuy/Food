package com.example.subprojectma14ctttantritoanlenhuyn5;

import java.io.Serializable;

public class Food implements Serializable {
    private int imvFood;
    private String name;
    private String description;
    private Double price;

    public Food(int imvFood, String name, String description, Double price) {
        this.imvFood = imvFood;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getImvFood() {
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
