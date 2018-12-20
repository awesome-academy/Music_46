package com.example.themo.musicmarvelous.data.model;

public class Genre {
    private String mGenreName;
    private int mGenreImage;

    public Genre(){
    }

    public Genre(String genreName, int genreImage) {
        mGenreName = genreName;
        mGenreImage = genreImage;
    }

    public String getGenreName() {
        return mGenreName;
    }

    public void setGenreName(String genreName) {
        mGenreName = genreName;
    }

    public int getGenreImage() {
        return mGenreImage;
    }

    public void setGenreImage(int genreImage) {
        mGenreImage = genreImage;
    }

    public static class GenreEntity {
        public static final String ALL_MUSIC = "all-music";
        public static final String ALL_AUDIO = "all-audio";
        public static final String ALTERNATIVE_ROCK = "alternativerock";
        public static final String AMBIENT = "ambient";
        public static final String CLASSICAL = "classical";
        public static final String COUNTRY = "country";
    }
}
