package com.tungpt.vn.musicmarvelous.data.repository;

import com.tungpt.vn.musicmarvelous.data.model.Track;
import com.tungpt.vn.musicmarvelous.data.source.TrackDataSource;
import com.tungpt.vn.musicmarvelous.data.source.local.TrackLocalDataSource;
import com.tungpt.vn.musicmarvelous.data.source.remote.TrackRemoteDataSource;

import java.util.List;

public class TrackRepository implements TrackDataSource.LocalDataSource,
        TrackDataSource.RemoteDataSource {
    private static TrackRepository sInstance;
    private TrackDataSource.LocalDataSource mLocalDataSource;
    private TrackDataSource.RemoteDataSource mRemoteDataSource;

    private TrackRepository(TrackDataSource.LocalDataSource localDataSource,
                            TrackDataSource.RemoteDataSource remoteDataSource) {
        this.mLocalDataSource = localDataSource;
        this.mRemoteDataSource = remoteDataSource;
    }

    public static TrackRepository getInstance() {
        if (sInstance == null) {
            sInstance = new TrackRepository(TrackLocalDataSource.getInstance(),
                    TrackRemoteDataSource.getInstance());
        }
        return sInstance;
    }

    @Override
    public void getOfflineTracksInFolder(String folderName, TrackDataSource.OnFetchDataListener<Track> listener) {
        if (mLocalDataSource != null) {
            mLocalDataSource.getOfflineTracksInFolder(folderName, listener);
        }
    }

    @Override
    public void getOfflineTracks(TrackDataSource.OnFetchDataListener<Track> listener) {
        if (mLocalDataSource != null) {
            mLocalDataSource.getOfflineTracks(listener);
        }
    }

    @Override
    public boolean deleteOfflineTrack(Track track) {
        return mLocalDataSource.deleteOfflineTrack(track);
    }

    @Override
    public boolean deleteTrack(Track track) {
        return mLocalDataSource != null && mLocalDataSource.deleteTrack(track);
    }

    @Override
    public List<Track> getTracksFavorite() {
        return mLocalDataSource != null ? mLocalDataSource.getTracksFavorite() : null;
    }

    @Override
    public void addTrackToFavorite(Track track, TrackDataSource.OnQueryDatabaseListener listener) {
        if (mLocalDataSource != null) {
            mLocalDataSource.addTrackToFavorite(track, listener);
        }
    }

    @Override
    public void deleteTrackFavorite(Track track, TrackDataSource.OnQueryDatabaseListener listener) {
        if (mLocalDataSource != null) {
            mLocalDataSource.deleteTrackFavorite(track, listener);
        }
    }

    @Override
    public boolean isTrackInFavorite(Track track) {
        return mLocalDataSource.isTrackInFavorite(track);
    }

    @Override
    public void getOnlineTracks(String genre, int limit, int offSet,
                                TrackDataSource.OnFetchDataListener<Track> listener) {
        if (mRemoteDataSource != null) {
            mRemoteDataSource.getOnlineTracks(genre, limit, offSet, listener);
        }
    }

    public void searchTracksOnline(String name, int offSet, TrackDataSource.OnFetchDataListener<Track> listener) {
        if (mRemoteDataSource != null) {
            mRemoteDataSource.searchTracksOnline(name, offSet, listener);
        }
    }
}
