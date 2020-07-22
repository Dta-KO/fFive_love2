package com.fivelove.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fivelove.databinding.BottomSheetStatusFragmentBinding;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

/**
 * Created by Nguyen Kim Khanh on 7/22/2020.
 */
public class BottomSheetStatusFragment extends BottomSheetDialogFragment {
    BottomSheetStatusFragmentBinding binding;
    public BottomSheetStatusFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = BottomSheetStatusFragmentBinding.inflate(getLayoutInflater());
        return binding.getRoot();

    }
}
