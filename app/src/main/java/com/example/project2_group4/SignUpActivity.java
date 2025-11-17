package com.example.project2_group4;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.project2_group4.database.entities.AppDatabase;
import com.example.project2_group4.database.entities.User;
import com.example.project2_group4.databinding.ActivitySignupBinding;

public class SignUpActivity extends AppCompatActivity{

    private ActivitySignupBinding binding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getInstance(this);

        binding.signUpButton.setOnClickListener(v -> {
            String username = binding.usernameEditText.getText().toString().trim();
            String password = binding.passwordEditText.getText().toString().trim();
            String retypePassword = binding.retypePasswordEditText.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty() || retypePassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(retypePassword)) {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                int exists = db.userDAO().checkUsernameExists(username);

                if (exists > 0) {
                    runOnUiThread(() ->
                            Toast.makeText(this, "Username already exists", Toast.LENGTH_SHORT).show()
                    );
                } else {
                    User newUser = new User(username, password, false);
                    db.userDAO().insertUser(newUser);

                    runOnUiThread(() -> {
                        Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();
                        finish(); // Go back to MainActivity
                    });
                }
            }).start();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

}
