package com.raulgarcan.wikitarkov.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.enums.Caliber;
import com.raulgarcan.wikitarkov.data.Main;
import com.raulgarcan.wikitarkov.pojo.Ammo;
import com.raulgarcan.wikitarkov.pojo.ErrorMsg;
import com.raulgarcan.wikitarkov.pojo.enums.MapTarkov;

public class MainActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin, btnSignup;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        String currentUser = preferences.getString("CurrentUser","");
        if(!currentUser.isEmpty()){
            startActivity(new Intent(this, HomeActivity.class));
        }
        etEmail = findViewById(R.id.et_email_login);
        etPassword = findViewById(R.id.et_password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnSignup = findViewById(R.id.btn_signup_activity);
        progressBar = findViewById(R.id.pb_login);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                logIn();
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        FirebaseHelper helper = new FirebaseHelper(this);
        helper.getTarkovMaps(true);

        for(MapTarkov m : MapTarkov.values()){
            new Thread(new Loader(m.getFileName())).start();
        }
        //saveDataOnDB();
    }
    class Loader implements Runnable {
        String fileName;
        Loader(String fileName){
           this.fileName = fileName;
        }
        @Override
        public void run() {
            loadMaps(fileName);
        }
    }
    private void loadMaps(String fileName){
        FirebaseHelper helper = new FirebaseHelper(this);
        FirebaseHelper.mapsImageHash.put(fileName,helper.readMap(fileName));
    }
    private void saveDataOnDB(){
        FirebaseHelper helper = new FirebaseHelper(this);
        FirebaseFirestore db = helper.getFirestore();
        Caliber[] calibers = Caliber.values();
        for(Caliber c : calibers){
            String json = Main.leerJsonAndroid("Caliber"+c.getInternalName());
            Ammo[] ammoList = new Gson().fromJson(json,Ammo[].class);
            for(Ammo a : ammoList){
                db.collection("ammo").document(c.getGunType().name().toLowerCase()).collection(c.getInternalName()).add(a).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Log.d("AmmoDataDB","Data Save Successful");
                        } else {
                            Log.w("AmmoDataDB",task.getException());
                        }
                    }
                });
            }
        }
    }
    private void logIn(){
        String email = etEmail.getText().toString().trim().replaceAll(" ","");
        String password = etPassword.getText().toString().trim().replaceAll(" ","");
        ErrorMsg errorMsg = new ErrorMsg("");
        if(checkFieldsLogIn(email, password, errorMsg)){
            FirebaseHelper helper = new FirebaseHelper(this);
            helper.logIn(email, password, MainActivity.this, progressBar);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            Toast.makeText(this, errorMsg.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private boolean checkFieldsLogIn(String email, String password, ErrorMsg errorMsg) {
        errorMsg.setMessage("Fill all the fields");
        return !email.isEmpty() && !password.isEmpty();
    }
}