package com.raulgarcan.wikitarkov.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.fragments.AmmoFragment;
import com.raulgarcan.wikitarkov.fragments.DefaultFragment;
import com.raulgarcan.wikitarkov.fragments.MapsFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        toolbar = findViewById(R.id.home_toolbar);
        drawerLayout = findViewById(R.id.home_drawer);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.home_navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        AmmoFragment defaultFragment = new AmmoFragment(HomeActivity.this);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_fragments,defaultFragment).commit();
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.side_menu_ammo) {
            // Acción para la opción 1
            // Puedes abrir un nuevo fragmento, iniciar una nueva actividad, etc.

            AmmoFragment ammoFragment = new AmmoFragment(HomeActivity.this);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_frame_fragments, ammoFragment)
                    .commit();

        } else if(itemId == R.id.side_menu_wishlist){

        } else if(itemId == R.id.side_menu_maps){
            MapsFragment mapsFragment = new MapsFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.home_frame_fragments, mapsFragment)
                    .commit();
        } else if(itemId == R.id.side_menu_hideout){

        } else if(itemId == R.id.side_menu_logout){
            FirebaseHelper helper = new FirebaseHelper(this);
            helper.logOut(this);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}