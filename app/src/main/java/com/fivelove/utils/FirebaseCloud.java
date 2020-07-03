package com.fivelove.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.fivelove.db.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.fivelove.utils.Constant.DB;


/**
 * Created by Nguyen Kim Khanh on 6/26/2020.
 */
public class FirebaseCloud {
    private final static String TAG = "FirebaseCloud";


    public static void addUser( FirebaseUser user) {
        Map<String, Object> userMap = new HashMap<>();
        userMap.put(Constant.USER_ID,user.getUid());
        userMap.put(Constant.USER_NAME,user.getDisplayName());
        DB.collection("users").add(userMap).addOnSuccessListener(aVoid -> {
            Log.d(TAG, "ok");
        }).addOnFailureListener(e -> {
            Log.d(TAG, "fail");
        });
    }

    public static ArrayList<User> getAllUser(FirebaseFirestore db) {
        ArrayList<User> users = new ArrayList<>();
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
//                                users.addAll(document.getData())
                            }
                        }
                    }
                });
        return users;
    }


}
