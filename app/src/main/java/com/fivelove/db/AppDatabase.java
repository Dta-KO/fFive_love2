package com.fivelove.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.fivelove.db.converter.BitmapConverter;
import com.fivelove.db.converter.DateConverter;
import com.fivelove.db.converter.UriConverter;
import com.fivelove.db.dao.FriendDao;
import com.fivelove.db.dao.ImageDao;
import com.fivelove.db.dao.MessageDao;
import com.fivelove.db.model.Image;
import com.fivelove.db.model.Message;
import com.fivelove.db.model.User;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
@Database(entities = {User.class, Message.class, Image.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverter.class, BitmapConverter.class, UriConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract FriendDao userDao();

    public abstract MessageDao messageDao();

    public abstract ImageDao imageDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
