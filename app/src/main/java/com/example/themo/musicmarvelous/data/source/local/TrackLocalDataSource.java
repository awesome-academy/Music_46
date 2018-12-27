package com.example.themo.musicmarvelous.data.source.local;

import android.content.ContentResolver;
import android.support.annotation.NonNull;

import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.source.TrackDataSource;

public class TrackLocalDataSource implements TrackDataSource.LocalDataSource {

    private static TrackLocalDataSource sInstance;
    private ContentResolver mContentResolver;

    public TrackLocalDataSource(ContentResolver contentResolver) {
        mContentResolver = contentResolver;
    }

    public static TrackLocalDataSource getInstance(@NonNull ContentResolver contentResolver) {
        if (sInstance == null) {
            sInstance = new TrackLocalDataSource(contentResolver);
        }
        return sInstance;
    }

    @Override
    public void getOfflineTracks(TrackDataSource.OnFetchDataListener<Track> listener) {

    }
}
