package com.fivelove.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.fivelove.adapter.FriendsAdapter;
import com.fivelove.databinding.FragmentUsersBinding;
import com.fivelove.viewmodel.UsersViewModel;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


public class FriendsFragment extends Fragment {

    FragmentUsersBinding userBinding;
    FriendsAdapter friendsAdapter;


    public FriendsFragment() {
    }


    public static FriendsFragment newInstance() {
        return new FriendsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    CircleImageView imageView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        userBinding = FragmentUsersBinding.inflate(inflater);
        Objects.requireNonNull(Objects.requireNonNull(getActivity()).getActionBar()).setTitle("Chat");
        return userBinding.getRoot();

    }

    @Override
    public void onResume() {
        super.onResume();
        Objects.requireNonNull(Objects.requireNonNull(getActivity()).getActionBar()).setTitle("Chat");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
        setViewModel();

    }

    public void setRecyclerView() {
        friendsAdapter = new FriendsAdapter();
        userBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userBinding.recyclerView.setHasFixedSize(true);
        userBinding.recyclerView.setFitsSystemWindows(true);
        userBinding.recyclerView.setAdapter(friendsAdapter);
    }

    public void setViewModel() {
        final UsersViewModel myViewModel = new ViewModelProvider(this).get(UsersViewModel.class);
        myViewModel.getAllFriends().observe(getViewLifecycleOwner(), users -> {
                    friendsAdapter.setUsers(users);
                    Toast.makeText(getContext(), "onChanged!", Toast.LENGTH_SHORT).show();
                }
        );
    }
}