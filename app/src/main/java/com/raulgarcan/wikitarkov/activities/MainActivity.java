package com.raulgarcan.wikitarkov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.Ammo;
import com.raulgarcan.wikitarkov.pojo.ErrorMsg;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String currentUser = preferences.getString("CurrentUser","");
        if(currentUser.isEmpty()){
            startActivity(new Intent(this, HomeActivity.class));
        }
        etEmail = findViewById(R.id.et_email_login);
        etPassword = findViewById(R.id.et_password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup_activity);

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

        //addAmmoDB();

        //Spinner sp = findViewById(R.id.sp_test_ammo);
        //FirebaseHelper helper = new FirebaseHelper();
        //helper.getAmmoDB("pistol","762x25",sp,this);
    }
    private void logIn(){
        String email = etEmail.getText().toString().trim().replaceAll(" ","");
        String password = etPassword.getText().toString().trim().replaceAll(" ","");
        ErrorMsg errorMsg = new ErrorMsg("");
        if(checkFieldsLogIn(email, password, errorMsg)){
            FirebaseHelper helper = new FirebaseHelper();
            helper.logIn(email, password, MainActivity.this);
        } else {
            Toast.makeText(this, errorMsg.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private boolean checkFieldsLogIn(String email, String password, ErrorMsg errorMsg){
        errorMsg.setMessage("Fill all the fields");
        return !email.isEmpty() && !password.isEmpty();
    }
    private void addAmmoDB(){
        long[] penPerTier = {6,5,1,0,0,0};
        Ammo ammo = new Ammo(1,"46x30mm","Action SX","Action SX",65d,
                18d,28d,0d,0d,0d,0d,690d);
        ammo.setPenPerTier(penPerTier);
        FirebaseHelper helper = new FirebaseHelper();
        helper.addAmmo(ammo, "pwd","46x30mm");

        penPerTier = new long[]{6, 6, 3, 0, 0, 0};
        ammo = new Ammo(2, "46x30mm", "Subsonic SX", "Subsonic SX", 52d,
                23d, 33d, 10d, -22d, 0d, 0d, 290d);
        ammo.setPenPerTier(penPerTier);
        helper = new FirebaseHelper();
        helper.addAmmo(ammo, "pwd","46x30mm");

        penPerTier = new long[]{6, 6, 6, 4, 2, 1};
        ammo = new Ammo(3, "46x30mm", "JSP SX", "JSP SX", 46d,
                32d, 37d, 0d, 0d, 0d, 0d, 579d);
        ammo.setPenPerTier(penPerTier);
        helper = new FirebaseHelper();
        helper.addAmmo(ammo, "pwd","46x30mm");

        penPerTier = new long[]{6, 6, 6, 6, 4, 3};
        ammo = new Ammo(4, "46x30mm", "FMJ SX", "FMJ SX", 43d,
                40d, 41d, 0d, 0d, 0d, 0d, 620d);
        ammo.setPenPerTier(penPerTier);
        helper = new FirebaseHelper();
        helper.addAmmo(ammo, "pwd","46x30mm");

        penPerTier = new long[]{6, 6, 6, 6, 6, 5};
        ammo = new Ammo(5, "46x30mm", "AP SX", "AP SX", 35d,
                53d, 46d, 0d, 10d, 0d, 0d, 680d);
        ammo.setPenPerTier(penPerTier);
        helper = new FirebaseHelper();
        helper.addAmmo(ammo, "pwd","46x30mm");

    }
}