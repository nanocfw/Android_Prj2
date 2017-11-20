package com.example.luanabelusso.aps_android;

import android.app.Application;
import android.content.Context;

/**
 * Created by Marciano on 20/11/2017.
 */

public class FastSort extends Application {
    private static FastSort app;

    public void onCreate() {
        super.onCreate();
        app = this;
    }

    public static Context getContext() {
        return app.getApplicationContext();
    }
}
