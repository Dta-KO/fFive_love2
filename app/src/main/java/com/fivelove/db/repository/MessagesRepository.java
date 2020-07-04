package com.fivelove.db.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppDatabase;
import com.fivelove.db.dao.MessageDao;
import com.fivelove.db.model.Message;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class MessagesRepository {
    private MessageDao messageDao;
    private String idFriend;

    private LiveData<List<Message>> allMessages;

    public MessagesRepository(App application) {
        AppDatabase db = AppDatabase.getInstance(application);

        messageDao = db.messageDao();
        allMessages = messageDao.getAllMessage(idFriend);
    }

    public void setIdFriend(String idFriend) {
        this.idFriend = idFriend;
    }

    public String getIdFriend() {
        return idFriend;
    }


    public void insertMessage(Message message) {
        new MessagesRepository.InsertMessageAsyncTask(messageDao).execute(message);
    }

    public void deleteMessage(Message message) {
        new MessagesRepository.DeleteMessageAsyncTask(messageDao).execute(message);
    }

    public void updateMessage(Message message) {
        new MessagesRepository.UpdateMessageAsyncTask(messageDao).execute(message);
    }

    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
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
