package com.fivelove.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fivelove.R;
import com.fivelove.databinding.UserItemBinding;
import com.fivelove.db.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/8/2020.
 */
public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.UserViewHolder> {
    private List<User> users = new ArrayList<>();

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public MessageAdapter() {
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.user_item, parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.binding.setUser(users.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {

        return users.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        UserItemBinding binding;

        public UserViewHolder(@NonNull UserItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}

