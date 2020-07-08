package com.fivelove.db.repository;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppDatabase;
import com.fivelove.db.dao.FriendDao;
import com.fivelove.db.model.User;
import com.fivelove.utils.Constant;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class UserRepository {
    private LiveData<User> user;
    private FriendDao friendDao;
    private static UserRepository instance;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository((App) App.getApp());
        }
        return instance;
    }

    public LiveData<User> getUser() {
        return user;
    }


    private UserRepository(App app) {
        AppDatabase db = AppDatabase.getInstance(app);
        friendDao = db.userDao();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            user = friendDao.getUser(FirebaseAuth.getInstance().getCurrentUser().getUid());
        }
    }

    public void updateUser(User user) {
        Constant.EXECUTORS.diskIO().execute(() -> friendDao.update(user)
        );
    }
}
