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

    public Track(){
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

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmImageTrack() {
        return mImageTrack;
    }

    public void setmImageTrack(String mImageTrack) {
        this.mImageTrack = mImageTrack;
    }

    public String getmDownloadUrl() {
        return mDownloadUrl;
    }

    public void setmDownloadUrl(String mDownloadUrl) {
        this.mDownloadUrl = mDownloadUrl;
    }

    public String getmGenre() {
        return mGenre;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    public String getmArtist() {
        return mArtist;
    }

    public void setmArtist(String mArtist) {
        this.mArtist = mArtist;
    }

    public boolean ismIsDownloadable() {
        return mIsDownloadable;
    }

    public void setmIsDownloadable(boolean mIsDownloadable) {
        this.mIsDownloadable = mIsDownloadable;
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
