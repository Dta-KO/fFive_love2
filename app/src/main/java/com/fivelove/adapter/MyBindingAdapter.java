package com.fivelove.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nguyen Kim Khanh on 6/18/2020.
 */
public class MyBindingAdapter {
    @BindingAdapter("android:src")
    public static void setImage(CircleImageView view, int resource) {
        view.setImageResource(resource);
    }
//    @BindingAdapter("app:imgBitmap")
//    public static void setImageBitmap(ImageView view, Bitmap bitmap){
//        view.setImageBitmap(bitmap);
//    }
//    @BindingAdapter("app:layoutManager")
//    public static void setLayoutManager(CardStackView view, Context context){
//        CardStackLayoutManager layoutManager = new CardStackLayoutManager(context);
//        layoutManager.setStackFrom(StackFrom.Right);
//        layoutManager.setVisibleCount(3);
//        layoutManager.setTranslationInterval(16.0f);
//        layoutManager.setScaleInterval(0.95f);
//        layoutManager.setMaxDegree(0.0f);
//        layoutManager.setDirections(Direction.HORIZONTAL);
//        view.setLayoutManager(layoutManager);
//        view.rewind();
//    }

}
