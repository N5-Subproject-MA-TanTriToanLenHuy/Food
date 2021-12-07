package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.subprojectma14ctttantritoanlenhuyn5.adapter.CartAdapter;
import com.example.subprojectma14ctttantritoanlenhuyn5.adapter.FoodTrendingAdapter;
import com.example.subprojectma14ctttantritoanlenhuyn5.adapter.FoodFavouritesAdapter;
import com.example.subprojectma14ctttantritoanlenhuyn5.entity.Food;
import com.example.subprojectma14ctttantritoanlenhuyn5.entity.MyCart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class Screen_Home extends AppCompatActivity {

    private RecyclerView rv_foodTrending, rv_foodFavourites;
    private ImageView imv_cart;
    private FoodTrendingAdapter foodTrendingAdapter;
    private FoodFavouritesAdapter foodFavouritesAdapter;
    private LinkedList<Food> foodsTrending = new LinkedList<>();
    private LinkedList<Food> foodsFavorites = new LinkedList<>();
    private LinkedList<MyCart> myCarts = new LinkedList<>();
    private ImageView status;

    String url = "https://sub-ma-food.herokuapp.com/api/food";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);

        initView();
        status.setVisibility(View.INVISIBLE);
        trendingFood();
        favouritesFood();

        cart();

        imv_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Screen_Home.this, Screen_CartList.class);
                startActivity(it);
            }
        });

    }


    private void initView() {
        rv_foodTrending = findViewById(R.id.rv);
        rv_foodFavourites = findViewById(R.id.rv_1);
        imv_cart = findViewById(R.id.imv_cart);
        status = findViewById(R.id.status);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                foodTrendingAdapter.getFilter().filter(query);
                foodFavouritesAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                foodTrendingAdapter.getFilter().filter(newText);
                foodFavouritesAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void cart() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest("https://sub-ma-food.herokuapp.com/api/cart/carts", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        int id = object.getInt("id");
                        String name = object.getString("name");
                        double price = object.getDouble("price");
                        String image = object.getString("urlImage");
                        int quantity = object.getInt("quantity");
                        myCarts.add(new MyCart(id, image, name, price, quantity));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if(myCarts.size() > 0){
                    status.setVisibility(View.VISIBLE);
                }else {
                    status.setVisibility(View.INVISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);
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
                foodTrendingAdapter = new FoodTrendingAdapter(foodsTrending, Screen_Home.this, Screen_Home.this);
                rv_foodTrending.setAdapter(foodTrendingAdapter);
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
                foodFavouritesAdapter = new FoodFavouritesAdapter(foodsFavorites, Screen_Home.this, Screen_Home.this);

                rv_foodFavourites.setAdapter(foodFavouritesAdapter);
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