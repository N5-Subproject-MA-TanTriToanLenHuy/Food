package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;

import java.util.LinkedList;

public class Screen_Home extends AppCompatActivity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView rv_food, rv_food_1;
        AdapterHomeFood adapterHomeFood;
        LinkedList<HomeFood> homeFoods = new LinkedList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);

        rv_food = findViewById(R.id.rv);
        rv_food_1 = findViewById(R.id.rv_1);

        homeFoods.add(new HomeFood(R.drawable.thebtsmeal, R.drawable.pic1, "From MCDonald's", "The BTS Meal"));
        homeFoods.add(new HomeFood(R.drawable.thebtsmeal, R.drawable.pic1, "From MCDonald's", "The BTS Meal"));
        homeFoods.add(new HomeFood(R.drawable.thebtsmeal, R.drawable.pic1, "From MCDonald's", "The BTS Meal"));
        homeFoods.add(new HomeFood(R.drawable.thebtsmeal, R.drawable.pic1, "From MCDonald's", "The BTS Meal"));

        adapterHomeFood = new AdapterHomeFood(homeFoods, this, Screen_Home.this);
        rv_food.setAdapter(adapterHomeFood);
        rv_food.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rv_food_1.setAdapter(adapterHomeFood);
        rv_food_1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onClick(View v) {

    }

}