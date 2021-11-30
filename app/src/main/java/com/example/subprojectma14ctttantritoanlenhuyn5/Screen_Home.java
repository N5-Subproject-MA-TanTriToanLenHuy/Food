package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class Screen_Home extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView rv_food, rv_food_1;
    private FoodAdapter foodAdapter;
    private LinkedList<Food> foods = new LinkedList<>();

    String url = "https://sub-ma-food.herokuapp.com/api/food";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);

        initView();

        getDataFromJsonAPI();
//        String image = "https://i.imgur.com/OK1u0FO.jpeg";
//        foods.add(new Food(image, "From MCDonald's", "The BTS Meal", 3D));
//        foods.add(new Food(image, "From MCDonald's", "The BTS Meal", 3D));
//        foods.add(new Food(image, "From MCDonald's", "The BTS Meal", 3D));
//        foods.add(new Food(image, "From MCDonald's", "The BTS Meal", 3D));
//
//        foodAdapter = new FoodAdapter(foods, this, Screen_Home.this);
//        rv_food.setAdapter(foodAdapter);
//        rv_food.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//        rv_food_1.setAdapter(foodAdapter);
//        rv_food_1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void initView() {
        rv_food = findViewById(R.id.rv);
        rv_food_1 = findViewById(R.id.rv_1);
    }

    @Override
    public void onClick(View v) {

    }

    private void getDataFromJsonAPI() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);

                        String name = object.getString("name");
                        double price = object.getDouble("price");
                        String description = object.getString("description");
                        String image = object.getString("urlImage");

                        foods.add(new Food(image, name, description, price));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                foodAdapter = new FoodAdapter(foods, Screen_Home.this, Screen_Home.this);
                rv_food.setAdapter(foodAdapter);
                rv_food.setLayoutManager(new LinearLayoutManager(Screen_Home.this, LinearLayoutManager.HORIZONTAL, false));

                rv_food_1.setAdapter(foodAdapter);
                rv_food_1.setLayoutManager(new LinearLayoutManager(Screen_Home.this, LinearLayoutManager.HORIZONTAL, false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

}