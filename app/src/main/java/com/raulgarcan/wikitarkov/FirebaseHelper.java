package com.raulgarcan.wikitarkov;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.common.util.SharedPreferencesUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseHelper {
    private final FirebaseAuth auth;
    private final FirebaseFirestore firestore;

    public FirebaseHelper() {
        this.auth = FirebaseAuth.getInstance();
        this.firestore = FirebaseFirestore.getInstance();
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getFirestore() {
        return firestore;
    }
    public void signUp(String email, String password){
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("SignUp Status","Successful");
                } else {
                    Log.w("SignUp Status","Error");
                }
            }
        });
    }
    public void logIn(String email, String password, Activity currentActivity){
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.d("LogIn Status", "Successful");
                    saveCurrentUser(currentActivity);
                } else {
                    Log.w("LogIn Status","Error");
                }
            }
        });
    }
    public void signOut(Activity currentActivity){
        auth.signOut();
        SharedPreferences preferences = currentActivity.getPreferences(Context.MODE_PRIVATE);
        preferences.edit().remove("CurrentUser").apply();
    }
    private void saveCurrentUser(Activity activity){
        SharedPreferences preferences = activity.getPreferences(Context.MODE_PRIVATE);
        preferences.edit().putString("CurrentUser",auth.getCurrentUser().getUid()).apply();
    }
}
