package com.tungpt.vn.musicmarvelous.ui.main.genredetail;

import com.tungpt.vn.musicmarvelous.BasePresenter;
import com.tungpt.vn.musicmarvelous.BaseView;
import com.tungpt.vn.musicmarvelous.data.model.Track;

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
