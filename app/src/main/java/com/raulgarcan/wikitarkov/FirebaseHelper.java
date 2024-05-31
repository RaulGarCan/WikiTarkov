package com.raulgarcan.wikitarkov;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.raulgarcan.wikitarkov.activities.HomeActivity;
import com.raulgarcan.wikitarkov.activities.MainActivity;
import com.raulgarcan.wikitarkov.pojo.Ammo;
import com.raulgarcan.wikitarkov.pojo.enums.MapTarkov;
import com.raulgarcan.wikitarkov.recyclers.AmmoAdapter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class FirebaseHelper {
    private final FirebaseAuth auth;
    private final FirebaseFirestore firestore;
    private final FirebaseStorage storage;
    private final String pathFiles;
    private final Activity activity;

    public FirebaseHelper(Activity activity) {
        this.auth = FirebaseAuth.getInstance();
        this.firestore = FirebaseFirestore.getInstance();
        this.storage = FirebaseStorage.getInstance("gs://wikitarkov.appspot.com");
        this.activity = activity;
        this.pathFiles = activity.getFilesDir().getPath();
    }

    public FirebaseFirestore getFirestore() {
        return firestore;
    }

    public String getPathFiles() {
        return pathFiles;
    }

    public Activity getActivity() {
        return activity;
    }
    public byte[] readMap(String fileName){
        if(!new File(pathFiles+"/cache/data/maps/"+fileName).exists() || fileName.isBlank()){
            return null;
        }
        try {
            InputStream read = new FileInputStream(pathFiles+"/cache/data/maps/"+fileName);
            return read.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getTarkovMaps(boolean checkIfFilesExists){
        MapTarkov[] maps = MapTarkov.values();
        ArrayList<String> mapFileNames = new ArrayList<>();
        for(MapTarkov m : maps){
            if(!m.getFileName().isBlank()){
                mapFileNames.add(m.getFileName());
            }
        }
        StorageReference storageRef = storage.getReference("/maps");
        for(String s : mapFileNames){
            if(new File(pathFiles+"/cache/data/maps/"+s).exists() && checkIfFilesExists){
                continue;
            }
            StorageReference pathRef = storageRef.child(s);
            final long MAX_MB_SIZE = 2024*1024;
            pathRef.getBytes(MAX_MB_SIZE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Log.d("ImageGetStatus","Successful");
                    try {
                        Log.d("ImageData",Arrays.toString(bytes));
                        OutputStream writer = new FileOutputStream(pathFiles +"/cache/data/maps/"+s);
                        writer.write(bytes);
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("ImageGetStatus","Failed: "+e);
                }
            });
        }
    }

    public void signUp(String email, String password, HashMap<String, Object> data, ProgressBar progressBar){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("SignUp Status","Successful");
                    saveAditionalSignUpData(data, auth.getCurrentUser().getUid());
                } else {
                    Log.w("SignUp Status","Error");
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
    private void saveAditionalSignUpData(HashMap<String, Object> data, String userID){
        firestore.collection("user").document(userID).set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    activity.startActivity(new Intent(activity, MainActivity.class));
                    Toast.makeText(activity,"Data saved successfully",Toast.LENGTH_LONG).show();
                } else {

                }
            }
        });
    }
    public void logIn(String email, String password, Activity currentActivity, ProgressBar progressBar){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("LogIn Status", "Successful");
                    saveCurrentUser(currentActivity);
                    currentActivity.startActivity(new Intent(currentActivity, HomeActivity.class));
                } else {
                    Toast.makeText(currentActivity, "User not found",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.w("LogIn Status","Error");
                }
            }
        });
    }
    public void logOut(Activity currentActivity){
        auth.signOut();
        SharedPreferences preferences = currentActivity.getPreferences(Context.MODE_PRIVATE);
        preferences.edit().remove("CurrentUser").apply();
        currentActivity.startActivity(new Intent(currentActivity, MainActivity.class));
        Toast.makeText(currentActivity, "Logged Out",Toast.LENGTH_SHORT).show();
    }
    private void saveCurrentUser(Activity activity){
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        preferences.edit().putString("CurrentUser",auth.getCurrentUser().getUid()).apply();
    }
    public void addAmmo(Ammo ammo, String ammoType, String caliber){
        firestore.collection("ammo").document(ammoType).collection(caliber).add(ammo).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    Log.d("AddAmmo","Successful!");
                }
            }
        });
    }
    public void getAmmoDB(String ammoType, String caliber, RecyclerView rv){
        ArrayList<Ammo> ammoList = new ArrayList<>();
        firestore.collection("ammo").document(ammoType).collection(caliber).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot query = task.getResult();
                    if(!query.isEmpty()){
                        List<DocumentSnapshot> documents = query.getDocuments();
                        for(DocumentSnapshot doc : documents){
                            try {
                                Ammo ammo = (Ammo)doc.getData();
                                ammoList.add(ammo);
                                Log.d("No Excepcion",ammo.toString());
                            } catch (ClassCastException e){
                                Ammo ammo = new Ammo((HashMap<String, Object>) doc.getData());
                                ammoList.add(ammo);
                                Log.d("Excepcion",e+" "+ammo);
                            }
                        }
                        sortList(ammoList);
                        fillComponent(rv, ammoList);
                    } else {
                        Log.w("Collection Status","Not found");
                    }
                }
                Log.d("AmmoList",ammoList.toString());
            }
        });
        Log.d("AmmoListReturn", ammoList.toString());
    }
    private void fillComponent(RecyclerView rv, ArrayList<Ammo> ammoList){
        String[] ammosName = new String[ammoList.size()];
        for(int i = 0; i<ammoList.size(); i++){
            ammosName[i] = ammoList.get(i).getCaliber()+" "+ammoList.get(i).getLongName();
            Log.d("AmmoElement",ammoList.get(i).toString());
        }
        rv.setAdapter(new AmmoAdapter(ammoList, activity));
        rv.getAdapter().notifyDataSetChanged();
    }
    private void sortList(ArrayList<Ammo> ammoList){
        ammoList.sort(new Comparator<Ammo>() {
            @Override
            public int compare(Ammo o1, Ammo o2) {
                return Integer.compare(o1.getPenetrationPower(), o2.getPenetrationPower());
            }
        });
    }
}
