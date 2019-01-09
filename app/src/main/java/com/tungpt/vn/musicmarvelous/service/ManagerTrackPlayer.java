package com.example.themo.musicmarvelous.service;

import com.example.themo.musicmarvelous.constants.LoopMode;
import com.example.themo.musicmarvelous.constants.ShuffleMode;
import com.example.themo.musicmarvelous.data.model.Track;

import java.util.List;

public interface ManagerTrackPlayer {

    int getCurrentState();

    void playNextTrack();

    void playPreviousTrack();

    void changeTrackState();

    void release();

    void seekTo(int percent);

    void setTrackInfoListener(TrackPlayerController.TrackInfoListener listener);

    Track getCurrentTrack();

    int getCurrentTrackPosition();

    List<Track> getListTracks();

    void playTrackAtPosition(int position, Track... tracks);

    void addToNextUp(Track track);

    @LoopMode
    int getLoopMode();

    @ShuffleMode
    int getShuffleMode();

    void changeLoopType();

    void changeShuffleState();

    int getCurrentPosition();

    int getDuration();

}
