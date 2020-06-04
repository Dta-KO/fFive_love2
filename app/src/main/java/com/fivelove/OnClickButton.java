package com.fivelove;

import android.graphics.Color;
import android.view.View;

import com.fivelove.databinding.ActivityMain2Binding;

/**
 * Created by Nguyen Kim Khanh on 6/4/2020.
 */
public class OnClickButton {
    com.fivelove.databinding.ActivityMain2Binding binding;

    public OnClickButton(ActivityMain2Binding binding) {
        this.binding = binding;
    }

    public void onClickBtnBlue(View v) {
        binding.container.setBackgroundColor(Color.BLUE);
    }

    public void onClickBtnRed(View v) {
        binding.container.setBackgroundColor(Color.RED);
    }

    public void onClickBtnYellow(View v) {
        binding.container.setBackgroundColor(Color.YELLOW);
    }

    public void onClickBtnGreen(View v) {
        binding.container.setBackgroundColor(Color.GREEN);
    }

    public void onClickBtnViolet(View v) {
        binding.container.setBackgroundColor(Color.parseColor("#AA00FF"));
    }

    public void onClickBtnOrange(View v) {
        binding.container.setBackgroundColor(Color.parseColor("#DD2C00"));
    }
}
