package com.tungpt.vn.musicmarvelous.ui.main;

import com.tungpt.vn.musicmarvelous.data.model.Track;

import java.util.List;

public interface TrackListener {
    void onPlayTrack(int position, List<Track> tracks);

    void onAddToNowPlaying(Track track);

    void onAddToFavorite(Track track);

    void downloadTrack(Track track);

    void onAddToNextUp(Track track);

    void onRemoveTrackFromFavorite(Track track);
}
