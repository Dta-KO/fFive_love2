package com.fivelove.db.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Nguyen Kim Khanh on 6/9/2020.
 */
@Entity(indices = {@Index("idUser")},foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "idUser", onDelete = ForeignKey.CASCADE))
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long idUser;
    private String text;
    private Date timePost;

    public Message(String text, Date timePost) {
        this.text = text;
        this.timePost = timePost;
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

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public String getText() {
        return text;
    }

    public Date getTimePost() {
        return timePost;
    }
}
