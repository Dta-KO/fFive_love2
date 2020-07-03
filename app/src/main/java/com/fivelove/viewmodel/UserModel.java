package com.fivelove.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fivelove.db.model.User;
import com.fivelove.utils.Constant;

/**
 * Created by Nguyen Kim Khanh on 6/30/2020.
 */
public class UserModel extends AndroidViewModel {
    private LiveData<User> user;
    public UserModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void setUser(LiveData<User> user) {
        this.user = user;
    }
}
