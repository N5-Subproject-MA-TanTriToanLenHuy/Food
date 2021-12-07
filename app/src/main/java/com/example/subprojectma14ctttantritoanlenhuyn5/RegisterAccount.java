package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.subprojectma14ctttantritoanlenhuyn5.model.JWTToken;
import com.example.subprojectma14ctttantritoanlenhuyn5.remote.APICall;
import com.example.subprojectma14ctttantritoanlenhuyn5.remote.RetroClass;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterAccount extends AppCompatActivity implements View.OnClickListener {

    EditText edtUsernme, edtPassword, edtPassConfirm;

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
        switch (v.getId()){
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

        if(!edtPassConfirm.getText().toString().matches(edtPassword.getText().toString())){
            Toast.makeText(this, "Password Confirm is not match", Toast.LENGTH_SHORT).show();
            return;
        }

        final APICall apiCall = RetroClass.getAPICall();

        Set<String> role = new HashSet<>();
        role.add("ADMIN");
        role.add("USER");

        final String username = edtUsernme.getText().toString();
        final String password = edtPassword.getText().toString();

        Call<ResponseBody> jwtTokenCall = apiCall.userRegister(username, password, role);

        jwtTokenCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                Toast.makeText(RegisterAccount.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RegisterAccount.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                VolleyLog.d("TAG", "Error: " + t.getMessage());
            }
        });

    }
}