package com.fivelove.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fivelove.R;
import com.fivelove.databinding.StoryItemBinding;
import com.fivelove.db.model.Image;
import com.fivelove.db.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/21/2020.
 */
public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private List<User> users = new ArrayList<>();
    private List<Image> imagesStory = new ArrayList<>();
    private List<Image> images = new ArrayList<>();

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setImages(int position) {
        String idUser = users.get(position).getId();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.story_item, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setUser(users.get(position));

        holder.binding.cardStackView.setAdapter(new ImageAdapter());
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {
        StoryItemBinding binding;

        public ViewHolder(@NonNull StoryItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
