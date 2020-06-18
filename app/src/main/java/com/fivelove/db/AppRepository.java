package com.fivelove.db;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.dao.MessageDao;
import com.fivelove.db.dao.UserDao;
import com.fivelove.db.model.Message;
import com.fivelove.db.model.User;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
public class AppRepository {
    private UserDao userDao;
    private MessageDao messageDao;
    private LiveData<List<Message>> allMessages;
    private LiveData<List<User>> allUsers;
    private long idUser;

    public AppRepository(App application) {
        AppDatabase db = AppDatabase.getInstance(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUser();

        messageDao = db.messageDao();
        allMessages = messageDao.getAllMessage(idUser);
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdUser() {
        return idUser;
    }

    public void insertUser(User user) {
        new InsertUserAsyncTask(userDao).execute(user);
    }

    public void deleteUser(User user) {
        new DeleteUserAsyncTask(userDao).execute(user);
    }

    public void updateUser(User user) {
        new UpdateUserAsyncTask(userDao).execute(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insertMessage(Message message) {
        new InsertMessageAsyncTask(messageDao).execute(message);
    }

    public void deleteMessage(Message message) {
        new DeleteMessageAsyncTask(messageDao).execute(message);
    }

    public void updateMessage(Message message) {
        new UpdateMessageAsyncTask(messageDao).execute(message);
    }

    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }

    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            return null;
        }
    }

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private DeleteUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.delete(users[0]);
            return null;
        }
    }

    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao userDao;

        private UpdateUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }
    }

    private static class UpdateMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        private MessageDao messageDao;

        private UpdateMessageAsyncTask(MessageDao messageDao) {
            this.messageDao = messageDao;
        }


        @Override
        protected Void doInBackground(Message... messages) {
            messageDao.update(messages[0]);
            return null;
        }
    }

    private static class DeleteMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        private MessageDao messageDao;

        private DeleteMessageAsyncTask(MessageDao messageDao) {
            this.messageDao = messageDao;
        }


        @Override
        protected Void doInBackground(Message... messages) {
            messageDao.delete(messages[0]);
            return null;
        }
    }

    private static class InsertMessageAsyncTask extends AsyncTask<Message, Void, Void> {
        private MessageDao messageDao;

        private InsertMessageAsyncTask(MessageDao messageDao) {
            this.messageDao = messageDao;
        }


        @Override
        protected Void doInBackground(Message... messages) {
            messageDao.insert(messages[0]);
            return null;
        }
    }

}
