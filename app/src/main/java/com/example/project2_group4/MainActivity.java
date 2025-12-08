package com.example.project2_group4;
//hi
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2_group4.databinding.ActivityMainBinding;
import com.example.project2_group4.factory.IntentFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(v -> {
            startActivity(IntentFactory.createLoginActivity(this));
        });

        binding.createAccountButton.setOnClickListener(v -> {
            startActivity(IntentFactory.createSignUpActivity(this));
        });
    }
}