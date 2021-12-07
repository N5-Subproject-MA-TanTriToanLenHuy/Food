package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    private LinkedList<MyCart> myCarts = new LinkedList<>();

    String url = "https://sub-ma-food.herokuapp.com/api/cart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_cart_list);

        initView();
        cart();

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
    }

    private void cart() {

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + "/carts" , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);

                        String name = object.getString("name");
                        double price = object.getDouble("price");
                        String image = object.getString("urlImage");

                        myCarts.add(new MyCart(image, name, price));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
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
}
