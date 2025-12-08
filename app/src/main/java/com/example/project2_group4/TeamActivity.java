package com.example.project2_group4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2_group4.database.entities.AppDatabase;
import com.example.project2_group4.databinding.ActivityTeamBinding;

import java.util.List;

public class TeamActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "POKEDEX_PREFERENCE";
    private static final String KEY_USER_ID = "USERID";

    private ActivityTeamBinding binding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTeamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Your Team");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = AppDatabase.getInstance(this);

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int userId = prefs.getInt(KEY_USER_ID, -1);

        if (userId == -1) {
            finish();
            return;
        }

        binding.btnBackHome.setOnClickListener(v -> {
            finish();
        });

        new Thread(() -> {
            List<String> teamNames = db.teamDAO().getTeamNamesForUser(userId);

            runOnUiThread(() -> {
                if (teamNames == null || teamNames.isEmpty()) {
                    binding.txtTeamList.setText("You don't have any Pokémon in your team yet.");
                } else {
                    StringBuilder sb = new StringBuilder();
                    for (String name : teamNames) {
                        sb.append("• ").append(name).append("\n");
                    }
                    binding.txtTeamList.setText(sb.toString());
                }
            });
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        binding = null;
        super.onDestroy();
    }
}
