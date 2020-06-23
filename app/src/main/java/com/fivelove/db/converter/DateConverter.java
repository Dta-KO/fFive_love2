package com.fivelove.db.converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import java.util.Date;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
public class DateConverter extends Date {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
