package com.fivelove;

import android.app.Application;
import android.content.Context;

/**
 * Created by Nguyen Kim Khanh on 6/16/2020.
 */
public class App extends Application {
    private static Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static Application getApp() {
        return app;
    }

    public static Context getAppContext() {
        return app.getApplicationContext();
    }
}
