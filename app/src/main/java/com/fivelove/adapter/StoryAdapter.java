package com.fivelove.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.fivelove.R;
import com.fivelove.databinding.StoryItemBinding;
import com.fivelove.db.model.Image;
import com.fivelove.db.model.User;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/21/2020.
 */
public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {
    private List<User> users = new ArrayList<>();
    private List<Image> images = new ArrayList<>();
    private ImageAdapter imageAdapter;
    private StoryItemBinding binding;
    private Context context;

    public void setUsers(List<User> users, Context context) {
        this.users = users;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setImages(List<Image> images) {
        this.images = images;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.story_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setUser(users.get(position));
        if (images.size() != 0) {
            CardStackLayoutManager layoutManager = new CardStackLayoutManager(context);
            layoutManager.setStackFrom(StackFrom.Right);
            layoutManager.setVisibleCount(3);
            layoutManager.setTranslationInterval(16.0f);
            layoutManager.setScaleInterval(0.95f);
            layoutManager.setMaxDegree(0.0f);
            layoutManager.setDirections(Direction.HORIZONTAL);
            binding.cardStackView.setLayoutManager(layoutManager);
            binding.cardStackView.rewind();
            imageAdapter = new ImageAdapter();
            imageAdapter.setImages(images);
            binding.cardStackView.setAdapter(imageAdapter);
        }
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
