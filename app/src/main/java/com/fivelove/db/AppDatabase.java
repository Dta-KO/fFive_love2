package com.fivelove.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.fivelove.R;
import com.fivelove.db.converter.DateConverter;
import com.fivelove.db.dao.MessageDao;
import com.fivelove.db.dao.UserDao;
import com.fivelove.db.model.Message;
import com.fivelove.db.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
@Database(entities = {User.class, Message.class}, version = 1,exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract UserDao userDao();

    public abstract MessageDao messageDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private UserDao userDao;
        private MessageDao messageDao;
        private List<User> userList;

        private PopulateDbAsyncTask(AppDatabase db) {
            userDao = db.userDao();
            messageDao = db.messageDao();
            setUserList();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < userList.size(); i++) {
                userDao.insert(userList.get(i));
            }
            for (int i = 0; i < userList.size(); i++) {
                Random rdRandom = new Random();
                for (int j = 0; i < rdRandom.nextInt(10); i++) {
                    messageDao.insert(new Message(userList.get(i).getId(), "this is a message", 10000));
                }
            }
            return null;
        }

        public void setUserList() {
            userList = new ArrayList<>();
            userList.add(new User("Khánh", R.drawable.avatar));
            userList.add(new User("hưng", R.drawable.th));
            userList.add(new User("hùng", R.drawable.avatar));
            userList.add(new User("dũng", R.drawable.th));
            userList.add(new User("toàn", R.drawable.avatar));
            userList.add(new User("anh", R.drawable.avatar));

        }
    }
}
