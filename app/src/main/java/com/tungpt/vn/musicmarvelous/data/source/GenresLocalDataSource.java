package com.tungpt.vn.musicmarvelous.data.source;

import com.tungpt.vn.musicmarvelous.R;
import com.tungpt.vn.musicmarvelous.constants.GenreEntity;
import com.tungpt.vn.musicmarvelous.data.model.Genre;

import java.util.ArrayList;
import java.util.List;

public class GenresLocalDataSource implements GenresDataSource {

    @Override
    public List<Genre> getGenres() {
        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre(GenreEntity.ALL_MUSIC, R.string.genre_all_music, R.raw.allmusic));
        genres.add(new Genre(GenreEntity.ALL_AUDIO, R.string.genre_all_audio, R.raw.audio));
        genres.add(new Genre(GenreEntity.ALTERNATIVE, R.string.genre_alternative, R.raw.alternative));
        genres.add(new Genre(GenreEntity.AMBIENT, R.string.genre_ambient, R.raw.ambient));
        genres.add(new Genre(GenreEntity.CLASSICAL, R.string.genre_classical, R.raw.classical));
        genres.add(new Genre(GenreEntity.COUNTRY, R.string.genre_country, R.raw.countryside));
        return genres;
    }
}
