package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyLog;
import com.example.subprojectma14ctttantritoanlenhuyn5.adapter.CartAdapter;
import com.example.subprojectma14ctttantritoanlenhuyn5.entity.Food;
import com.example.subprojectma14ctttantritoanlenhuyn5.entity.MyCart;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;

public class Screen_FoodDetail extends AppCompatActivity {

    private ImageView image, btnDecreaseQuantity, btnIncreaseQuantity;
    private TextView tvName, tvDescrip, tvPrice, tvQuantity;
    private Button bt_addtocart, btnBackToMenu;
    private LinkedList<MyCart> myCarts = new LinkedList<>();
    int count = 0;
    double price = 0.0 ;
    String url = "https://sub-ma-food.herokuapp.com/api/cart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_food_detail);

        initView();

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("entity");
        Food food = (Food) bundle.getSerializable("food");
        tvName.setText(food.getName());
        tvDescrip.setText(food.getDescription());
        tvPrice.setText(String.valueOf(food.getPrice()));
        Picasso.get().load(food.getImvFood()).into(image);
        myCarts.add(new MyCart(food.getImvFood(), food.getName(), food.getPrice()));
        price = Double.parseDouble(tvPrice.getText().toString());

//        ----------------------------ADD TO CART--------------------------
        bt_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToJsonAPI(url, food);
            }
        });

        btnIncreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Integer.parseInt(tvQuantity.getText().toString()) > 0){
                    btnDecreaseQuantity.setVisibility(View.VISIBLE);
                }
                count =  Integer.parseInt(tvQuantity.getText().toString());
                count++;
                tvQuantity.setText(String.valueOf(count));

                double p = (double) Math.round(price * count * 1000) / 1000;
                tvPrice.setText(String.valueOf(p));
            }
        });

        btnDecreaseQuantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( Integer.parseInt(tvQuantity.getText().toString()) <= 1){
                    btnDecreaseQuantity.setVisibility(View.INVISIBLE);
                }
                count = Integer.parseInt(tvQuantity.getText().toString());
                count--;
                tvQuantity.setText(String.valueOf(count));

                double p = (double) Math.round(price * count * 1000) / 1000;
                tvPrice.setText(String.valueOf(p));
            }
        });

        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Screen_FoodDetail.this, Screen_Home.class));
                finish();
            }
        });
    }

    private void initView() {
        image = findViewById(R.id.imgFoodDetail);
        tvName = findViewById(R.id.textName);
        tvDescrip = findViewById(R.id.textDescription);
        tvPrice = findViewById(R.id.tvPriceDetail);
        bt_addtocart = findViewById(R.id.btnAddToCart);
        btnBackToMenu = findViewById(R.id.backToMenu);
        btnDecreaseQuantity = findViewById(R.id.btn_decrease_quantity);
        btnIncreaseQuantity = findViewById(R.id.btn_increase_quantity);
        tvQuantity = findViewById(R.id.tv_quantity);
    }

    //    -------------------- ADD DATA-----------------------------
    private void postDataToJsonAPI(String url, Food food) {
        // Optional Parameters to pass as POST request
        JSONObject js = new JSONObject();
        try {
            js.put("name", food.getName());
            js.put("price", Double.parseDouble(tvPrice.getText().toString()));
            js.put("urlImage", food.getImvFood());
            js.put("quantity", Integer.parseInt(tvQuantity.getText().toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Screen_FoodDetail.this
                                , tvQuantity.getText().toString() + " " + tvName.getText().toString() + " is added"
                                , Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TAG", "Error: " + error.getMessage());
            }
        }) {
            /**
             * Passing some request headers
             */
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }
        };
        // Adding request to request queue
        Volley.newRequestQueue(this).add(jsonObjReq);
    }
}



