package com.fivelove.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fivelove.db.model.Image;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/21/2020.
 */
@Dao
public interface ImageDao {
    @Insert
    void insert(Image image);

    @Delete
    void delete(Image image);

    @Update
    void update(Image image);

    @Query("SELECT * FROM images WHERE idUser = :idUser ORDER BY timePost DESC")
    LiveData<List<Image>> getAllImage(String idUser);
}
