package com.fivelove.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.model.User;
import com.fivelove.db.repository.FriendsRepository;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class FriendsViewModel extends AndroidViewModel {
    private LiveData<List<User>> allFriends;
    private FriendsRepository repository;

    public FriendsViewModel(@NonNull Application application) {
        super(application);
        repository = new FriendsRepository((App) application);
        allFriends = repository.getAllFriends();

    }

    public void insertUser(User friend) {
        repository.insertUser(friend);
    }

    public void deleteUser(User friend) {
        repository.deleteUser(friend);
    }

    public void updateUser(User friend) {
        repository.updateUser(friend);
    }

    public LiveData<List<User>> getAllFriends() {
        return allFriends;
    }
}
