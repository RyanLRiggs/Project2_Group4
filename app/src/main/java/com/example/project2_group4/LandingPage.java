package com.example.project2_group4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import java.util.List;


import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.project2_group4.database.entities.AppDatabase;
import com.example.project2_group4.database.entities.User;
import com.example.project2_group4.databinding.ActivityLandingPageBinding;

import Common.Common;

public class LandingPage extends AppCompatActivity {

    private static final String PREFS_NAME = "POKEDEX_PREFERENCE";
    private static final String KEY_USER_ID = "USERID";

    private ActivityLandingPageBinding binding;
    private AppDatabase db;

    private BroadcastReceiver showDetailReceiver;
    private BroadcastReceiver evolutionReceiver;

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

        binding.btnViewTeam.setOnClickListener(v -> {
            Intent intent = new Intent(LandingPage.this, TeamActivity.class);
            startActivity(intent);
        });

        new Thread(() -> {
            User user = db.userDAO().getUserByID(userId);
            if (user != null) {
                runOnUiThread(() -> {
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle("Welcome, " + user.getUsername());
                    }
                    if (user.isAdmin()) {
                        binding.btnAdmin.setVisibility(View.VISIBLE);
                        binding.btnAdmin.setOnClickListener(v -> {
                            Intent intent = new Intent(LandingPage.this, AdminActivity.class);
                            startActivity(intent);
                        });
                    }
                });
            }
        }).start();



        setupBroadcastReceivers();
    }

    private void setupBroadcastReceivers() {
        showDetailReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int position = intent.getIntExtra("position", -1);
                if (position != -1) {
                    PokemonDetail detailFragment = PokemonDetail.Companion.getInstance();
                    Bundle args = new Bundle();
                    args.putInt("position", position);
                    detailFragment.setArguments(args);

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.list_pokemon_fragment, detailFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        };

        evolutionReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String num = intent.getStringExtra("num");
                if (num != null) {
                    PokemonDetail detailFragment = PokemonDetail.Companion.getInstance();
                    Bundle args = new Bundle();
                    args.putString("num", num);
                    detailFragment.setArguments(args);

                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.list_pokemon_fragment, detailFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        };

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(showDetailReceiver,
                new IntentFilter(Common.KEY_ENABLE_HOME));
        lbm.registerReceiver(evolutionReceiver,
                new IntentFilter(Common.KEY_NUM_EVOLUTION));
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        if (showDetailReceiver != null) {
            lbm.unregisterReceiver(showDetailReceiver);
        }
        if (evolutionReceiver != null) {
            lbm.unregisterReceiver(evolutionReceiver);
        }
        binding = null;
        super.onDestroy();
    }
}

