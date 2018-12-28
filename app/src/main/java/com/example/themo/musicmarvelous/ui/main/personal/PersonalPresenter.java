package com.example.themo.musicmarvelous.ui.main.personal;

import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.repository.TrackRepository;

import java.util.List;

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
