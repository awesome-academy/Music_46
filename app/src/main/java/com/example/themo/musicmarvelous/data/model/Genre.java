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

    public String getmGenreName() {
        return mGenreName;
    }

    public void setmGenreName(String mGenreName) {
        this.mGenreName = mGenreName;
    }

    public int getmGenreImage() {
        return mGenreImage;
    }

    public void setmGenreImage(int mGenreImage) {
        this.mGenreImage = mGenreImage;
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
