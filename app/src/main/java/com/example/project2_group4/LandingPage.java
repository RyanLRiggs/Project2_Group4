package com.example.project2_group4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2_group4.database.entities.AppDatabase;
import com.example.project2_group4.database.entities.User;
import com.example.project2_group4.databinding.ActivityLandingPageBinding;

public class LandingPage extends AppCompatActivity {




    private static final String PREFS_NAME = "POKEDEX_PREFERENCE";
    private static final String KEY_USER_ID = "USERID";

    private ActivityLandingPageBinding binding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        db = AppDatabase.getInstance(this);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int userId = prefs.getInt(KEY_USER_ID, -1);

        if (userId == -1) {
            Intent intent = new Intent(LandingPage.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        new Thread(() -> {
            User user = db.userDAO().getUserByID(userId);
            if (user != null) {
                runOnUiThread(() -> {
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle("Welcome, " + user.getUsername());
                    }
                });
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}
