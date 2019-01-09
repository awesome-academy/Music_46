package com.tungpt.vn.musicmarvelous.ui.main;

import com.tungpt.vn.musicmarvelous.data.model.Track;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void addToFavorite(Track track) {

    }

    @Override
    public void removeFromFavorite(Track track) {

    }
}
