package com.fivelove.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by Nguyen Kim Khanh on 6/20/2020.
 */
public class PermissionUtil {
    private static final int REQUEST_WRITE_STORAGE = 1;
    public Context context;

    public PermissionUtil(Context context) {
        this.context = context;
    }

    public boolean hasStoragePermission() {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
    }
    public void checkPermission() {
        boolean hasPermission = hasStoragePermission();
        if (hasPermission) {
            //do something
        } else {
            ActivityCompat.requestPermissions((Activity) context,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_WRITE_STORAGE);
        }
    }
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == REQUEST_WRITE_STORAGE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            //do something
//        }
//    }
}
