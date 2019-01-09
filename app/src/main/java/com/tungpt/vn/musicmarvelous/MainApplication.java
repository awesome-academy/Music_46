package com.example.themo.musicmarvelous;

import android.app.Application;

import com.example.themo.musicmarvelous.data.source.local.TrackLocalDataSource;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TrackLocalDataSource.init(this);
    }
}
