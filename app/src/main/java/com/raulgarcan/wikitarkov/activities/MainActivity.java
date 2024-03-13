package com.raulgarcan.wikitarkov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.Ammo;

import java.util.ArrayList;

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

        //addAmmoDB();

        Spinner sp = findViewById(R.id.sp_test_ammo);
        FirebaseHelper helper = new FirebaseHelper();
        ArrayList<Ammo> ammoList = helper.getAmmoDB("pistol","762x25");
        String[] ammosName = new String[ammoList.size()];
        for(int i = 0; i<ammoList.size(); i++){
            ammosName[i] = ammoList.get(i).getCaliber()+" "+ammoList.get(i).getLongName();
            Log.d("AmmoElement",ammoList.get(i).toString());
        }
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ammosName);
        sp.setAdapter(adapter);
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
    private void addAmmoDB(){
        long[] penPerTier = {6,6,4,1,0,0};
        Ammo ammo = new Ammo(7,"7.62x25mm","TT Pst gzh","Pst",50d,
                25d,36d,0d,0d,20d,0d,0d,430d);
        ammo.setPenPerTier(penPerTier);
        FirebaseHelper helper = new FirebaseHelper();
        helper.addAmmo(ammo, "pistol","762x25");
    }
}