package com.fivelove.db.repository;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppDatabase;
import com.fivelove.db.dao.ImageDao;
import com.fivelove.db.model.Image;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class ImageRepository {
    private ImageDao imageDao;
    private LiveData<List<Image>> allImages;
    private String idFriend;


    public String getIdFriend() {
        return idFriend;
    }

    public void setIdFriend(String idFriend) {
        this.idFriend = idFriend;
    }


    public ImageRepository(App application) {
        AppDatabase db = AppDatabase.getInstance(application);

        imageDao = db.imageDao();
        allImages = imageDao.getAllImage(idFriend);
    }

    public void insertImage(Image image) {
        imageDao.insert(image);
    }

    public void deleteImage(Image image) {
        imageDao.delete(image);

    }

    public void updateImage(Image image) {
        imageDao.update(image);

    }

    public LiveData<List<Image>> getAllImages() {
        return allImages;
    }

}
