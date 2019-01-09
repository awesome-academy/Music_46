package com.tungpt.vn.musicmarvelous.ui.main.home;

import com.tungpt.vn.musicmarvelous.data.model.Genre;
import com.tungpt.vn.musicmarvelous.data.repository.GenreRepository;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;
    private GenreRepository mGenreRepository;

    public HomePresenter(HomeContract.View view, GenreRepository genreRepository) {
        mView = view;
        mGenreRepository = genreRepository;
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
