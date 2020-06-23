package com.fivelove.db;

import android.os.AsyncTask;

import com.fivelove.R;
import com.fivelove.db.dao.FriendDao;
import com.fivelove.db.dao.ImageDao;
import com.fivelove.db.dao.MessageDao;
import com.fivelove.db.model.Message;
import com.fivelove.db.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nguyen Kim Khanh on 6/21/2020.
 */
public class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
    private FriendDao friendDao;
    private MessageDao messageDao;
    private ImageDao imageDao;
    private List<User> userList;

    PopulateDbAsyncTask(AppDatabase db) {
        friendDao = db.userDao();
        messageDao = db.messageDao();
        imageDao = db.imageDao();
        setUserList();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for (int i = 0; i < userList.size(); i++) {
            friendDao.insert(userList.get(i));
        }

        return null;
    }

    public void setUserList() {
        userList = new ArrayList<>();
        userList.add(new User("Khánh", R.drawable.avatar));
        userList.add(new User("hưng", R.drawable.th));
        userList.add(new User("hùng", R.drawable.avatar));
        userList.add(new User("dũng", R.drawable.th));
        userList.add(new User("toàn", R.drawable.avatar));
        userList.add(new User("anh", R.drawable.avatar));
    }
}
