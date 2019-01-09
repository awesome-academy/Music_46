package com.example.themo.musicmarvelous.ui.main.favorite;

import com.example.themo.musicmarvelous.data.model.Track;

import java.util.List;

public interface FavoriteContract {
    interface Presenter {
        void getAllFavorites();
    }

    interface View {
        void showFavorites(List<Track> tracks);
    }
}
