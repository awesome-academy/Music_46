package com.example.themo.musicmarvelous.data.repository;

import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.source.TrackDataSource;

public class TrackRepository implements TrackDataSource.LocalDataSource,
        TrackDataSource.RemoteDataSource {
    private static TrackRepository sInstance;
    private TrackDataSource.LocalDataSource mLocalDataSource;
    private TrackDataSource.RemoteDataSource mRemoteDataSource;

    private TrackRepository(TrackDataSource.LocalDataSource localDataSource,
                            TrackDataSource.RemoteDataSource remoteDataSource) {
        mLocalDataSource = localDataSource;
        mRemoteDataSource = remoteDataSource;
    }

    public static synchronized TrackRepository
    getInstance(TrackDataSource.LocalDataSource localDataSource,
                TrackDataSource.RemoteDataSource remoteDataSource) {
        if (sInstance == null) {
            sInstance = new TrackRepository(localDataSource, remoteDataSource);
        }
        return sInstance;
    }

    @Override
    public void getOfflineTracks(TrackDataSource.OnFetchDataListener<Track> listener) {

    }

    @Override
    public void getOnlineTracks(String genre, int limit, int offSet,
                                TrackDataSource.OnFetchDataListener<Track> listener) {

    }
}
