package com.example.themo.musicmarvelous.ui.main.home;

import com.example.themo.musicmarvelous.data.model.Genre;

import java.util.List;

public interface HomeContract {
    interface Presenter {
        void loadGenres();
    }

    interface View {
        void showGenres(List<Genre> genres);
    }
}
