package com.example.medbook;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class BookingActivity extends AppCompatActivity {
    private static final String LOG_TAG = BookingActivity.class.getName();
    // private static final String PREF_KEY = BookingActivity.class.getPackage().toString();
    private static final int SECRET_KEY = 99;

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        int secret_key = getIntent().getIntExtra("SECRET_KEY", 0);

        if(secret_key != SECRET_KEY) {
            finish();
        }

        // lekérjük a user adatait aki be van jelentkezve
        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user != null){
            Log.d(LOG_TAG, "Authenticated user");
        } else {
            Log.d(LOG_TAG, "Unauthenticated user");
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.mb_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.log_out_button) {
            Log.d(LOG_TAG, "Logout clicked!");
            FirebaseAuth.getInstance().signOut();
            finish();
            return true;

        } else if (id == R.id.settings_button) {
            Log.d(LOG_TAG, "Setting clicked!");
            Toast.makeText(BookingActivity.this,
                    "Fejlesztés alatt :) ", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}