package com.fivelove.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.fivelove.R;
import com.fivelove.databinding.ImageItemBinding;
import com.fivelove.db.model.Image;
import com.yuyakaido.android.cardstackview.CardStackView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/21/2020.
 */
public class ImageAdapter extends CardStackView.Adapter<ImageAdapter.ViewHolder> {
    private List<Image> images = new ArrayList<>();

    public void setImages(List<Image> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ImageItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.image_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder holder, int position) {
        holder.binding.setImage(images.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return Math.min(images.size(), 3);
    }

    protected static class ViewHolder extends CardStackView.ViewHolder {
        ImageItemBinding binding;

        public ViewHolder(@NonNull ImageItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
