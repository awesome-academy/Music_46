package com.example.themo.musicmarvelous.ui.main.play;

import com.example.themo.musicmarvelous.BasePresenter;
import com.example.themo.musicmarvelous.BaseView;

public class PlayTrackContract {
    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {

        void showLoading();

        void hideLoading();
    }
}
