package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.subprojectma14ctttantritoanlenhuyn5.model.User;
import com.example.subprojectma14ctttantritoanlenhuyn5.model.UserCreate;
import com.example.subprojectma14ctttantritoanlenhuyn5.remote.APICall;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterAccount extends AppCompatActivity implements View.OnClickListener {

    private EditText edtUsernme, edtPassword, edtPassConfirm;
    private Retrofit retrofit;
    public static final String BASE_URL = "https://n5-apigateway-food.herokuapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_account);

        overridePendingTransition(R.anim.leave, R.anim.enter);

        initView();

        findViewById(R.id.tvLogin).setOnClickListener(this);
        findViewById(R.id.btnSubmit).setOnClickListener(this);
    }

    private void initView() {
        edtUsernme = findViewById(R.id.usernameSignUp);
        edtPassword = findViewById(R.id.passwordSignUp);
        edtPassConfirm = findViewById(R.id.passwordConfirm);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvLogin:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.btnSubmit:
                register();
                break;
        }
    }

    private void register() {

        if (!edtPassConfirm.getText().toString().matches(edtPassword.getText().toString())) {
            Toast.makeText(this, "Password Confirm is not match", Toast.LENGTH_SHORT).show();
            return;
        }

        Set<String> role = new HashSet<>();
        role.add("USER");

        build();
        APICall apiCall = retrofit.create(APICall.class);

        String username = edtUsernme.getText().toString();
        String password = edtPassConfirm.getText().toString();

        UserCreate user = new UserCreate(username, password, role);

        Call<UserCreate> call = apiCall.userSignUp(user);

        call.enqueue(new Callback<UserCreate>(){
            @Override
            public void onResponse(Call<UserCreate> call, Response<UserCreate> response) {

                if (response.isSuccessful()) {
                    Toast.makeText(RegisterAccount.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegisterAccount.this, MainActivity.class));
                    finish();
                }else {
                    Toast.makeText(RegisterAccount.this, "Invalid username or password, please try again", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserCreate> call, Throwable t) {
                Log.d("TAG", t.getMessage());
            }
        });
    }

    private void build() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}