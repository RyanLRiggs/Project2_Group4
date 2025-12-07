package com.example.project2_group4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.project2_group4.database.entities.AppDatabase;
import com.example.project2_group4.database.entities.User;
import com.example.project2_group4.databinding.ActivityAdminBinding;

import java.util.List;

public class AdminActivity extends AppCompatActivity {

    private ActivityAdminBinding binding;
    private AppDatabase db;
    private com.example.project2_group4.UsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Admin - Users");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        db = AppDatabase.getInstance(this);

        binding.recyclerUsers.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        );

        loadUsers();
    }

    private void loadUsers() {
        new Thread(() -> {
            List<User> users = db.userDAO().getAllUsers();
            runOnUiThread(() -> {
                adapter = new com.example.project2_group4.UsersAdapter(users, userToDelete -> {
                    deleteUser(userToDelete);
                });
                binding.recyclerUsers.setAdapter(adapter);
            });
        }).start();
    }

    private void deleteUser(User user) {
        new Thread(() -> {
            db.userDAO().deleteUser(user);
            List<User> newList = db.userDAO().getAllUsers();
            runOnUiThread(() -> adapter.updateData(newList));
        }).start();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
