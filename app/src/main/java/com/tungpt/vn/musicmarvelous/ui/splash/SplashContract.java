package com.example.themo.musicmarvelous.ui.splash;

import com.example.themo.musicmarvelous.BasePresenter;
import com.example.themo.musicmarvelous.BaseView;

public class SplashContract {
    interface View extends BaseView<Presenter> {
        void gotoMainActivity();
    }

    interface Presenter extends BasePresenter {
        void updateUI();
    }
}
