package com.fivelove.db.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

/**
 * Created by Nguyen Kim Khanh on 6/20/2020.
 */
@Entity(tableName = "images", indices = {@Index("idUser")}, foreignKeys = @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "idUser", onDelete = ForeignKey.CASCADE))
public class Image {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String bitmap;
    @ServerTimestamp
    private Date timePost;
    private int likes;
    private String descriptions;
    private String idUser;

    @Ignore
    public Image() {
    }

    public Image(String bitmap, Date timePost, int likes, String descriptions, String idUser) {
        this.bitmap = bitmap;
        this.timePost = timePost;
        this.likes = likes;
        this.descriptions = descriptions;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBitmap() {
        return bitmap;
    }

    public void setBitmap(String bitmap) {
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

}
