package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.LinkedList;

public class Screen_Home extends AppCompatActivity implements View.OnClickListener{

    private int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView rv_food, rv_food_1;
        AdapterHomeFood adapterHomeFood;
        LinkedList<HomeFood> homeFoods = new LinkedList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_home);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(Screen_Home.this);
        if (acct != null) {
            findViewById(R.id.button2).setVisibility(View.VISIBLE);
        }

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

        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                signOut();
                break;
        }
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(Screen_Home.this, MainActivity.class));
                        finish();
                    }
                });
    }
}