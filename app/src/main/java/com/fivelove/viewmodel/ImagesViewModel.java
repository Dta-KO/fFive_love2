package com.fivelove.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.fivelove.App;
import com.fivelove.db.model.Image;
import com.fivelove.db.repository.ImageRepository;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 7/3/2020.
 */
public class ImagesViewModel extends AndroidViewModel {
    private LiveData<List<Image>> allImages;
    private ImageRepository repository;

    public ImagesViewModel(@NonNull App application) {
        super(application);
        repository = new ImageRepository(application);
        allImages = repository.getAllImages();

    }
    public void insertImage(Image image) {
        repository.insertImage(image);
    }

    public void updateImage(Image image) {
        repository.updateImage(image);
    }

    public void deleteImage(Image image) {
        repository.deleteImage(image);
    }

    public LiveData<List<Image>> getAllImages() {
        return allImages;
    }
}
