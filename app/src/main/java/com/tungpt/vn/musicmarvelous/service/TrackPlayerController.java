package com.tungpt.vn.musicmarvelous.service;

import android.media.MediaPlayer;

import com.tungpt.vn.musicmarvelous.constants.LoopMode;
import com.tungpt.vn.musicmarvelous.constants.ShuffleMode;
import com.tungpt.vn.musicmarvelous.constants.State;
import com.tungpt.vn.musicmarvelous.data.model.Track;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrackPlayerController implements ManagerTrackPlayer {

    private static final long DELAY_MILLIS = 500;
    private static final int HUNDRED = 100;
    private static final int CHANGED_POSITION = 1;
    private static final int ZERO = 0;

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
            notifyStateChanged(State.PAUSE);
        }
    };

    private final MediaPlayer.OnPreparedListener mOnPrepared = new MediaPlayer.OnPreparedListener() {
        @Override
        public void onPrepared(MediaPlayer mp) {
            mMediaPlayer.start();
            notifyStateChanged(State.PLAYING);
        }
    };

    @Override
    public int getCurrentState() {
        return mState;
    }

    @Override
    public void playNextTrack() {
        if (mCurrentTrackPosition < mTracks.size() - CHANGED_POSITION) {
            mCurrentTrackPosition++;
            prepareTrack();
        }
    }

    @Override
    public void playPreviousTrack() {
        if (mCurrentTrackPosition > ZERO) {
            mCurrentTrackPosition--;
            prepareTrack();
        }
    }

    @Override
    public void changeTrackState() {
        if (mMediaPlayer == null) return;
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            notifyStateChanged(State.PAUSE);
        } else {
            mMediaPlayer.start();
            notifyStateChanged(State.PLAYING);
            // TODO
        }
    }

    @Override
    public void release() {
        if (mMediaPlayer == null) return;
        mMediaPlayer.release();
        mMediaPlayer = null;
    }

    @Override
    public void seekTo(int percent) {
        if (mState == State.PAUSE || mState == State.PLAYING) {
            mMediaPlayer.seekTo(mMediaPlayer.getDuration() / HUNDRED * percent);
        }
    }

    @Override
    public void setTrackInfoListener(TrackInfoListener listener) {
        mInfoListener = listener;
    }

    @Override
    public Track getCurrentTrack() {
        return mTracks == null ? null : mTracks.get(mCurrentTrackPosition);
    }

    @Override
    public int getCurrentTrackPosition() {
        return mCurrentTrackPosition;
    }

    @Override
    public List<Track> getListTracks() {
        return mTracks;
    }

    @Override
    public void playTrackAtPosition(int position, Track... tracks) {
        if (tracks == null && mTracks == null) {
            notifyStateChanged(State.INVALID);
            return;
        }
        if ((tracks == null || tracks.length == 0) && mCurrentTrackPosition == position) return;
        if (tracks != null && tracks.length != 0) {
            mTracks = new ArrayList<>();
            Collections.addAll(mTracks, tracks);
        }
        mCurrentTrackPosition = position;
        prepareTrack();
    }

    @Override
    public void addToNextUp(Track track) {
        if (mTracks == null || mTracks.isEmpty()) return;
        mTracks.add(track);
        if (mShuffleMode == ShuffleMode.ON) {
            mOriginalTracks.add(track);
        }
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
        if (mMediaPlayer == null) return 0;
        return mMediaPlayer.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        if (mMediaPlayer == null) return 0;
        return mMediaPlayer.getDuration();
    }

    private void prepareTrack() {
        if (mTracks == null || mTracks.isEmpty()) {
            notifyStateChanged(State.INVALID);
            return;
        }
        release();
        notifyStateChanged(State.PREPARE);
        if (mInfoListener == null) return;
        mInfoListener.onTrackChanged(mTracks.get(mCurrentTrackPosition));
    }

    private void notifyStateChanged(@State int state) {
        mState = state;
        if (mTrackPlayerService != null) {
            if (state == State.PREPARE) mTrackPlayerService.loadImage();
            mTrackPlayerService.createNotification(state);
        }
        if (mInfoListener == null) return;
        mInfoListener.onStateChanged(mState);
    }

    public interface TrackInfoListener {

        void onTrackChanged(Track track);

        void onStateChanged(@State int state);

        void onLoopChanged(@LoopMode int loopType);

        void onShuffleChanged(@ShuffleMode int shuffleMode);
    }
}
