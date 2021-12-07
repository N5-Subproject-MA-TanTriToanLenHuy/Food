package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

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

    private RecyclerView rv_foodTrending, rv_foodFavourites;
    private FoodAdapterTrending foodAdapterTrending;
    private FoodAdapterFavourites foodAdapterFavourites;
    private ImageView imv_cart;
    private LinkedList<Food> foodsTrending = new LinkedList<>();
    private LinkedList<Food> foodsFavorites = new LinkedList<>();

    String url = "https://sub-ma-food.herokuapp.com/api/food";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);

        initView();

        trendingFood();
        favouritesFood();

        imv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Screen_Home.this, Screen_CartList.class);
                startActivity(it);
            }
        });

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
        rv_foodTrending = findViewById(R.id.rv);
        rv_foodFavourites = findViewById(R.id.rv_1);
        imv_cart = findViewById(R.id.imv_cart);
    }

    @Override
    public void onClick(View v) {

    }

    private void trendingFood() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + "/trending", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);

                        String name = object.getString("name");
                        double price = object.getDouble("price");
                        String description = object.getString("description");
                        String image = object.getString("urlImage");

                        foodsTrending.add(new Food(image, name, description, price));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                foodAdapterTrending = new FoodAdapterTrending(foodsTrending, Screen_Home.this, Screen_Home.this);
                rv_foodTrending.setAdapter(foodAdapterTrending);
                rv_foodTrending.setLayoutManager(new LinearLayoutManager(Screen_Home.this, LinearLayoutManager.HORIZONTAL, false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private void favouritesFood() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + "/favourites", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);

                        String name = object.getString("name");
                        double price = object.getDouble("price");
                        String description = object.getString("description");
                        String image = object.getString("urlImage");

                        foodsFavorites.add(new Food(image, name, description, price));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                foodAdapterFavourites = new FoodAdapterFavourites(foodsFavorites, Screen_Home.this, Screen_Home.this);

                rv_foodFavourites.setAdapter(foodAdapterFavourites);
                rv_foodFavourites.setLayoutManager(new LinearLayoutManager(Screen_Home.this, LinearLayoutManager.HORIZONTAL, false));
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