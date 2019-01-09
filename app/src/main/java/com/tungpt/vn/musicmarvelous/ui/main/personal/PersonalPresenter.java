package com.tungpt.vn.musicmarvelous.ui.main.personal;

import com.tungpt.vn.musicmarvelous.data.repository.TrackRepository;

public class PersonalPresenter implements PersonalContract.Presenter {
    private PersonalContract.View mView;
    private TrackRepository mTrackRepository;

    public PersonalPresenter(PersonalContract.View view) {
        mView = view;
        mTrackRepository = TrackRepository.getInstance();
    }

    @Override
    public void getOfflineTrackCount() {

    }

    @Override
    public void getFavoriteTrackCount() {

    }

    @Override
    public void getDownloadTrackCount() {

    }
}
