package com.fivelove.db.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

/**
 * Created by Nguyen Kim Khanh on 6/9/2020.
 */
@Entity(indices = {@Index("idPartner")},foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "idPartner", onDelete = ForeignKey.CASCADE))
public class Message {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private long idPartner;
    private String text;
    @ServerTimestamp
    private Date timePost;

    public Message(long idPartner,String text) {
        this.idPartner = idPartner;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(long idPartner) {
        this.idPartner = idPartner;
    }

    public String getText() {
        return text;
    }

    public Date getTimePost() {
        return timePost;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTimePost(Date timePost) {
        this.timePost = timePost;
    }
}
