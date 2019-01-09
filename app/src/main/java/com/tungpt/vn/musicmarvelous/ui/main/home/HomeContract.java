package com.tungpt.vn.musicmarvelous.ui.main.home;

import com.tungpt.vn.musicmarvelous.data.model.Genre;

import java.util.List;

public interface HomeContract {
    interface Presenter {
        void loadGenres();
        void loadSongsByGenre();
    }

    interface View {
        void showGenres(List<Genre> genres);
        void showSongsByGenre();
    }
}
