package com.fivelove.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fivelove.db.model.User;

import java.util.List;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
@Dao
public interface FriendDao {
    @Insert
    void insert(User friend);

    @Update
    void update(User friend);

    @Delete
    void delete(User friend);

    @Query("SELECT * FROM friends")
    LiveData<List<User>> getAllFriends();

    @Query(("SELECT * FROM friends WHERE id = :id"))
    LiveData<User> getUser(String id);

    @Query("DELETE FROM friends")
    void deleteAllUser();
}
