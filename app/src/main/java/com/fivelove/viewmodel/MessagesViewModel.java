package com.fivelove.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.model.Message;
import com.fivelove.db.repository.MessagesRepository;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
public class MessagesViewModel extends AndroidViewModel {
    private MessagesRepository repository;
    private LiveData<List<Message>> allMessages;

    public MessagesViewModel(@NonNull App application) {
        super(application);
        repository = new MessagesRepository(application);
        allMessages = repository.getAllMessages();
    }


    public void insertMessage(Message message) {
        repository.insertMessage(message);
    }

    public void updateMessage(Message message) {
        repository.updateMessage(message);
    }

    public void deleteMessage(Message message) {
        repository.deleteMessage(message);
    }

    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }


}
