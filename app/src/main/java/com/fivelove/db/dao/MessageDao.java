package com.fivelove.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fivelove.db.model.Message;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
@Dao
public interface MessageDao {
    @Insert
    void insert(Message message);

    @Delete
    void delete(Message message);

    @Update
    void update(Message message);

    @Query("SELECT * FROM message WHERE idPartner = :idUser ORDER BY timePost DESC")
    LiveData<List<Message>> getAllMessage(String idUser);

}
