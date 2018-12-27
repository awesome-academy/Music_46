package com.example.themo.musicmarvelous.ui.main.genredetail;

import com.example.themo.musicmarvelous.BasePresenter;
import com.example.themo.musicmarvelous.BaseView;
import com.example.themo.musicmarvelous.data.model.Track;

import java.util.ArrayList;

public interface GenreDetailContract {
    interface Presenter extends BasePresenter {
        void loadTracks(String genre, int limit, int offset);
    }

    interface View extends BaseView<Presenter> {
        void showTrack(ArrayList<Track> tracks);

        void showNoTrackAvailable();

        void showLoadingTracksError(String error);
    }
}
