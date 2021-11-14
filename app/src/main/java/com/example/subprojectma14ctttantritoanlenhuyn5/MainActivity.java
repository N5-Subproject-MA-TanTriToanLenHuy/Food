package com.example.subprojectma14ctttantritoanlenhuyn5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void showHidePass(View view){
        EditText editText = findViewById(R.id.edtPassword);
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