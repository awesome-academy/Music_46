package com.example.themo.musicmarvelous.data.source;

import com.example.themo.musicmarvelous.data.model.Track;

import java.util.List;

public interface TrackDataSource {

    interface OnFetchDataListener<T> {
        void onFetchDataSuccess(List<T> data);

        void onFetchDataFailure(Exception e);
    }

    interface OnQueryDatabaseListener {
        void onQuerySuccess(String msg);

        void onQueryFailure(Exception e);
    }

    interface LocalDataSource {
    }

    interface RemoteDataSource {
        void getTracksRemote(String genre, int limit, int offSet, OnFetchDataListener<Track> listener);
    }
}
