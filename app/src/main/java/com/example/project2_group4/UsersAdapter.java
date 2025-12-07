package com.example.project2_group4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project2_group4.database.entities.User;
import com.example.project2_group4.databinding.ItemUserBinding;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    public interface OnDeleteClickListener {
        void onDelete(User user);
    }

    private List<User> users;
    private final OnDeleteClickListener onDeleteClickListener;

    public UsersAdapter(List<User> users, OnDeleteClickListener listener) {
        this.users = users;
        this.onDeleteClickListener = listener;
    }

    public void updateData(List<User> newUsers) {
        this.users = newUsers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemUserBinding binding = ItemUserBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.binding.txtUsername.setText(user.getUsername());
        holder.binding.txtRole.setText(user.isAdmin() ? "Admin" : "User");

        holder.binding.btnDelete.setOnClickListener(v -> onDeleteClickListener.onDelete(user));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        ItemUserBinding binding;
        public UserViewHolder(ItemUserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

