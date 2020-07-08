package com.fivelove.utils;

import android.annotation.SuppressLint;

import com.fivelove.AppExecutors;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * Created by Nguyen Kim Khanh on 6/20/2020.
 */
public final class Constant {
    public static final int RC_SIGN_IN = 101;
    public final int STORY_ITEM = 0;
    public final int IMAGE_ITEM = 1;
    public static final int IMAGE = 1403;
    @SuppressLint("StaticFieldLeak")
    public static final FirebaseFirestore DB = FirebaseFirestore.getInstance();
    public static final StorageReference FIREBASE_STORAGE = FirebaseStorage.getInstance().getReference("images");
    public static final AppExecutors EXECUTORS = new AppExecutors();


}
