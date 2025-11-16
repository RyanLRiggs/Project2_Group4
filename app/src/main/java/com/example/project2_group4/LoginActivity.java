package com.example.project2_group4;

import static com.example.project2_group4.database.PokeDatabase.prepopulateCallback;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.project2_group4.database.PokeDatabase;

import com.example.project2_group4.database.User;
import com.example.project2_group4.databinding.ActivityLoginBinding;

import java.security.Key;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFERENCE = "POKEDEX_PREFERENCE";

    private static final String KEY = "USERID";
    private ActivityLoginBinding binding;
    private PokeDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.LoginLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.UserNameTextBox.getText().toString();
                String password = binding.PasswordTextBox.getText().toString();

                new Thread(() -> {
                    User user = db.userDAO().validateCredentials(username, password);

                    if (!username.isEmpty() && !password.isEmpty()) {

                        SharedPreferences preferences = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt(KEY, user.getUserID());
                        editor.apply();
                        finish();

                    }
                }).start();


            }
        });




    }

    public static Intent mainToLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }
}