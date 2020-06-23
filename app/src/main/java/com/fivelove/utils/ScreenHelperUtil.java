package com.fivelove.utils;

import android.app.Activity;
import android.graphics.Point;
import android.view.Display;

/**
 * Created by Nguyen Kim Khanh on 6/20/2020.
 */
public class ScreenHelperUtil {
    public int screenWidth;
    public int screenHeight;

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        this.screenWidth = point.x;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getRealSize(point);
        this.screenHeight = point.y;
    }


}
