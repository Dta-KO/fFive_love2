package com.fivelove.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fivelove.databinding.FragmentStatusBinding;

public class StatusFragment extends Fragment {
    FragmentStatusBinding binding;
    public static Callback callback;

    public StatusFragment() {
        // Required empty public constructor
    }

    public static StatusFragment newInstance() {

        return new StatusFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStatusBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        callback.onDestroyStatusFragment();
    }

    public interface Callback {
        void onDestroyStatusFragment();
    }
}