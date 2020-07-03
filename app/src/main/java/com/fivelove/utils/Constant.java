package com.fivelove.utils;

import android.annotation.SuppressLint;

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
    public static final String USER_NAME = "name";
    public static final String USER_ID = "id";
    public static final String USER_AVT = "avt";
    public final int STORY_ITEM = 0;
    public final int IMAGE_ITEM = 1;
    public static final int IMAGE = 1403;
    @SuppressLint("StaticFieldLeak")
    public static final FirebaseFirestore DB = FirebaseFirestore.getInstance();
    public static final StorageReference FIREBASE_STORAGE = FirebaseStorage.getInstance().getReference("images");
    public static final FirebaseAuth AUTH = FirebaseAuth.getInstance();
    public static final FirebaseUser CURRENT_USER = AUTH.getCurrentUser();

}
