package com.tungpt.vn.musicmarvelous.ui.main.play;

public class PlayTrackPresenter implements PlayTrackContract.Presenter {

    private PlayTrackContract.View mView;

    public PlayTrackPresenter(PlayTrackContract.View view) {
        mView = view;
    }

    @Override
    public void start() {
        mView.setPresenter(this);
    }
}
