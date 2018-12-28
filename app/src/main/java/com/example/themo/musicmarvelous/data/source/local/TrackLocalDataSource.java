package com.example.themo.musicmarvelous.data.source.local;

import android.content.Context;

import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.source.TrackDataSource;

import java.util.List;

public class TrackLocalDataSource implements TrackDataSource.LocalDataSource {

    private static TrackLocalDataSource sInstance;
    private Context mContext;

    public TrackLocalDataSource(Context context) {
        mContext = context;
    }

    public static TrackLocalDataSource getInstance() {
        return sInstance;
    }

    public static void init(Context context) {
        if (sInstance == null) {
            sInstance = new TrackLocalDataSource(context);
        }
    }

    @Override
    public void getOfflineTracksInFolder(String folderName, TrackDataSource.OnFetchDataListener<Track> listener) {

    }

    @Override
    public void getOfflineTracks(TrackDataSource.OnFetchDataListener<Track> listener) {

    }

    @Override
    public boolean deleteOfflineTrack(Track track) {
        return false;
    }

    @Override
    public boolean deleteTrack(Track track) {
        return false;
    }

    @Override
    public List<Track> getTracksFavorite() {
        return null;
    }

    @Override
    public void addTrackToFavorite(Track track, TrackDataSource.OnQueryDatabaseListener listener) {

    }

    @Override
    public void deleteTrackFavorite(Track track, TrackDataSource.OnQueryDatabaseListener listener) {

    }

    @Override
    public boolean isTrackInFavorite(Track track) {
        return false;
    }
}
