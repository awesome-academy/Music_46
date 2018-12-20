package com.example.themo.musicmarvelous.data.model;

public class Track {
    private int mId;
    private int mDuration;
    private String mTitle;
    private String mImageTrack;
    private String mDownloadUrl;
    private String mGenre;
    private String mArtist;
    private boolean mIsDownloadable;

    public Track() {
    }

    public Track(int mId, int mDuration, String mTitle, String mImageSong, String mDownloadUrl,
                 String mGenre, String mArtist, boolean mIsDownloadable) {
        this.mId = mId;
        this.mDuration = mDuration;
        this.mTitle = mTitle;
        this.mImageTrack = mImageSong;
        this.mDownloadUrl = mDownloadUrl;
        this.mGenre = mGenre;
        this.mArtist = mArtist;
        this.mIsDownloadable = mIsDownloadable;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public int getDuration() {
        return mDuration;
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getImageTrack() {
        return mImageTrack;
    }

    public void setImageTrack(String imageTrack) {
        mImageTrack = imageTrack;
    }

    public String getDownloadUrl() {
        return mDownloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        mDownloadUrl = downloadUrl;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String genre) {
        mGenre = genre;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public boolean isDownloadable() {
        return mIsDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        mIsDownloadable = downloadable;
    }

    public final class TrackEntity {
        public static final String ID = "id";
        public static final String DURATION = "duration";
        public static final String TITLE = "title";
        public static final String IMAGE_TRACK = "artwork_url";
        public static final String URL_DOWNLOAD = "download_url";
        public static final String GENRE = "genre";
        public static final String NAME_ARTIST = "label_name";
        public static final String DOWNLOADABLE = "downloadable";
    }
}
