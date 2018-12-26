package com.example.themo.musicmarvelous.ui.main.home;

import com.example.themo.musicmarvelous.data.model.Genre;
import com.example.themo.musicmarvelous.data.repository.GenreRepository;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private GenreRepository mGenreRepository;

    public HomePresenter(HomeContract.View view, GenreRepository instance) {
        mView = view;
        mGenreRepository = instance;
    }

    @Override
    public void loadGenres() {
        List<Genre> genres = mGenreRepository.getGenres();
        mView.showGenres(genres);
    }

    @Override
    public void loadSongsByGenre() {

    }
}
