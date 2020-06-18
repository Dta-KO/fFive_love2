package com.fivelove.db.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Nguyen Kim Khanh on 6/9/2020.
 */
@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "idUser", onDelete = ForeignKey.CASCADE))
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long idUser;
    private String text;
    private long timeSent;

    public Message(long idUser,String text, long timeSent) {
        this.idUser = idUser;
        this.text = text;
        this.timeSent = timeSent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdUser() {
        return idUser;
    }

    public String getText() {
        return text;
    }

    public long getTimeSent() {
        return timeSent;
    }
}
