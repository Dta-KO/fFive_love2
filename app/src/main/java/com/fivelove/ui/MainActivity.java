package com.fivelove.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.fivelove.R;
import com.fivelove.adapter.MPagerAdapter;
import com.fivelove.databinding.ActivityMainBinding;
import com.fivelove.viewmodel.UserViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Kim Khanh on 6/8/2020.
 */
public class MainActivity extends FragmentActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private ViewPager2 viewPager2;
    private CircleImageView circleImageView;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        circleImageView = binding.avtProfile;
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
        MPagerAdapter pagerAdapter = new MPagerAdapter(this);
        viewPager2.setAdapter(pagerAdapter);

    }

    public void setTabLayout() {
        TabLayout tabLayout = binding.tab;
        new TabLayoutMediator(tabLayout, viewPager2, true, (tab, position) -> {
            if (position == 0) {
                tab.setIcon(R.drawable.ic_amp_stories_24);
            } else {
                tab.setIcon(R.drawable.ic_mes);
            }
        }).attach();
    }


    public void setToolbar() {
        Toolbar toolbar = binding.materialToolbar;
        toolbar.inflateMenu(R.menu.menu_header);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.color_red, getTheme()));
        } else {
            toolbar.setTitleTextColor(getResources().getColor(R.color.color_red));
        }
        setActionBar(toolbar);
    }

    public void setAvtProfile() {
        final UserViewModel myViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        myViewModel.getCurrentUser().observe(this, users -> {
            binding.setUser(users);
        });
        circleImageView.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        });
    }

}


