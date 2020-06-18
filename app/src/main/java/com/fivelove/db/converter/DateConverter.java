package com.fivelove.db.converter;

import androidx.room.TypeConverter;

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
