package com.raulgarcan.wikitarkov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.R;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email_login);
        etPassword = findViewById(R.id.et_password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });
    }
    private void logIn(){
        String email = etEmail.getText().toString().trim().replaceAll(" ","");
        String password = etPassword.getText().toString().trim().replaceAll(" ","");
        if(checkFieldsLogIn(email, password)){
            FirebaseHelper helper = new FirebaseHelper();
            helper.logIn(email, password, MainActivity.this);
        }
    }
    private boolean checkFieldsLogIn(String email, String password){
        return !email.isEmpty() && !password.isEmpty() && checkEmail(email) && checkPassword(password);
    }
    private boolean checkEmail(String email){
        // Implements gmail system?
        if(email.split("@").length!=2){
            return false;
        }
        return true;
    }
    private boolean checkPassword(String password){
        if(password.length()<8){
            return false;
        }
        if(!containsMayusc(password)){
            return false;
        }
        if(!containsNumber(password)){
            return false;
        }
        return true;
    }
    private boolean containsMayusc(String text){
        for(char c : text.toCharArray()){
            if(Character.isUpperCase(c)){
                return true;
            }
        }
        return false;
    }
    private boolean containsNumber(String text){
        for(char c : text.toCharArray()){
            if(Character.isDigit(c)){
                return true;
            }
        }
        return false;
    }
}