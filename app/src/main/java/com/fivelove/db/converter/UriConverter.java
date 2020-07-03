package com.fivelove.db.converter;

import android.net.Uri;

import androidx.room.TypeConverter;

/**
 * Created by Nguyen Kim Khanh on 6/28/2020.
 */
public class UriConverter{
    @TypeConverter
    public static Uri toUri(String uri){
        return Uri.parse(uri);
    }
    @TypeConverter
    public static String toString(Uri uri){
        return uri.toString();
    }
}
