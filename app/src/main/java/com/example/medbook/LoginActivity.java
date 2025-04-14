package com.example.medbook;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity{

    private static final String LOG_TAG = LoginActivity.class.getName();
    private static final String PREF_KEY = LoginActivity.class.getPackage().toString();
    // private static final int RC_SIGN_IN = 123;
    private static final int SECRET_KEY = 99;

    EditText emailEt;
    EditText passwordEt;

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEt = findViewById(R.id.emailEtL);
        passwordEt = findViewById(R.id.passwordEtL);

        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        anim(this, view);

        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this,
                    "Kérlek add meg az email címed és a jelszavad!", Toast.LENGTH_SHORT).show();
            return;
        }


        //Bejelentkeztetés
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()){
                        Log.i(LOG_TAG,"User loging successfully");
                        startBooking();
                    } else {
                        Log.d(LOG_TAG, "User login fail");
                        Toast.makeText(LoginActivity.this,
                                "Hibás felhasználó vagy jelszó", Toast.LENGTH_SHORT).show();
                        // Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void registration(View view) {
        anim(this, view);

        Intent intent = new Intent(this, RegistrationActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    private void startBooking(/*user data*/){
        Intent intent = new Intent(this,BookingActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userEmail", emailEt.getText().toString());
        editor.putString("password", passwordEt.getText().toString());
        editor.apply();

        Log.i(LOG_TAG, "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOG_TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOG_TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOG_TAG, "onRestart");
    }

    private void anim(Context context, View view){
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.button_anim);
        view.startAnimation(anim);
    }

}
