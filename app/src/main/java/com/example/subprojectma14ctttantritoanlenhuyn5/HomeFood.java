package com.example.subprojectma14ctttantritoanlenhuyn5;

import java.io.Serializable;

public class HomeFood implements Serializable {
    private int imvBackground;
    private int imvFood;
    private String nameTitle;
    private String nameTitle1;

    public HomeFood(int imvBackground, int imvFood, String nameTitle, String nameTitle1) {
        this.imvBackground = imvBackground;
        this.imvFood = imvFood;
        this.nameTitle = nameTitle;
        this.nameTitle1 = nameTitle1;
    }

    public int getImvBackground() {
        return imvBackground;
    }

    public void setImvBackground(int imvBackground) {
        this.imvBackground = imvBackground;
    }

    public int getImvFood() {
        return imvFood;
    }

    public void setImvFood(int imvFood) {
        this.imvFood = imvFood;
    }

    public String getNameTitle() {
        return nameTitle;
    }

    public void setNameTitle(String nameTitle) {
        this.nameTitle = nameTitle;
    }

    public String getNameTitle1() {
        return nameTitle1;
    }

    public void setNameTitle1(String nameTitle1) {
        this.nameTitle1 = nameTitle1;
    }
}
