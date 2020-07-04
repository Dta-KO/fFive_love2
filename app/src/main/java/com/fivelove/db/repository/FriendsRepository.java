package com.fivelove.db.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppDatabase;
import com.fivelove.db.dao.FriendDao;
import com.fivelove.db.model.User;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class FriendsRepository {
    private FriendDao friendDao;
    private LiveData<List<User>> allFriends;

    public FriendsRepository(App app) {
        AppDatabase db = AppDatabase.getInstance(app);
        friendDao = db.userDao();
        allFriends = friendDao.getAllFriends();
    }
    public void insertUser(User friend) {
        new FriendsRepository.InsertUserAsyncTask(friendDao).execute(friend);
    }

    public void deleteUser(User friend) {
        new FriendsRepository.DeleteUserAsyncTask(friendDao).execute(friend);
    }

    public void updateUser(User friend) {
        new FriendsRepository.UpdateUserAsyncTask(friendDao).execute(friend);
    }

    public LiveData<List<User>> getAllFriends() {
        return allFriends;
    }
    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private FriendDao friendDao;

        private InsertUserAsyncTask(FriendDao friendDao) {
            this.friendDao = friendDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            friendDao.insert(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private FriendDao friendDao;

        private DeleteUserAsyncTask(FriendDao friendDao) {
            this.friendDao = friendDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            friendDao.delete(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private FriendDao friendDao;

        private UpdateUserAsyncTask(FriendDao friendDao) {
            this.friendDao = friendDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            friendDao.update(users[0]);
            return null;
        }
    }
}
