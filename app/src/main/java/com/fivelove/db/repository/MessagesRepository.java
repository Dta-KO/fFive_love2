package com.fivelove.db.repository;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppDatabase;
import com.fivelove.db.dao.MessageDao;
import com.fivelove.db.model.Message;
import com.fivelove.utils.Constant;

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
        Constant.EXECUTORS.diskIO().execute(() -> messageDao.insert(message));
    }

    public void deleteMessage(Message message) {
        Constant.EXECUTORS.diskIO().execute(() -> messageDao.delete(message));

    }

    public void updateMessage(Message message) {
        Constant.EXECUTORS.diskIO().execute(() -> messageDao.update(message));
    }

    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }

}
