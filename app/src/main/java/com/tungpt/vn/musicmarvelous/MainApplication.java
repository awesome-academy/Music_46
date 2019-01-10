package com.tungpt.vn.musicmarvelous;

import android.app.Application;

import com.tungpt.vn.musicmarvelous.data.source.local.TrackLocalDataSource;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TrackLocalDataSource.init(this);
    }
}
