package com.example.themo.musicmarvelous.ui.main.genredetail;

import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.repository.TrackRepository;
import com.example.themo.musicmarvelous.data.source.TrackDataSource;

import java.util.ArrayList;
import java.util.List;

public class GenreDetailPresenter implements GenreDetailContract.Presenter,
        TrackDataSource.OnFetchDataListener {
    private GenreDetailContract.View mView;
    private TrackRepository mTrackRepository;

    public GenreDetailPresenter(GenreDetailContract.View view, TrackRepository trackRepository) {
        mView = view;
        mTrackRepository = trackRepository;
    }

    @Override
    public void loadTracks(String genre, int limit, int offset) {
        mTrackRepository.getOnlineTracks(genre, limit, offset, this);
    }

    @Override
    public void start() {

    }

    @Override
    public void onFetchDataSuccess(List data) {
        if (data == null || data.isEmpty()) {
            mView.showNoTrackAvailable();
            return;
        }
        mView.showTrack((ArrayList<Track>) data);
    }

    @Override
    public void onFetchDataFailure(Exception e) {
        mView.showLoadingTracksError(e.getMessage());
    }
}
