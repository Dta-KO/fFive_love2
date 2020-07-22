package com.fivelove.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.fivelove.R;
import com.fivelove.adapter.MPagerAdapter;
import com.fivelove.databinding.ActivityMainBinding;
import com.fivelove.fragment.StatusFragment;
import com.fivelove.fragment.StoryFragment;
import com.fivelove.viewmodel.UserViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Kim Khanh on 6/8/2020.
 */
public class MainActivity extends FragmentActivity implements StoryFragment.Callback, StatusFragment.Callback {

    private ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private CircleImageView circleImageView;
    private TabLayout tabLayout;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        circleImageView = binding.toolbar.avtProfile;
        setContentView(binding.getRoot());
        setViewPager2();
        setTabLayout();
        setToolbar();
        setAvtProfile();
        StoryFragment.callback = this;
        StatusFragment.callback = this;
    }


    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager2.setCurrentItem(binding.pager.getCurrentItem() - 1);
        }
    }

    public void setViewPager2() {
        viewPager2 = binding.pager;
        MPagerAdapter pagerAdapter = new MPagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

    }

    public void setTabLayout() {
        tabLayout = binding.tab;
        new TabLayoutMediator(tabLayout, viewPager2, true, (tab, position) -> {
            if (position == 0) {
                tab.setIcon(R.drawable.ic_amp_stories_24).setText("Story");
            } else {
                tab.setIcon(R.drawable.ic_mes).setText("Chat");
            }
        }).attach();
    }


    public void setToolbar() {
        Toolbar toolbar = binding.toolbar.textView4;
        toolbar.setTitleTextColor(Color.RED);
        setActionBar(toolbar);
    }

    public void setAvtProfile() {
        final UserViewModel myViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        myViewModel.getCurrentUser().observe(this, users -> binding.setUser(users));
        circleImageView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onBtnStatusClick() {
        StatusFragment statusFragment = StatusFragment.newInstance();
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction()
                .replace(R.id.container_pager, statusFragment)
                .addToBackStack("tag")
                .commit();
        Objects.requireNonNull(getActionBar()).setTitle("Tạo bài viết");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setDisplayShowHomeEnabled(true);
        enableTab(false);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(false);
            getActionBar().setDisplayShowHomeEnabled(false);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyStatusFragment() {
        Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(false);
        getActionBar().setDisplayShowHomeEnabled(false);
        getActionBar().setTitle("Five love");
        enableTab(true);
    }

    public void enableTab(Boolean isEnable) {
        LinearLayout tabStrip = (LinearLayout) tabLayout.getChildAt(0);
        tabStrip.setEnabled(isEnable);
        for (int i = 0; i < tabStrip.getChildCount(); i++) {
            tabStrip.getChildAt(i).setClickable(isEnable);
        }
    }
}


