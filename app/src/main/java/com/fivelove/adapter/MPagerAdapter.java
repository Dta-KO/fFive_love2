package com.fivelove.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.fivelove.fragment.StoryFragment;
import com.fivelove.fragment.MessageFragment;

/**
 * Created by Nguyen Kim Khanh on 6/8/2020.
 */
public class MPagerAdapter extends FragmentStateAdapter {
    public MPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position == 1) {
            fragment = MessageFragment.newInstance();
        } else {
            fragment = StoryFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
