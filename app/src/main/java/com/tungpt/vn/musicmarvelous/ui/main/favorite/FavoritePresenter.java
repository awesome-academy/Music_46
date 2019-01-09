package com.tungpt.vn.musicmarvelous.ui.main.favorite;

import com.tungpt.vn.musicmarvelous.data.model.Track;
import com.tungpt.vn.musicmarvelous.data.repository.TrackRepository;

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
