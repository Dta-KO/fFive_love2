package com.fivelove.db.repository;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppDatabase;
import com.fivelove.db.dao.FriendDao;
import com.fivelove.db.model.User;
import com.fivelove.utils.Constant;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class UserRepository {
    private LiveData<User> user;
    private FriendDao friendDao;

    public LiveData<User> getCurrentUser() {
        return user;
    }

    public UserRepository(App app) {
        AppDatabase db = AppDatabase.getInstance(app);
        friendDao = db.userDao();
        if (Constant.CURRENT_USER != null) {
            user = friendDao.getCurrentUser(Constant.CURRENT_USER.getUid());
        }
    }
}
