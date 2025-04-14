package com.example.medbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class RegistrationActivity extends AppCompatActivity {

    private static final String LOG_TAG = RegistrationActivity.class.getName();
    private static final String PREF_KEY = LoginActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;

    EditText userNameEt;
    EditText emailEt;
    EditText passwordEt;
    EditText passwordConfirmEt;
    EditText phoneEt;

    private SharedPreferences preferences;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if(secret_key != SECRET_KEY) {
            finish();
        }

        userNameEt = findViewById(R.id.userNameEtR);
        emailEt = findViewById(R.id.emailEtR);
        passwordEt = findViewById(R.id.passwordEtR);
        passwordConfirmEt = findViewById(R.id.passwordConfirmEtR);
        phoneEt = findViewById(R.id.phoneEtR);


        preferences = getSharedPreferences(PREF_KEY, MODE_PRIVATE);
        String userName = preferences.getString("userEmail", "");
        String password = preferences.getString("password", "");

        emailEt.setText(userName);
        passwordEt.setText(password);

        mAuth = FirebaseAuth.getInstance();
    }

    public void cancel(View view) {
        anim(this, view);
        finish();
    }

    public void registration(View view) {
        anim(this, view);

        String username = userNameEt.getText().toString();
        String email = emailEt.getText().toString();
        String password = passwordEt.getText().toString();
        String passwordConfirm = passwordConfirmEt.getText().toString();
        String phone = phoneEt.getText().toString();


        if(!password.equals(passwordConfirm)){
            Log.e(LOG_TAG,"Nem egyenlő a jelszó és a megerősítése");
            Toast.makeText(RegistrationActivity.this,
                    "Nem egyenlő a jelszó és a megerősítése", Toast.LENGTH_SHORT).show();
            return;
        }

        if(phone.isEmpty()){
            Log.e(LOG_TAG,"A telefonszám megadása kötelező!");
            Toast.makeText(RegistrationActivity.this, "A telefonszám megadása kötelező!", Toast.LENGTH_SHORT).show();
            return;
        }

        //A regisztrációs funkcionalitás
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if(task.isSuccessful()){
                        Log.i(LOG_TAG, "User created successfully");
                        startBooking();
                    } else {
                        Log.d(LOG_TAG, "User was not created");
                        Toast.makeText(RegistrationActivity.this,
                                task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        Log.i(LOG_TAG, "Regisztrált: " + username + ", email: " + email);

    }

    private void startBooking(/*user data*/){
        Intent intent = new Intent(this,BookingActivity.class);
        intent.putExtra("SECRET_KEY", SECRET_KEY);
        startActivity(intent);
    }

    private void anim(Context context, View view){
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.button_anim);
        view.startAnimation(anim);
    }
}