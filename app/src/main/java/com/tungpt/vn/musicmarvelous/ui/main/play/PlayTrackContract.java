package com.tungpt.vn.musicmarvelous.ui.main.play;

import com.tungpt.vn.musicmarvelous.BasePresenter;
import com.tungpt.vn.musicmarvelous.BaseView;

public class PlayTrackContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();
    }
}
