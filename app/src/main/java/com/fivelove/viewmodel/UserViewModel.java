package com.fivelove.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.model.User;
import com.fivelove.db.repository.UserRepository;

/**
 * Created by Nguyen Kim Khanh on 6/30/2020.
 */
public class UserViewModel extends AndroidViewModel {
    private LiveData<User> currentUser;


    public UserViewModel(@NonNull App application) {
        super(application);
        UserRepository repository = new UserRepository(application);
        currentUser = repository.getCurrentUser();
    }

    public LiveData<User> getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(LiveData<User> currentUser) {
        this.currentUser = currentUser;
    }
}
