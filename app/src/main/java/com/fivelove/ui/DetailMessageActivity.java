package com.fivelove.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.fivelove.databinding.DetailMessageActivityBinding;

public class DetailMessageActivity extends BaseActivity implements View.OnClickListener {
    DetailMessageActivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DetailMessageActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setBinding();
    }

    void setBinding() {
        binding.btnSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}