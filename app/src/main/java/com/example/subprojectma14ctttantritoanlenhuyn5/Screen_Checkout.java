package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.subprojectma14ctttantritoanlenhuyn5.adapter.CartAdapter;
import com.example.subprojectma14ctttantritoanlenhuyn5.entity.MyCart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class Screen_Checkout extends AppCompatActivity implements View.OnClickListener {

    EditText etName, etPhone, etAddress;
    TextView tvTotal;
    Button btnComplete;
    ImageView btnBack;
    private LinkedList<MyCart> myCarts = new LinkedList<>();
    String url = "https://sub-ma-food.herokuapp.com/api/cart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_checkout);
        initView();
        cart();
        btnComplete.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    private void initView() {
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etSDT);
        etAddress = findViewById(R.id.etDiaChi);
        btnComplete = findViewById(R.id.btnCheckOut);
        tvTotal = findViewById(R.id.tv_TongThanhToan);
        btnBack = findViewById(R.id.btnBackCheckout);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnCheckOut:
                checkOut();
                break;
            case R.id.btnBackCheckout:
                startActivity(new Intent(Screen_Checkout.this, Screen_CartList.class));
                finish();
                break;
        }
    }

    private void checkOut() {
        if (etName.getText().toString().trim().equals("")){
            Toast.makeText(this, "Invalid Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etPhone.getText().toString().trim().equals("")){
            Toast.makeText(this, "Invalid phone", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etAddress.getText().toString().trim().equals("")){
            Toast.makeText(this, "Invalid address", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this,"Checkout successfully", Toast.LENGTH_LONG).show();

        for(int i = 0; i < myCarts.size(); i ++){
            DeleteDataToJsonAPI(url, myCarts.get(i).getId());
        }

        startActivity(new Intent(Screen_Checkout.this, Screen_Home.class));
        finish();
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

    //        --------

    private void DeleteDataToJsonAPI(String url, int id) {
        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE, url + "/" + id, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(Screen_Checkout.this);
        requestQueue.add(stringRequest);
    }
}