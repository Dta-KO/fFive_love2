package com.fivelove.ui;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.fivelove.R;
import com.fivelove.adapter.MPagerAdapter;
import com.fivelove.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * Created by Nguyen Kim Khanh on 6/8/2020.
 */
public class MainActivity extends FragmentActivity {
    ActivityMainBinding binding;
    ViewPager2 viewPager2;
    public TabLayout tabLayout;
    MPagerAdapter pagerAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setViewPager2();
        setTabLayout();
        setToolbar();
    }

    @Override
    public void onBackPressed() {
        if (binding.pager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            binding.pager.setCurrentItem(binding.pager.getCurrentItem() - 1);
        }
    }

    public void setViewPager2() {
        viewPager2 = binding.pager;
        pagerAdapter = new MPagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

    }

    public void setTabLayout() {
        tabLayout = binding.tab;
        new TabLayoutMediator(tabLayout, viewPager2, true, (tab, position) -> {
            if (position == 0) {
                tab.setIcon(R.drawable.ic_amp_stories_24);
            } else {
                tab.setIcon(R.drawable.ic_mes);
            }
        }).attach();
    }

    public void setToolbar() {
        toolbar = (Toolbar) binding.materialToolbar;
        toolbar.inflateMenu(R.menu.menu_header);
        toolbar.setTitleTextColor(getResources().getColor(R.color.color_red));
        setActionBar(toolbar);
    }
}


