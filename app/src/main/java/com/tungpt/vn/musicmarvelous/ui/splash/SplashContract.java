package com.tungpt.vn.musicmarvelous.ui.splash;

import com.tungpt.vn.musicmarvelous.BasePresenter;
import com.tungpt.vn.musicmarvelous.BaseView;

public class SplashContract {
    interface View extends BaseView<Presenter> {
        void gotoMainActivity();
    }

    interface Presenter extends BasePresenter {
        void updateUI();
    }
}
