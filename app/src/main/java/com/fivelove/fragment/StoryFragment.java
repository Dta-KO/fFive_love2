package com.fivelove.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fivelove.adapter.StoryAdapter;
import com.fivelove.databinding.FragmentStoryBinding;
import com.fivelove.db.model.User;
import com.fivelove.viewmodel.ImagesViewModel;
import com.fivelove.viewmodel.UsersViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class StoryFragment extends Fragment {
    private FragmentStoryBinding binding;

    private StoryAdapter storyAdapter;
    private List<User> users = new ArrayList<>();
    public static Callback callback;


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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storyAdapter = new StoryAdapter();
        setRecyclerView();
        setUsersViewModel();
        setImagesViewModel();
        setBtnStatus();
    }

    public void setRecyclerView() {
        binding.storyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.storyRecyclerView.setHasFixedSize(true);
        binding.storyRecyclerView.setFitsSystemWindows(true);
        binding.storyRecyclerView.setAdapter(storyAdapter);

    }

    public void setUsersViewModel() {
        final UsersViewModel model = new ViewModelProvider(this).get(UsersViewModel.class);
        model.getAllFriends().observe(getViewLifecycleOwner(), users -> {
            storyAdapter.setUsers(users, getContext());
            this.users.addAll(users);
        });

    }

    public void setImagesViewModel() {
        for (User user : users) {
            final ImagesViewModel imagesViewModel = new ViewModelProvider(this).get(ImagesViewModel.class);
            imagesViewModel.setIdUser(user.getId());
            imagesViewModel.getAllImages().observe(getViewLifecycleOwner(), images -> {
                storyAdapter.setImages(images);
            });
        }
    }

    public void setBtnStatus() {
        Button btnStatus = binding.headerStory.btnStatus;
        btnStatus.setOnClickListener(view -> {
            callback.onBtnStatusClick();
        });
    }

    public interface Callback {
        void onBtnStatusClick();
    }

}