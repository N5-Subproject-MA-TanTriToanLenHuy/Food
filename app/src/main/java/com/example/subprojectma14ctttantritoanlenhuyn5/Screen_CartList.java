package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class Screen_CartList extends AppCompatActivity {

    private Button bt_back;
    private RecyclerView rv_cart;
    private CartAdapter cartAdapter;
    private TextView tvTotal;
    private LinkedList<MyCart> myCarts = new LinkedList<>();

    String url = "https://sub-ma-food.herokuapp.com/api/cart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_cart_list);
        initView();
        cart();
//        Intent refresh = new Intent(this, Screen_CartList.class);
//        startActivity(refresh);
//        this.finish();

        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }




    private void initView() {
        rv_cart = findViewById(R.id.rv_cart);
        bt_back = findViewById(R.id.btn_back);
        tvTotal =  findViewById(R.id.tv_total);
    }

    private void cart() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + "/carts" , new Response.Listener<JSONArray>() {
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
                        myCarts.add(new MyCart(id,image, name, price,quantity ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                tvTotal.setText(String.valueOf(grardTotal(myCarts)) + " $");



                cartAdapter = new CartAdapter(myCarts, Screen_CartList.this, Screen_CartList.this);
                rv_cart.setAdapter(cartAdapter);
                rv_cart.setLayoutManager(new LinearLayoutManager(Screen_CartList.this, LinearLayoutManager.VERTICAL, false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });
        requestQueue.add(jsonArrayRequest);

    }

    private double grardTotal(LinkedList<MyCart> myCarts) {
        double totalPrice = 0.0;
        for(int i = 0; i < myCarts.size(); i ++){
            totalPrice += myCarts.get(i).getPrice();
        }
        return totalPrice;
    }

}
