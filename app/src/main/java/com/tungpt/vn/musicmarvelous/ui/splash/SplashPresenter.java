package com.tungpt.vn.musicmarvelous.ui.splash;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View mView;

    public SplashPresenter(SplashContract.View view) {
        mView = view;
    }

    @Override
    public void updateUI() {
        mView.gotoMainActivity();
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }
}
