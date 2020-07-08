package com.fivelove.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.fivelove.db.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

import static com.fivelove.utils.Constant.DB;


/**
 * Created by Nguyen Kim Khanh on 6/26/2020.
 */
public class FirebaseCloud {
    private final static String TAG = "FirebaseCloud";


    public static void addUser(User user) {
        DocumentReference reference = DB.collection("users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
        reference.set(user).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "on insert success");
            }
        });

    }

}
