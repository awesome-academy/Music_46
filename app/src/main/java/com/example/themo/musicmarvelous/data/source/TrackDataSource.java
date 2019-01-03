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
        void getOfflineTracksInFolder(String folderName, OnFetchDataListener<Track> listener);

        void getOfflineTracks(OnFetchDataListener<Track> listener);

        boolean deleteOfflineTrack(Track track);

        boolean deleteTrack(Track track);

        List<Track> getTracksFavorite();

        void addTrackToFavorite(Track track, OnQueryDatabaseListener listener);

        void deleteTrackFavorite(Track track, OnQueryDatabaseListener listener);

        boolean isTrackInFavorite(Track track);

    }

    interface RemoteDataSource {
        void getOnlineTracks(String genre, int limit, int offSet, OnFetchDataListener<Track> listener);

        void searchTracksOnline(String name, int offSet, OnFetchDataListener<Track> listener);
    }
}
