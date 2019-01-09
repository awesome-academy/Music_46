package com.example.themo.musicmarvelous.ui.main.favorite;

import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.repository.TrackRepository;

import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {
    private FavoriteContract.View mView;
    private TrackRepository mTrackRepository;

    public FavoritePresenter(FavoriteContract.View view) {
        mView = view;
        mTrackRepository = TrackRepository.getInstance();
    }

    @Override
    public void getAllFavorites() {
        List<Track> tracks = mTrackRepository.getTracksFavorite();
        mView.showFavorites(tracks);
    }
}
