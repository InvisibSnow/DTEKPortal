package com.dtek.portal;

import android.app.Application;
import android.content.Context;

import com.orhanobut.hawk.Hawk;

public class GlobalApplication extends Application {

    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();

        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
