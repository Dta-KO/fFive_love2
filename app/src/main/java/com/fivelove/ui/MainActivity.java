package com.fivelove.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.fivelove.MyService;
import com.fivelove.R;
import com.fivelove.adapter.MPagerAdapter;
import com.fivelove.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * Created by Nguyen Kim Khanh on 6/8/2020.
 */
public class MainActivity extends FragmentActivity {
    ActivityMainBinding binding;
    ViewPager2 viewPager2;
    TabLayout tabLayout;
    MPagerAdapter pagerAdapter;
    FloatingActionButton btnAdd;
    public static final String URL_NAME = "name";
    public static final String URL ="https://i.imgur.com/BeWRgkr.jpg";
    public static final int REQUEST_WRITE_STORAGE = 1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setViewPager2();
        setTabLayout();
//        setBtnAdd();
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
            Log.d("pos", String.valueOf(position));
            if (position == 0) {
                tab.setText(R.string.message).setIcon(R.drawable.ic_mes);
            } else {
                tab.setText(R.string.contacts).setIcon(R.drawable.ic_contacts);
            }
        }).attach();
    }

//    public void setBtnAdd() {
//        btnAdd = binding.btnAdd;
//        btnAdd.setOnClickListener(v -> {
//            checkPermission();
//        });
//    }
    private boolean hasStoragePermission() {
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }

    private void startDownloadService(){
        Intent intent = new Intent(MainActivity.this, MyService.class);
        intent.putExtra(URL_NAME,URL);
        startService(intent);
    }

    private void checkPermission() {
        boolean hasPermission = hasStoragePermission();
        if (hasPermission) {
           startDownloadService();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startDownloadService();
        }
    }
}


