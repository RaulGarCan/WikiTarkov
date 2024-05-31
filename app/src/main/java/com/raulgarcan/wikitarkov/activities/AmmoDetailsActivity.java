package com.raulgarcan.wikitarkov.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.Ammo;

public class AmmoDetailsActivity extends AppCompatActivity {
    private TextView tvAmmoDetails;
    private Button btnGoBack;
    private Ammo ammo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ammo_details);
        tvAmmoDetails = findViewById(R.id.tv_ammo_details);
        btnGoBack = findViewById(R.id.btn_ammo_details_goback);
        Intent i = this.getIntent();
        if(i!=null){
            ammo = i.getSerializableExtra("ammo", Ammo.class);
            tvAmmoDetails.setText(ammo.toString());
        }
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AmmoDetailsActivity.this, HomeActivity.class));
            }
        });
    }
}