package com.fivelove.db.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppDatabase;
import com.fivelove.db.dao.FriendDao;
import com.fivelove.db.model.User;
import com.fivelove.utils.Constant;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class FriendsRepository {
    private FriendDao friendDao;
    private LiveData<List<User>> allFriends;
    private static FriendsRepository instance;

    private FriendsRepository(App app) {
        AppDatabase db = AppDatabase.getInstance(app);
        friendDao = db.userDao();
        allFriends = friendDao.getAllFriends();
    }

    public static FriendsRepository getInstance() {
        if (instance==null){
            instance = new FriendsRepository((App) App.getApp());
        }
        return instance;
    }


    public void insertUser(User friend) {
        Constant.EXECUTORS.diskIO().execute(() -> friendDao.insert(friend));
    }

    public void deleteUser(User friend) {
        Constant.EXECUTORS.diskIO().execute(() -> friendDao.delete(friend));
    }

    public void updateUser(User friend) {
        Constant.EXECUTORS.diskIO().execute(() -> friendDao.update(friend));
    }

    public LiveData<List<User>> getAllFriends() {
        return allFriends;
    }


    public void deleteAllUser() {
        Constant.EXECUTORS.diskIO().execute(() -> friendDao.deleteAllUser());
    }
}
