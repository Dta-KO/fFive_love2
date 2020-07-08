package com.fivelove.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fivelove.db.model.User;
import com.fivelove.db.repository.UserRepository;

/**
 * Created by Nguyen Kim Khanh on 6/30/2020.
 */
public class UserViewModel extends AndroidViewModel {
    private LiveData<User> currentUser;
    UserRepository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository = UserRepository.getInstance();
        currentUser = repository.getUser();
    }

    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    public void updateUser(User user) {
        repository.updateUser(user);
    }
}
