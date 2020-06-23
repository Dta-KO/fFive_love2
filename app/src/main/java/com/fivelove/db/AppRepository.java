package com.fivelove.db;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.dao.FriendDao;
import com.fivelove.db.dao.ImageDao;
import com.fivelove.db.dao.MessageDao;
import com.fivelove.db.model.Image;
import com.fivelove.db.model.Message;
import com.fivelove.db.model.User;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
public class AppRepository {
    private FriendDao friendDao;
    private MessageDao messageDao;
    private ImageDao imageDao;
    private LiveData<List<Message>> allMessages;
    private LiveData<List<Image>> allImages;
    private LiveData<List<User>> allFriends;
    private long idFriend;

    public AppRepository(App application) {
        AppDatabase db = AppDatabase.getInstance(application);
        friendDao = db.userDao();
        allFriends = friendDao.getAllFriends();

        messageDao = db.messageDao();
        allMessages = messageDao.getAllMessage(idFriend);

        imageDao = db.imageDao();
        allImages = imageDao.getAllImage(idFriend);
    }

    public void setIdFriend(long idFriend) {
        this.idFriend = idFriend;
    }

    public long getIdFriend() {
        return idFriend;
    }

    public void insertUser(User friend) {
        new InsertUserAsyncTask(friendDao).execute(friend);
    }

    public void deleteUser(User friend) {
        new DeleteUserAsyncTask(friendDao).execute(friend);
    }

    public void updateUser(User friend) {
        new UpdateUserAsyncTask(friendDao).execute(friend);
    }

    public LiveData<List<User>> getAllFriends() {
        return allFriends;
    }

    public void insertImage(Image image) {
        new InsertImageAsyncTask(imageDao).execute(image);
    }

    public void deleteImage(Image image) {
        new DeleteImageAsyncTask(imageDao).execute(image);
    }

    public void updateImage(Image image) {
        new UpdateImageAsyncTask(imageDao).execute(image);
    }

    public LiveData<List<Image>> getAllImages() {
        return allImages;
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

    private static class UpdateImageAsyncTask extends AsyncTask<Image, Void, Void> {
        private ImageDao imageDao;

        private UpdateImageAsyncTask(ImageDao imageDao) {
            this.imageDao = imageDao;
        }


        @Override
        protected Void doInBackground(Image... images) {
            imageDao.update(images[0]);
            return null;
        }
    }

    private static class InsertImageAsyncTask extends AsyncTask<Image, Void, Void> {
        private ImageDao imageDao;

        private InsertImageAsyncTask(ImageDao imageDao) {
            this.imageDao = imageDao;
        }


        @Override
        protected Void doInBackground(Image... images) {
            imageDao.insert(images[0]);
            return null;
        }
    }

    private static class DeleteImageAsyncTask extends AsyncTask<Image, Void, Void> {
        private ImageDao imageDao;

        private DeleteImageAsyncTask(ImageDao imageDao) {
            this.imageDao = imageDao;
        }

        public ImageDao getImageDao() {
            return imageDao;
        }

        public void setImageDao(ImageDao imageDao) {
            this.imageDao = imageDao;
        }

        @Override
        protected Void doInBackground(Image... images) {
            imageDao.delete(images[0]);
            return null;
        }
    }

}
