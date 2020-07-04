package com.fivelove.db.repository;

import android.os.AsyncTask;

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


    public ImageRepository(App application) {
        AppDatabase db = AppDatabase.getInstance(application);

        imageDao = db.imageDao();
        allImages = imageDao.getAllImage(idFriend);
    }

    public void insertImage(Image image) {
        new ImageRepository.InsertImageAsyncTask(imageDao).execute(image);
    }

    public void deleteImage(Image image) {
        new ImageRepository.DeleteImageAsyncTask(imageDao).execute(image);
    }

    public void updateImage(Image image) {
        new ImageRepository.UpdateImageAsyncTask(imageDao).execute(image);
    }

    public LiveData<List<Image>> getAllImages() {
        return allImages;
    }
    private static class UpdateImageAsyncTask extends AsyncTask<Image, Void, Void> {
        private ImageDao imageDao;

        private UpdateImageAsyncTask(ImageDao imageDao) {
            this.imageDao = imageDao;
        }


        @Override
        protected Void doInBackground(Image... images) {
            imageDao.update(images[0]);
            return null;
        }
    }

    private static class InsertImageAsyncTask extends AsyncTask<Image, Void, Void> {
        private ImageDao imageDao;

        private InsertImageAsyncTask(ImageDao imageDao) {
            this.imageDao = imageDao;
        }


        @Override
        protected Void doInBackground(Image... images) {
            imageDao.insert(images[0]);
            return null;
        }
    }

    private static class DeleteImageAsyncTask extends AsyncTask<Image, Void, Void> {
        private ImageDao imageDao;

        private DeleteImageAsyncTask(ImageDao imageDao) {
            this.imageDao = imageDao;
        }

        public ImageDao getImageDao() {
            return imageDao;
        }

        public void setImageDao(ImageDao imageDao) {
            this.imageDao = imageDao;
        }

        @Override
        protected Void doInBackground(Image... images) {
            imageDao.delete(images[0]);
            return null;
        }
    }
}
