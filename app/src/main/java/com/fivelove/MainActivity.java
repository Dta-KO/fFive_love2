package com.fivelove;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fivelove.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private int currentCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        currentCounter = 0;
        binding.btnIncrease.setOnClickListener(v -> {
            currentCounter++;
            binding.txtView.setText("" + currentCounter);
        });
        binding.btnDecrease.setOnClickListener(v -> {
            currentCounter--;
            binding.txtView.setText("" + currentCounter);
        });
        setContentView(binding.getRoot());
    }


}