package com.tungpt.vn.musicmarvelous.data.source.remote;

import com.tungpt.vn.musicmarvelous.data.model.Track;
import com.tungpt.vn.musicmarvelous.data.source.TrackDataSource;
import com.tungpt.vn.musicmarvelous.utils.StringUtil;

public class TrackRemoteDataSource implements TrackDataSource.RemoteDataSource {
    private static TrackRemoteDataSource sInstance;

    public static TrackRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new TrackRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getOnlineTracks(String genre, int limit, int offSet,
                                TrackDataSource.OnFetchDataListener<Track> listener) {
        new FetchTrackFromUrl(listener)
                .execute(StringUtil.formatTrackURL(genre, limit, offSet));
    }

    @Override
    public void searchTracksOnline(String name, int offSet, TrackDataSource.OnFetchDataListener<Track> listener) {

    }
}
