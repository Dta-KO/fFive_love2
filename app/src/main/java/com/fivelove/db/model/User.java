package com.fivelove.db.model;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by Nguyen Kim Khanh on 6/8/2020.
 */
@Entity(tableName = "friends")
public class User {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private String avt;

    @Ignore
    public User() {
    }

    public User(@NonNull String id, String name, String avt) {
        this.id = id;
        this.name = name;
        this.avt = avt;

    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvt() {
        return avt;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvt(String avt) {
        this.avt = avt;
    }
}
