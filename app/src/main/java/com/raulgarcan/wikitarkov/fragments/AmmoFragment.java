package com.raulgarcan.wikitarkov.fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.raulgarcan.wikitarkov.FirebaseHelper;
import com.raulgarcan.wikitarkov.R;
import com.raulgarcan.wikitarkov.pojo.enums.Caliber;
import com.raulgarcan.wikitarkov.pojo.enums.Guns;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AmmoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AmmoFragment extends Fragment {
    private Activity activity;
    public AmmoFragment() {
        // Required empty public constructor
    }
    public AmmoFragment(Activity activity) {
        this.activity = activity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AmmoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AmmoFragment newInstance() {
        AmmoFragment fragment = new AmmoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ammo, container, false);
        Spinner spGuns = v.findViewById(R.id.sp_guns_type);
        Spinner spCalibers = v.findViewById(R.id.sp_ammo_caliber);
        RecyclerView rvAmmoList = v.findViewById(R.id.rv_ammo_list);
        rvAmmoList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        String[] gunsName = new String[Guns.values().length];
        Guns[] guns = Guns.values();
        for(int i = 0; i<guns.length; i++){
            gunsName[i] = guns[i].getDisplayName();
        }
        spGuns.setAdapter(new ArrayAdapter<CharSequence>(this.getContext(), R.layout.ammo_spinner_item,gunsName));
        spGuns.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> ammoCaliberList = getAmmoCaliberList(Guns.getGunByName(spGuns.getAdapter().getItem(position).toString()));
                String[] calibers = new String[ammoCaliberList.size()];
                for(int i = 0; i<ammoCaliberList.size(); i++){
                    calibers[i] = ammoCaliberList.get(i);
                }
                spCalibers.setAdapter(new ArrayAdapter<CharSequence>(AmmoFragment.this.getContext(),  R.layout.ammo_spinner_item,calibers));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spCalibers.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fillRecyclerView(rvAmmoList, Caliber.getCaliberByName(spCalibers.getSelectedItem().toString()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spGuns.setSelection(spGuns.getAdapter().getCount()-1);
        return v;
    }
    private ArrayList<String> getAmmoCaliberList(Guns selectedGun){
        ArrayList<String> ammoCaliberList = new ArrayList<>();
        for(Caliber c : Caliber.values()){
            if(c.getGunType().equals(selectedGun) || selectedGun.equals(Guns.ALL)){
                ammoCaliberList.add(c.getDisplayName());
            }
        }
        return ammoCaliberList;
    }
    private void fillRecyclerView(RecyclerView rv, Caliber caliber){
        FirebaseHelper helper = new FirebaseHelper();
        Log.d("GetAmmoDB",caliber.getGunType().name().toLowerCase()+" "+caliber.getInternalName());
        helper.getAmmoDB(caliber.getGunType().name().toLowerCase(), caliber.getInternalName(), rv,activity);
    }
}