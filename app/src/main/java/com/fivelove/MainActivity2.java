package com.fivelove;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fivelove.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {
    public ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.setOnClickButton(new OnClickButton(binding));
    }


}