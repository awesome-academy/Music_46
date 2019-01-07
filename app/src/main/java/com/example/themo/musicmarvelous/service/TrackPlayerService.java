package com.example.themo.musicmarvelous.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.themo.musicmarvelous.constants.LoopMode;
import com.example.themo.musicmarvelous.constants.ShuffleMode;
import com.example.themo.musicmarvelous.constants.State;
import com.example.themo.musicmarvelous.data.model.Track;

import java.util.List;

public class TrackPlayerService extends Service {

    public static final String ACTION_CHANGE_STATE = "ACTION_CHANGE_STATE";
    public static final String ACTION_NEXT_TRACK = "ACTION_NEXT_TRACK";
    public static final String ACTION_PREVIOUS_TRACK = "ACTION_PREVIOUS_TRACK";
    public static final String ACTION_OPEN_PLAY_TRACK_ACTIVITY = "ACTION_OPEN_PLAY_TRACK_ACTIVITY";
    public static final int SECONDS_FACTOR = 1000;

    private ManagerTrackPlayer mTrackPlayerManager;
    private final IBinder mBinder = new LocalBinder();


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleIntent(intent);
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void handleIntent(Intent intent) {
        if (intent == null || intent.getAction() == null) return;
        switch (intent.getAction()) {
            case ACTION_CHANGE_STATE:
                if (getMediaState() != State.PREPARE) {
                    changeTrackState();
                }
                break;
            case ACTION_PREVIOUS_TRACK:
                playPrevious();
                break;
            case ACTION_NEXT_TRACK:
                playNext();
                break;
            default:
                break;
        }
    }

    public Track getCurrentTrack() {
        return mTrackPlayerManager != null ? mTrackPlayerManager.getCurrentTrack() : null;
    }

    public void setTrackInfoListener(TrackPlayerController.TrackInfoListener listener) {
        if (mTrackPlayerManager == null) return;
        mTrackPlayerManager.setTrackInfoListener(listener);
    }

    public void actionSeekTo(int userSelectedPosition) {
        if (mTrackPlayerManager != null) {
            mTrackPlayerManager.seekTo(userSelectedPosition);
        }
    }

    public void changeTrackState() {
        if (mTrackPlayerManager != null) {
            mTrackPlayerManager.changeTrackState();
        }
    }

    public void playNext() {
        if (mTrackPlayerManager != null) {
            mTrackPlayerManager.playNextTrack();
        }
    }

    public void playPrevious() {
        if (mTrackPlayerManager != null) {
            mTrackPlayerManager.playPreviousTrack();
        }
    }

    public int getMediaState() {
        if (mTrackPlayerManager == null) return State.INVALID;
        return mTrackPlayerManager.getCurrentState();
    }

    public List<Track> getListTrack() {
        if (mTrackPlayerManager == null) return null;
        return mTrackPlayerManager.getListTracks();
    }

    public void playTrackAtPosition(int position, Track... tracks) {
        if (mTrackPlayerManager == null) {
            mTrackPlayerManager = new TrackPlayerController(this);
        }
        mTrackPlayerManager.playTrackAtPosition(position, tracks);
    }

    public void addToNextUp(Track track) {
        if (mTrackPlayerManager != null) {
            mTrackPlayerManager.addToNextUp(track);
        }
    }

    public int getCurrentTrackPosition() {
        if (mTrackPlayerManager == null) return 0;
        return mTrackPlayerManager.getCurrentTrackPosition();
    }

    public void changeLoopType() {
        if (mTrackPlayerManager != null) {
            mTrackPlayerManager.changeLoopType();
        }
    }

    @LoopMode
    public int getLoopMode() {
        if (mTrackPlayerManager == null) return LoopMode.NO_LOOP;
        return mTrackPlayerManager.getLoopMode();
    }

    public void changeShuffleState() {
        if (mTrackPlayerManager != null) {
            mTrackPlayerManager.changeShuffleState();
        }
    }

    public int getShuffleMode() {
        if (mTrackPlayerManager != null) {
            return mTrackPlayerManager.getShuffleMode();
        }
        return ShuffleMode.OFF;
    }

    public int getCurrentPosition() {
        if (mTrackPlayerManager != null) {
            return mTrackPlayerManager.getCurrentPosition();
        }
        return 0;
    }

    public int getDuration() {
        if (mTrackPlayerManager != null) {
            return mTrackPlayerManager.getDuration();
        }
        return 0;
    }

    public class LocalBinder extends Binder {
        public TrackPlayerService getService() {
            return TrackPlayerService.this;
        }
    }
}
