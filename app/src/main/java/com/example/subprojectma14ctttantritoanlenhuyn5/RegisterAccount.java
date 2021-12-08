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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

        JSONObject js = new JSONObject();

        Set<String> role = new HashSet<>();
        role.add("ADMIN");
        role.add("USER");

        try {
            js.put("username", edtUsernme.getText().toString());
            js.put("password", edtPassConfirm.getText().toString());
            js.put("role", role);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String url = "https://n5-apigateway-food.herokuapp.com/auth/register";
        // Make request for JSONObject
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                Request.Method.POST, url, js,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("TAG", response.toString());

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
//        startActivity(new Intent(RegisterAccount.this, MainActivity.class));

    }
}