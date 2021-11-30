package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Screen_FoodDetail extends AppCompatActivity {

    private ImageView image;
    private TextView tvName, tvDescrip, tvPrice;

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
        tvPrice.setText(String.valueOf(food.getPrice()) + "$");
        Picasso.get().load(food.getImvFood()).into(image);
    }

    private void initView() {
        image = findViewById(R.id.imgFoodDetail);
        tvName = findViewById(R.id.textName);
        tvDescrip = findViewById(R.id.textDescription);
        tvPrice = findViewById(R.id.tvPriceDetail);
    }
}