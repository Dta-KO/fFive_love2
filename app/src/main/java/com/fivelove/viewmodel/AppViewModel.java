package com.fivelove.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppRepository;
import com.fivelove.db.model.Image;
import com.fivelove.db.model.Message;
import com.fivelove.db.model.User;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
public class AppViewModel extends AndroidViewModel {
    private AppRepository repository;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Message>> allMessages;
    private LiveData<List<Image>> allImages;

    public AppViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository((App) application);
        allUsers = repository.getAllFriends();
        allMessages = repository.getAllMessages();
        allImages = repository.getAllImages();
    }

    public void insertUser(User user) {
        repository.insertUser(user);
    }

    public void deleteUser(User user) {
        repository.deleteUser(user);
    }

    public void updateUser(User user) {
        repository.updateUser(user);
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
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

    public void insertImage(Image image) {
        repository.insertImage(image);
    }

    public void updateImage(Image image) {
        repository.updateImage(image);
    }

    public void deleteImage(Image image) {
        repository.deleteImage(image);
    }

    public LiveData<List<Image>> getAllImages() {
        return allImages;
    }
}
