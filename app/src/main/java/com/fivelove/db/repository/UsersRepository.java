package com.fivelove.db.repository;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppDatabase;
import com.fivelove.db.dao.FriendDao;
import com.fivelove.db.model.User;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class UsersRepository {
    private FriendDao friendDao;
    private LiveData<List<User>> allFriends;
    private static UsersRepository instance;

    private UsersRepository(App app) {
        AppDatabase db = AppDatabase.getInstance(app);
        friendDao = db.userDao();
        allFriends = friendDao.getAllFriends();
    }

    public static UsersRepository getInstance() {
        if (instance==null){
            instance = new UsersRepository((App) App.getApp());
        }
        return instance;
    }


    public void insertUser(User friend) {
        friendDao.insert(friend);
    }

    public void deleteUser(User friend) {
        friendDao.delete(friend);
    }

    public void updateUser(User friend) {
       friendDao.update(friend);
    }

    public LiveData<List<User>> getAllFriends() {
        return allFriends;
    }


    public void deleteAllUser() {
        friendDao.deleteAllUser();
    }
}
