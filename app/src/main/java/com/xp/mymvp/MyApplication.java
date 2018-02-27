package com.xp.mymvp;

import android.app.Application;
import android.content.Context;

/**
 * 描  述：Application
 * 开发者：x i a n g  p a n
 * 时  间：2017/5/18.
 */

public class MyApplication extends Application{
    private static Context baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = getApplicationContext();

    }

    public static Context getAppContext() {
        return baseApplication;
    }


}
