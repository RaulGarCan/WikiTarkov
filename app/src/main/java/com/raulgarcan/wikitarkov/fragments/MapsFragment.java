package com.raulgarcan.wikitarkov.fragments;

import android.content.res.ColorStateList;
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

import com.google.android.material.tabs.TabLayout;
import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.enums.MapTarkov;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapsFragment extends Fragment {
    private TabLayout tabLayoutTop, tabLayoutBottom;
    private Drawable indicator;

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
        indicator = tabLayoutTop.getTabSelectedIndicator();

        MapTarkov[] mapTarkovs = MapTarkov.values();
        ArrayList<MapTarkov> mapTarkovTop = new ArrayList<>();
        ArrayList<MapTarkov> mapTarkovBottom = new ArrayList<>();
        for(int i = 0; i<mapTarkovs.length; i++){
            if(i%2==0){
                mapTarkovTop.add(mapTarkovs[i]);
            } else {
                mapTarkovBottom.add(mapTarkovs[i]);
            }
        }
        for(MapTarkov m : mapTarkovTop){
            tabLayoutTop.addTab(tabLayoutTop.newTab().setText(m.getDisplayName()));
        }
        for(MapTarkov m : mapTarkovBottom){
            tabLayoutBottom.addTab(tabLayoutBottom.newTab().setText(m.getDisplayName()));
        }

        deselectTabBottom();
        tabLayoutTop.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        tabLayoutBottom.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.black)));
        tabLayoutTop.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab!=null) {
                    deselectTabBottom();
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
    private void showSelectedMap(TabLayout.Tab tab){
        Log.d("SelectedMap",tab.getText().toString());
    }
}