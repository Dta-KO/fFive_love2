package com.fivelove.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fivelove.databinding.BottomSheetStatusFragmentBinding;
import com.fivelove.utils.ImageUtils;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Objects;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBtnAddImage();
    }

    public void setBtnAddImage() {
        Button btnAddImage = binding.txtAddImage;
        btnAddImage.setOnClickListener(view -> {
            ImageUtils.chooseImage(Objects.requireNonNull(getActivity()));
        });
    }


    public interface Callback {
        void onGetImageSuccess(Uri imgUri);
    }
}
