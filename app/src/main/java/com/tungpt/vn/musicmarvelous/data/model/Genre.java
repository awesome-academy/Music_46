package com.tungpt.vn.musicmarvelous.data.model;

public class Genre {
    private String mGenreName;
    private int mGenre;
    private int mGenreImage;

    public Genre() {
    }

    public Genre(String genreName, int genre, int genreImage) {
        mGenreName = genreName;
        mGenre = genre;
        mGenreImage = genreImage;
    }

    public String getGenreName() {
        return mGenreName;
    }

    public void setGenreName(String genreName) {
        mGenreName = genreName;
    }

    public int getGenre() {
        return mGenre;
    }

    public void setGenre(int genre) {
        mGenre = genre;
    }

    public int getGenreImage() {
        return mGenreImage;
    }

    public void setGenreImage(int genreImage) {
        mGenreImage = genreImage;
    }
}
