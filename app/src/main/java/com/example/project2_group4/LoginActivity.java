package com.example.project2_group4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.example.project2_group4.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

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

                if (!username.isEmpty() && !password.isEmpty()) {
                    if (username.equals("testuser1") && password.equals("pass")) {
                        Intent intent = LandingActivity.LoginToLanding(getApplicationContext());
                        startActivity(intent);
                    }
                }
            }
        });




    }

    public static Intent mainToLogin(Context context){
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }
}