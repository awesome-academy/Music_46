package com.example.themo.musicmarvelous.data.source.remote;

import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.source.TrackDataSource;

public class TrackRemoteDataSource implements TrackDataSource.RemoteDataSource {
    @Override
    public void getOnlineTracks(String genre, int limit, int offSet,
                                TrackDataSource.OnFetchDataListener<Track> listener) {

    }
}
