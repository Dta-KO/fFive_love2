package com.fivelove.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.fivelove.databinding.FragmentStoryBinding;

import java.util.Objects;


public class StoryFragment extends Fragment {
    FragmentStoryBinding binding;

    public StoryFragment() {
        // Required empty public constructor
    }

    public static StoryFragment newInstance() {

        return new StoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStoryBinding.inflate(getLayoutInflater());
        Objects.requireNonNull(Objects.requireNonNull(getActivity()).getActionBar()).setTitle("Five Love");
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(Objects.requireNonNull(getActivity()).getActionBar()).setTitle("Five Love");
    }
}