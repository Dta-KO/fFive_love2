package com.fivelove.fragment;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.fivelove.databinding.FragmentStatusBinding;
import com.fivelove.ui.MainActivity;

public class StatusFragment extends Fragment implements BottomSheetStatusFragment.Callback {
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
        MainActivity.callback = this;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentStatusBinding.inflate(getLayoutInflater());
        binding.addMore.imgAddImage.bringToFront();
        binding.addMore.imgAddFeel.bringToFront();
        binding.addMore.imgAddFriends.bringToFront();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setBtnAdd();
        getActivity().setRequestedOrientation(
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        callback.onDestroyStatusFragment();
    }
    public void setBtnAdd(){
        binding.addMore.btnAdd.setOnClickListener(view -> {
            BottomSheetStatusFragment fragment = new BottomSheetStatusFragment();
            fragment.show(getParentFragmentManager(),fragment.getTag());
        });
    }

    @Override
    public void onGetImageSuccess(Uri imgUri) {
        binding.content.image.setVisibility(View.VISIBLE);
        binding.content.image.setImageURI(imgUri);
    }

    public interface Callback {
        void onDestroyStatusFragment();
    }
}