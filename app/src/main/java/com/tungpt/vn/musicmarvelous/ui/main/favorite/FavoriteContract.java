package com.tungpt.vn.musicmarvelous.ui.main.favorite;

import com.tungpt.vn.musicmarvelous.data.model.Track;

import java.util.List;

public interface FavoriteContract {
    interface Presenter {
        void getAllFavorites();
    }

    interface View {
        void showFavorites(List<Track> tracks);
    }
}
