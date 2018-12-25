package com.example.themo.musicmarvelous.constants;

import android.support.annotation.StringDef;

@StringDef({
        TrackEntity.ARTWORK_URL,
        TrackEntity.DESCRIPTION,
        TrackEntity.DOWNLOADABLE,
        TrackEntity.DOWNLOAD_URL,
        TrackEntity.DURATION,
        TrackEntity.GENRE,
        TrackEntity.ID,
        TrackEntity.TITLE,
        TrackEntity.ARTIST,
        TrackEntity.URI,
        TrackEntity.PUBLISHER_METADATA,
        TrackEntity.PUBLISHER_ALBUM_TITLE
})

public @interface TrackEntity {
    String ARTWORK_URL = "artwork_url";
    String DESCRIPTION = "description";
    String DOWNLOADABLE = "downloadable";
    String DOWNLOAD_URL = "download_url";
    String DURATION = "full_duration";
    String GENRE = "genre";
    String ID = "id";
    String TITLE = "title";
    String ARTIST = "artist";
    String URI = "uri";
    String DISPLAY_DATE = "display_date";
    String PUBLISHER_METADATA = "publisher_metadata";
    String PUBLISHER_ALBUM_TITLE = "album_title";
}
