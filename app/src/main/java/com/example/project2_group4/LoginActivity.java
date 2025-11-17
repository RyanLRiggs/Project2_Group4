package com.example.project2_group4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project2_group4.database.entities.AppDatabase;
import com.example.project2_group4.database.entities.User;
import com.example.project2_group4.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private static final String PREFERENCE = "POKEDEX_PREFERENCE";
    private static final String KEY = "USERID";
    private ActivityLoginBinding binding;
    private AppDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getInstance(this);

        binding.loginButton.setOnClickListener(v -> {
            String username = binding.usernameEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();

            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this,"Please fill out the form", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                User user = db.userDAO().validateCredentials(username, password);





                runOnUiThread(() -> {
                    if(user != null) {
                        // Save user to SharedPreferences
                        SharedPreferences preferences = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt(KEY, user.getUserID());
                        editor.apply();

                        Toast.makeText(this, "Login successful! Welcome " + user.getUsername(), Toast.LENGTH_LONG).show();

                        // Go back to MainActivity (or wherever you want)
                        Intent intent = new Intent(LoginActivity.this, LandingPage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}