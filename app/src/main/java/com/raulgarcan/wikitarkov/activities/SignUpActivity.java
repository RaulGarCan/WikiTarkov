package com.raulgarcan.wikitarkov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.ErrorMsg;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private Button btnSignUp, btnGoback;
    private EditText etEmail, etPassword, etField1, etField2, etField3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.et_email_signup);
        etPassword = findViewById(R.id.et_password_signup);
        etField1 = findViewById(R.id.et_field1_signup);
        etField2 = findViewById(R.id.et_field2_signup);
        etField3 = findViewById(R.id.et_field3_signup);
        btnSignUp = findViewById(R.id.btn_signup);
        btnGoback = findViewById(R.id.btn_signup_goback);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> data = new HashMap<>();
                data.put("field1","f1");
                data.put("field2","f2");
                data.put("field3","f3");
                String email = etEmail.getText().toString().trim().replaceAll(" ","");
                String password = etPassword.getText().toString().trim().replaceAll(" ","");
                ErrorMsg errorMsg = new ErrorMsg("");
                if(checkFieldsSignUp(email, password, data, errorMsg)){
                    signUp(email, password, data);
                } else {
                    Toast.makeText(SignUpActivity.this, errorMsg.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            }
        });
    }
    private void signUp(String email, String password, HashMap<String, Object> data){
        FirebaseHelper helper = new FirebaseHelper();
        helper.signUp(email, password, data, this);
    }
    private boolean checkFieldsSignUp(String email, String password, HashMap<String, Object> data, ErrorMsg errorMsg){
        if(data.get("field1").toString().isEmpty() || data.get("field2").toString().isEmpty() || data.get("field3").toString().isEmpty()
                || email.isEmpty() || password.isEmpty()){
            errorMsg.setMessage("Fill all the fields");
            return false;
        }
        return checkEmail(email, errorMsg) && checkPassword(password, errorMsg);
    }
    private boolean checkEmail(String email, ErrorMsg errorMsg){
        if(email.split("@").length!=2){
            errorMsg.setMessage("Invalid email");
            return false;
        }
        return true;
    }
    private boolean checkPassword(String password, ErrorMsg errorMsg){
        if(password.length()<8){
            errorMsg.setMessage("Password must be 8 length");
            return false;
        }
        if(!containsMayusc(password)){
            errorMsg.setMessage("Password must contain at least 1 capital letter");
            return false;
        }
        if(!containsNumber(password)){
            errorMsg.setMessage("Password must contain at least 1 number");
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