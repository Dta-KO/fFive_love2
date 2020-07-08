package com.fivelove.db.repository;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.AppDatabase;
import com.fivelove.db.dao.ImageDao;
import com.fivelove.db.model.Image;
import com.fivelove.utils.Constant;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class ImageRepository {
    private ImageDao imageDao;
    private LiveData<List<Image>> allImages;
    private String idFriend;


    public ImageRepository(App application) {
        AppDatabase db = AppDatabase.getInstance(application);

        imageDao = db.imageDao();
        allImages = imageDao.getAllImage(idFriend);
    }

    public void insertImage(Image image) {
        Constant.EXECUTORS.diskIO().execute(() -> imageDao.insert(image));
    }

    public void deleteImage(Image image) {
        Constant.EXECUTORS.diskIO().execute(() -> imageDao.delete(image));

    }

    public void updateImage(Image image) {
        Constant.EXECUTORS.diskIO().execute(() -> imageDao.update(image));

    }

    public LiveData<List<Image>> getAllImages() {
        return allImages;
    }

}
