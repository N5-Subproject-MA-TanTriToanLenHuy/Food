package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.subprojectma14ctttantritoanlenhuyn5.model.User;
import com.example.subprojectma14ctttantritoanlenhuyn5.remote.APICall;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int RC_SIGN_IN = 1;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView tvUsername, tvPassword;
    private Retrofit retrofit;
    public static final String BASE_URL = "https://n5-apigateway-food.herokuapp.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.enter_x, R.anim.exit_x);

        initView();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(MainActivity.this);
        if (acct != null) {
            Toast.makeText(this, "Đăng nhập Google thành công", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Screen_Home.class));
            finish();
        }

        findViewById(R.id.status).setOnClickListener(this);
        findViewById(R.id.tvSignUp).setOnClickListener(this);
        findViewById(R.id.btnLogIn).setOnClickListener(this);
    }

    private void initView() {
        tvUsername = findViewById(R.id.userNameLogin);
        tvPassword = findViewById(R.id.passwordLogin);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.status:
                signInWithGoogle();
                break;
            case R.id.tvSignUp:
                startActivity(new Intent(this, RegisterAccount.class));
                finish();
                break;
            case R.id.btnLogIn:
                loginJWT();
                break;
        }
    }

    private void loginJWT() {
        build();
        APICall apiCall = retrofit.create(APICall.class);

        String username = tvUsername.getText().toString();
        String password = tvPassword.getText().toString();

        User user = new User(username, password);

        Call<User> call = apiCall.userLogin(user);

        call.enqueue(new Callback<User>(){
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {

                    User data = response.body();
                    String Dname = data.getUsername();
                    String token = data.getToken();
                    Toast.makeText(MainActivity.this, token.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("TAG", t.getMessage());
                Toast.makeText(MainActivity.this, "Invalid username or password, please try again", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void build() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            GoogleSignInAccount account = result.getSignInAccount();
            Toast.makeText(this, "Đăng nhập Google thành công", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, Screen_Home.class));
            finish();
        }else {

        }
    }

    public void showHidePass(View view){
        EditText editText = findViewById(R.id.passwordLogin);
        TextView textView = findViewById(R.id.tvShowHidePass);
        if(view.getId() == R.id.tvShowHidePass){
            if(editText.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                textView.setText("Hide");
                editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else {
                textView.setText("Show");
                editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }
}