package com.fivelove.fragment;

import android.content.Intent;
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

import com.fivelove.R;
import com.fivelove.adapter.MessageAdapter;
import com.fivelove.databinding.FragmentMessageBinding;
import com.fivelove.db.model.User;
import com.fivelove.viewmodel.AppViewModel;


public class MessageFragment extends Fragment {

    FragmentMessageBinding messageBinding;
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
        messageBinding = FragmentMessageBinding.inflate(inflater);
        setViewModel();
        return messageBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRecyclerView();
    }

    public void setRecyclerView() {
        messageAdapter = new MessageAdapter();
        messageBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        messageBinding.recyclerView.setHasFixedSize(true);
        messageBinding.recyclerView.setFitsSystemWindows(true);
        messageBinding.recyclerView.setAdapter(messageAdapter);
    }

    public void setViewModel() {
        final AppViewModel myViewModel = new ViewModelProvider(this).get(AppViewModel.class);
        myViewModel.getAllUsers().observe(getViewLifecycleOwner(), users -> {
                    messageAdapter.setUsers(users);
                    Toast.makeText(getContext(), "onChanged!", Toast.LENGTH_SHORT).show();
                }
        );
//        Intent intent=getActivity().getIntent();
//        if(intent.getStringExtra("name")!=null)
//        myViewModel.insertUser(new User(intent.getStringExtra("name"), R.drawable.avatar));
    }
}