package com.raulgarcan.wikitarkov.fragments;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.material.tabs.TabLayout;
import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.enums.MapTarkov;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsFragment extends Fragment {
    private TabLayout tabLayoutTop, tabLayoutBottom, tabLayoutMid;
    private Drawable indicator;
    private PhotoView pvMap;
    private Activity activity;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MapsFragment() {
        // Required empty public constructor
    }
    public MapsFragment(Activity activity) {
        // Required empty public constructor
        this.activity = activity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapsFragment newInstance(String param1, String param2) {
        MapsFragment fragment = new MapsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        tabLayoutTop = v.findViewById(R.id.tl_maps_top);
        tabLayoutBottom = v.findViewById(R.id.tl_maps_bottom);
        tabLayoutMid = v.findViewById(R.id.tl_maps_mid);
        indicator = tabLayoutTop.getTabSelectedIndicator();
        pvMap = v.findViewById(R.id.pv_map);

        MapTarkov[] mapTarkovs = MapTarkov.values();
        ArrayList<MapTarkov> mapTarkovTop = new ArrayList<>();
        ArrayList<MapTarkov> mapTarkovBottom = new ArrayList<>();
        ArrayList<MapTarkov> mapTarkovMid = new ArrayList<>();
        for(int i = 0; i<mapTarkovs.length; i++){
            int division = mapTarkovs.length/3;
            if(i<division){
                mapTarkovTop.add(mapTarkovs[i]);
            } else if(i<division+division){
                mapTarkovMid.add(mapTarkovs[i]);
            } else {
                mapTarkovBottom.add(mapTarkovs[i]);
            }
        }
        for(MapTarkov m : mapTarkovTop){
            TabLayout.Tab tab = tabLayoutTop.newTab().setText(m.getDisplayName());
            tabLayoutTop.addTab(tab);
        }
        for(MapTarkov m : mapTarkovMid){
            TabLayout.Tab tab = tabLayoutMid.newTab().setText(m.getDisplayName());
            tabLayoutMid.addTab(tab);
        }for(MapTarkov m : mapTarkovBottom){
            TabLayout.Tab tab = tabLayoutBottom.newTab().setText(m.getDisplayName());
            tabLayoutBottom.addTab(tab);
        }

        deselectTabBottom();
        tabLayoutTop.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        tabLayoutBottom.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.white)));
        tabLayoutMid.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.white)));

        tabLayoutTop.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab!=null) {
                    deselectTabBottom();
                    deselectTabMid();
                    tabLayoutTop.setSelectedTabIndicator(indicator);
                    showSelectedMap(tab);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayoutBottom.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab!=null) {
                    deselectTabTop();
                    deselectTabMid();
                    tabLayoutBottom.setSelectedTabIndicator(indicator);
                    showSelectedMap(tab);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayoutMid.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab!=null) {
                    deselectTabBottom();
                    deselectTabTop();
                    tabLayoutMid.setSelectedTabIndicator(indicator);
                    showSelectedMap(tab);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        showSelectedMap(tabLayoutTop.getTabAt(tabLayoutTop.getSelectedTabPosition()));
        return v;
    }
    private void deselectTabBottom(){
        tabLayoutBottom.selectTab(null);
        tabLayoutBottom.setSelectedTabIndicator(null);
    }
    private void deselectTabTop(){
        tabLayoutTop.selectTab(null);
        tabLayoutTop.setSelectedTabIndicator(null);
    }
    private void deselectTabMid(){
        tabLayoutMid.selectTab(null);
        tabLayoutMid.setSelectedTabIndicator(null);
    }
    private void showSelectedMap(TabLayout.Tab tab){
        Log.d("SelectedMap",tab.getText().toString());
        String fileName = MapTarkov.getMapByName(tab.getText().toString()).getFileName();
        FirebaseHelper helper = new FirebaseHelper(activity);
        if(FirebaseHelper.mapsImageHash.get(fileName)==null){
            FirebaseHelper.mapsImageHash.put(fileName,helper.readMap(fileName));
        }
        byte[] image = FirebaseHelper.mapsImageHash.get(fileName);
        if(image!=null){
            Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
            pvMap.setImageBitmap(bmp);
        }
    }
}