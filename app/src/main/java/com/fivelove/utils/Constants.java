package com.fivelove.utils;

import android.annotation.SuppressLint;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Nguyen Kim Khanh on 6/20/2020.
 */
public final class Constants {
    public static final int RC_SIGN_IN = 101;
    public final int STORY_ITEM = 0;
    public final int IMAGE_ITEM = 1;
    public static final int IMAGE = 1403;
    public static final String KEY_IMAGE_URI = "image_uri";
    @SuppressLint("StaticFieldLeak")
    public static final FirebaseFirestore DB = FirebaseFirestore.getInstance();
    public static final StorageReference FIREBASE_STORAGE = FirebaseStorage.getInstance().getReference("images");


}
