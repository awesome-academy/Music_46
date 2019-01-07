package com.example.themo.musicmarvelous.service;

import android.media.MediaPlayer;

import com.example.themo.musicmarvelous.constants.LoopMode;
import com.example.themo.musicmarvelous.constants.ShuffleMode;
import com.example.themo.musicmarvelous.constants.State;
import com.example.themo.musicmarvelous.data.model.Track;

import java.util.List;

public class TrackPlayerController implements ManagerTrackPlayer {

    private static final long DELAY_MILLIS = 500;
    @State
    private int mState;
    @LoopMode
    private int mLoopMode = LoopMode.NO_LOOP;
    @ShuffleMode
    private int mShuffleMode;

    private MediaPlayer mMediaPlayer;
    private TrackPlayerService mTrackPlayerService;
    private TrackInfoListener mInfoListener;
    private int mCurrentTrackPosition;
    private List<Track> mTracks;
    private List<Track> mOriginalTracks;


    public TrackPlayerController(TrackPlayerService trackPlayerService) {
        mTrackPlayerService = trackPlayerService;
    }

    private final MediaPlayer.OnErrorListener mErrorListener = new MediaPlayer.OnErrorListener() {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {
            return true;
        }
    };

    private final MediaPlayer.OnCompletionListener mCompletion = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {

        }
    };

    @Override
    public int getCurrentState() {
        return mState;
    }

    @Override
    public void playNextTrack() {

    }

    @Override
    public void playPreviousTrack() {

    }

    @Override
    public void changeTrackState() {

    }

    @Override
    public void release() {

    }

    @Override
    public void seekTo(int percent) {

    }

    @Override
    public void setTrackInfoListener(TrackInfoListener listener) {
        mInfoListener = listener;
    }

    @Override
    public Track getCurrentTrack() {
        return null;
    }

    @Override
    public int getCurrentTrackPosition() {
        return 0;
    }

    @Override
    public List<Track> getListTracks() {
        return null;
    }

    @Override
    public void playTrackAtPosition(int position, Track... tracks) {

    }

    @Override
    public void addToNextUp(Track track) {

    }

    @Override
    public int getLoopMode() {
        return mLoopMode;
    }

    @Override
    public int getShuffleMode() {
        return mShuffleMode;
    }

    @Override
    public void changeLoopType() {

    }

    @Override
    public void changeShuffleState() {

    }

    @Override
    public int getCurrentPosition() {
        return 0;
    }

    @Override
    public int getDuration() {
        return 0;
    }

    public interface TrackInfoListener {

        void onTrackChanged(Track track);

        void onStateChanged(@State int state);

        void onLoopChanged(@LoopMode int loopType);

        void onShuffleChanged(@ShuffleMode int shuffleMode);
    }
}
