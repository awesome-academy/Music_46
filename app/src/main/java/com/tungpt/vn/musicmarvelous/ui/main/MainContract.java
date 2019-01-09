package com.tungpt.vn.musicmarvelous.ui.main;

import com.tungpt.vn.musicmarvelous.BasePresenter;
import com.tungpt.vn.musicmarvelous.BaseView;
import com.tungpt.vn.musicmarvelous.constants.State;
import com.tungpt.vn.musicmarvelous.data.model.Track;

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
