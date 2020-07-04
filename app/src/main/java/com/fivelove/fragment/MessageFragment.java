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

import com.fivelove.adapter.MessageAdapter;
import com.fivelove.databinding.FragmentUsersBinding;
import com.fivelove.viewmodel.FriendsViewModel;

import java.util.Objects;


public class MessageFragment extends Fragment {

    FragmentUsersBinding userBinding;
    MessageAdapter messageAdapter;


    public MessageFragment() {
    }


    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


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
        messageAdapter = new MessageAdapter();
        userBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        userBinding.recyclerView.setHasFixedSize(true);
        userBinding.recyclerView.setFitsSystemWindows(true);
        userBinding.recyclerView.setAdapter(messageAdapter);
    }

    public void setViewModel() {
        final FriendsViewModel myViewModel = new ViewModelProvider(this).get(FriendsViewModel.class);
        myViewModel.getAllFriends().observe(getViewLifecycleOwner(), users -> {
                    messageAdapter.setUsers(users);
                    Toast.makeText(getContext(), "onChanged!", Toast.LENGTH_SHORT).show();
                }
        );
    }
}