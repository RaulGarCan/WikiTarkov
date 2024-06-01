package com.raulgarcan.wikitarkov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.enums.MapTarkov;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the main activity after the splash screen duration
                Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2500);
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
}