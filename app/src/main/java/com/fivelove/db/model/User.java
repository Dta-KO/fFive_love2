package com.fivelove.db.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Nguyen Kim Khanh on 6/8/2020.
 */
@Entity(tableName = "friends")
public class User {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String name;
    private int avt;

    public User(String name, int avt) {
        this.name = name;
        this.avt = avt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getAvt() {
        return avt;
    }

}
