package com.example.themo.musicmarvelous.data.model;

public class Track {
    private int mId;
    private int mDuration;
    private String mTitle;
    private String mArtworkUrl;
    private String mDownloadUrl;
    private String mGenre;
    private String mArtist;
    private String mDescription;
    private boolean mIsDownloadable;
    private String mUri;
    private String mPublisherAlbumTitle;

    private Track(TrackBuilder builder) {
        mId = builder.mId;
        mDuration = builder.mDuration;
        mTitle = builder.mTitle;
        mArtworkUrl = builder.mArtworkUrl;
        mDownloadUrl = builder.mDownloadUrl;
        mGenre = builder.mGenre;
        mArtist = builder.mArtist;
        mDescription = builder.mDescription;
        mIsDownloadable = builder.mIsDownloadable;
        mUri = builder.mUri;
        mPublisherAlbumTitle = builder.mPublisherAlbumTitle;
    }

    public static class TrackBuilder {
        public String mPublisherAlbumTitle;
        private int mId;
        private int mDuration;
        private String mTitle;
        private String mArtworkUrl;
        private String mDownloadUrl;
        private String mGenre;
        private String mArtist;
        private String mDescription;
        private String mUri;
        private boolean mIsDownloadable;

        public TrackBuilder withId(int id) {
            this.mId = id;
            return this;
        }

        public TrackBuilder withDuration(int duration) {
            this.mDuration = duration;
            return this;
        }

        public TrackBuilder withTitle(String title) {
            this.mTitle = title;
            return this;
        }

        public TrackBuilder withArtworkUrl(String artworkUrl) {
            this.mArtworkUrl = artworkUrl;
            return this;
        }

        public TrackBuilder withDownloadUrl(String downloadUrl) {
            this.mDownloadUrl = downloadUrl;
            return this;
        }

        public TrackBuilder withGenre(String genre) {
            this.mGenre = genre;
            return this;
        }

        public TrackBuilder withArtist(String artist) {
            this.mArtist = artist;
            return this;
        }

        public TrackBuilder withDescription(String description) {
            this.mDescription = description;
            return this;
        }

        public TrackBuilder withDownloadable(boolean downloadable) {
            this.mIsDownloadable = downloadable;
            return this;
        }

        public TrackBuilder withUri(String uri) {
            this.mUri = uri;
            return this;
        }

        public TrackBuilder withPublisherAlbumTitle(String title) {
            this.mPublisherAlbumTitle = title;
            return this;
        }

        public Track build() {
            return new Track(this);
        }


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

    public String getArtworkUrl() {
        return mArtworkUrl;
    }

    public void setArtworkUrl(String artworkUrl) {
        mArtworkUrl = artworkUrl;
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

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public boolean isDownloadable() {
        return mIsDownloadable;
    }

    public void setDownloadable(boolean downloadable) {
        mIsDownloadable = downloadable;
    }

    public String getUri() {
        return mUri;
    }

    public void setUri(String uri) {
        mUri = uri;
    }

    public String getPublisherAlbumTitle() {
        return mPublisherAlbumTitle;
    }

    public void setPublisherAlbumTitle(String publisherAlbumTitle) {
        mPublisherAlbumTitle = publisherAlbumTitle;
    }
}
