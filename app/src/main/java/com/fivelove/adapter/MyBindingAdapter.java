package com.fivelove.adapter;

import android.util.Log;

import androidx.databinding.BindingAdapter;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Kim Khanh on 6/18/2020.
 */
public class MyBindingAdapter {
    @BindingAdapter("android:src")
    public static void setImage(CircleImageView view, int resource) {
        Log.d("abc", "" + view.getHeight());
        Log.d("width", "" + view.getWidth());
        view.setImageResource(resource);
    }
}
