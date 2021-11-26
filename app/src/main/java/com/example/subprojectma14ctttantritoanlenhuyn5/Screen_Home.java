package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.LinkedList;

public class Screen_Home extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView rv_food, rv_food_1;
        FoodAdapter foodAdapter;
        LinkedList<Food> foods = new LinkedList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);

        rv_food = findViewById(R.id.rv);
        rv_food_1 = findViewById(R.id.rv_1);

        foods.add(new Food(R.drawable.pic1, "From MCDonald's", "The BTS Meal", 3D));
        foods.add(new Food(R.drawable.pic1, "From MCDonald's", "The BTS Meal", 3D));
        foods.add(new Food(R.drawable.pic1, "From MCDonald's", "The BTS Meal", 3D));
        foods.add(new Food(R.drawable.pic1, "From MCDonald's", "The BTS Meal", 3D));

        foodAdapter = new FoodAdapter(foods, this, Screen_Home.this);
        rv_food.setAdapter(foodAdapter);
        rv_food.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        rv_food_1.setAdapter(foodAdapter);
        rv_food_1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onClick(View v) {

    }

}