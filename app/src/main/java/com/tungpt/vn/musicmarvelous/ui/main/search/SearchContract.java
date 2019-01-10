package com.tungpt.vn.musicmarvelous.ui.main.search;

import com.tungpt.vn.musicmarvelous.BasePresenter;
import com.tungpt.vn.musicmarvelous.BaseView;
import com.tungpt.vn.musicmarvelous.data.model.Track;

import java.util.ArrayList;

public class SearchContract {

    interface Presenter extends BasePresenter {

        void searchTracks(String name, int offset);
    }

    interface View extends BaseView<Presenter> {

        void showNoTrackAvailable();

        void showTracks(ArrayList<Track> trackList);

        void showLoadingIndicator();

        void hideLoadingIndicator();

        void showLoadingTrackError(String err);
    }
}
