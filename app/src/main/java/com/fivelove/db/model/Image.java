package com.fivelove.db.model;

import android.graphics.Bitmap;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

/**
 * Created by Nguyen Kim Khanh on 6/20/2020.
 */
@Entity(tableName = "images",indices = {@Index("idUser")},foreignKeys = @ForeignKey(entity = User.class,parentColumns = "id",childColumns = "idUser",onDelete = ForeignKey.CASCADE))
public class Image {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private Bitmap bitmap;
    @ServerTimestamp
    private Date timePost;
    private int likes;
    private String descriptions;
    private long idUser;

    public Image(Bitmap bitmap, int likes, String descriptions) {
        this.bitmap = bitmap;
        this.likes = likes;
        this.descriptions = descriptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Date getTimePost() {
        return timePost;
    }

    public void setTimePost(Date timePost) {
        this.timePost = timePost;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

}
