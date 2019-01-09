package com.example.themo.musicmarvelous.ui.main;

import com.example.themo.musicmarvelous.BasePresenter;
import com.example.themo.musicmarvelous.BaseView;
import com.example.themo.musicmarvelous.constants.State;
import com.example.themo.musicmarvelous.data.model.Track;

public interface MainContract {
    interface View extends BaseView {

        void showLoading();

        void hideLoading();

        void updateStateInfo(@State int state);

        void updateTrackInfo(Track track);

        void showAddToFavoriteSuccess();

        void showAddToFavoriteError();

        void showRemoveFromFavoriteSuccess();

        void showRemoveFromFavoriteError();
    }

    interface Presenter extends BasePresenter {

        void addToFavorite(Track track);

        void removeFromFavorite(Track track);
    }
}
