package com.example.themo.musicmarvelous.data.source.remote;

import android.os.AsyncTask;

import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.source.TrackDataSource;

import java.util.List;

public class FetchTrackFromUrl extends AsyncTask<String, Void, List<Track>> {

    private TrackDataSource.OnFetchDataListener<Track> mListener;

    public FetchTrackFromUrl(TrackDataSource.OnFetchDataListener<Track> listener) {
        mListener = listener;
    }

    @Override
    protected List<Track> doInBackground(String... strings) {
        return null;
    }
}
