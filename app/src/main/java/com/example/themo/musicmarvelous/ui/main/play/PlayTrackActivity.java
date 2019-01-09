package com.example.themo.musicmarvelous.ui.main.play;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.themo.musicmarvelous.R;
import com.example.themo.musicmarvelous.constants.LoopMode;
import com.example.themo.musicmarvelous.constants.ShuffleMode;
import com.example.themo.musicmarvelous.constants.State;
import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.repository.TrackRepository;
import com.example.themo.musicmarvelous.data.source.TrackDataSource;
import com.example.themo.musicmarvelous.service.TrackPlayerController;
import com.example.themo.musicmarvelous.service.TrackPlayerService;
import com.example.themo.musicmarvelous.utils.StringUtil;

public class PlayTrackActivity extends AppCompatActivity implements PlayTrackContract.View,
        View.OnClickListener, TrackPlayerController.TrackInfoListener {

    private static final long DELAY_MILLIS = 500;
    private static final int HUNDRED = 100;
    private ImageView mImageStated;
    private ImageView mImagePrevious;
    private ImageView mImageNext;
    private ImageView mImageLoop;
    private ImageView mImageShuffle;
    private TextView mTextViewStart;
    private TextView mTextViewEnd;
    private SeekBar mSeekBar;
    private ImageView mImageTimer;
    private ImageView mImageFavorite;
    private ImageView mImageDownload;
    private ImageView mImageDescription;
    private ProgressBar mProgressLoading;
    private ImageView mImageTrack;
    private Track mCurrentTrack;
    private int mUserSelectedPosition = 0;
    private boolean mIsBound;
    private TrackPlayerService mTrackPlayerService;
    private PlayTrackContract.Presenter mPresenter;
    private ImageView mImageNextUp;
    private ImageView mImageClose;
    private ImageView mImageBackground;
    private TextView mTextViewTitle;
    private TextView mTextViewArtist;
    private TrackRepository mTrackRepository;
    private int mDisplayWidth;
    private int mDisplayHeight;

    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_track);
        mPresenter = new PlayTrackPresenter(this);
        initComponents();
        setupEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent trackIntent = new Intent(this, TrackPlayerService.class);
        bindService(trackIntent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
        mIsBound = false;
    }

    public static Intent getIntent(Context context){
        Intent intent = new Intent(context, PlayTrackActivity.class);
        return intent;
    }

    @Override
    public void onClick(View v) {
        if (mTrackPlayerService == null) return;
        switch (v.getId()) {
            case R.id.image_track_state:
                mTrackPlayerService.changeTrackState();
                break;
            case R.id.image_track_previous:
                mTrackPlayerService.playPrevious();
                break;
            case R.id.image_track_next:
                mTrackPlayerService.playNext();
                break;
            case R.id.image_loop:
                mTrackPlayerService.changeLoopType();
                break;
            case R.id.image_shuffle:
                mTrackPlayerService.changeShuffleState();
                break;
            case R.id.image_equalizer:
                break;
            case R.id.image_favorite:
                handleAddToFavorite();
                break;
            case R.id.image_download:
                handleDownload();
                break;
            case R.id.image_description:
                handleShowDescription();
                break;
            case R.id.image_next_up:
                handleShowNextUp();
                break;
            case R.id.image_close:
                onBackPressed();
                break;
            default:
                break;
        }
    }

    private void handleDownload() {
    }

    private void handleShowDescription() {
        Toast.makeText(PlayTrackActivity.this, mCurrentTrack.getDescription(), Toast.LENGTH_SHORT).show();
    }

    private void handleShowNextUp() {
    }

    private void handleAddToFavorite() {
        if (mCurrentTrack == null) return;
        mTrackRepository = TrackRepository.getInstance();
        if (!mTrackRepository.isTrackInFavorite(mCurrentTrack)) {
            mTrackRepository.addTrackToFavorite(mCurrentTrack,
                    new TrackDataSource.OnQueryDatabaseListener() {
                        @Override
                        public void onQuerySuccess(String msg) {
                            mImageFavorite.setImageResource(R.drawable.ic_favorite_filled);
                            Toast.makeText(PlayTrackActivity.this,
                                    R.string.msg_added, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onQueryFailure(Exception e) {
                            mImageFavorite.setImageResource(R.drawable.ic_favorite_border);
                            Toast.makeText(PlayTrackActivity.this,
                                    R.string.error, Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            mTrackRepository.deleteTrackFavorite(mCurrentTrack,
                    new TrackDataSource.OnQueryDatabaseListener() {
                        @Override
                        public void onQuerySuccess(String msg) {
                            mImageFavorite.setImageResource(R.drawable.ic_favorite_border);
                            Toast.makeText(PlayTrackActivity.this,
                                    R.string.msg_removed, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onQueryFailure(Exception e) {
                            mImageFavorite.setImageResource(R.drawable.ic_favorite_filled);
                            Toast.makeText(PlayTrackActivity.this,
                                    R.string.error, Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    @Override
    public void setPresenter(PlayTrackContract.Presenter presenter) {

    }

    @Override
    public void onTrackChanged(Track track) {
        mCurrentTrack = track;
        updateTrackInfo();
    }

    @Override
    public void onStateChanged(int state) {
        updateStateInfo(state);

    }

    @Override
    public void onLoopChanged(int loopMode) {
        updateLoopMode(loopMode);

    }

    @Override
    public void onShuffleChanged(int shuffleMode) {
        updateShuffleMode(shuffleMode);

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            mIsBound = true;
            mTrackPlayerService = ((TrackPlayerService.LocalBinder) service).getService();
            mTrackPlayerService.setTrackInfoListener(PlayTrackActivity.this);
            mCurrentTrack = mTrackPlayerService.getCurrentTrack();

            updateTrackInfo();
            updateStateInfo(mTrackPlayerService.getMediaState());
            updateLoopMode(mTrackPlayerService.getLoopMode());
            updateShuffleMode(mTrackPlayerService.getShuffleMode());
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mIsBound = false;
           mTrackPlayerService.setTrackInfoListener(null);
        }
    };

    private void updateTrackInfo() {
    }

    private void initComponents() {

        mImageStated = findViewById(R.id.image_track_state);
        mImagePrevious = findViewById(R.id.image_track_previous);
        mImageNext = findViewById(R.id.image_track_next);
        mImageLoop = findViewById(R.id.image_loop);
        mImageShuffle = findViewById(R.id.image_shuffle);
        mTextViewStart = findViewById(R.id.text_track_start);
        mTextViewEnd = findViewById(R.id.text_track_end);
        mSeekBar = findViewById(R.id.seek_bar_play_track);
        mImageTimer = findViewById(R.id.image_equalizer);
        mImageFavorite = findViewById(R.id.image_favorite);
        mImageDownload = findViewById(R.id.image_download);
        mImageDescription = findViewById(R.id.image_description);
        mImageTrack = findViewById(R.id.image_track);
        mProgressLoading = findViewById(R.id.progress_loading);
        mImageNextUp = findViewById(R.id.image_next_up);
        mImageClose = findViewById(R.id.image_close);
        mImageBackground = findViewById(R.id.image_background);
        mTextViewTitle = findViewById(R.id.text_track_title);
        mTextViewArtist = findViewById(R.id.text_track_artist);

        mDisplayWidth = getResources().getDisplayMetrics().widthPixels;
        mDisplayHeight = getResources().getDisplayMetrics().heightPixels;
    }

    private void setupEvents() {

        mImageStated.setOnClickListener(this);
        mImagePrevious.setOnClickListener(this);
        mImageNext.setOnClickListener(this);
        mImageLoop.setOnClickListener(this);
        mImageShuffle.setOnClickListener(this);
        mImageTimer.setOnClickListener(this);
        mImageFavorite.setOnClickListener(this);
        mImageDownload.setOnClickListener(this);
        mImageDescription.setOnClickListener(this);
        mImageNextUp.setOnClickListener(this);
        mImageClose.setOnClickListener(this);
        mSeekBar.setOnSeekBarChangeListener(mSeekBarChangeListener);
    }

    private SeekBar.OnSeekBarChangeListener mSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser) {
                mUserSelectedPosition = progress;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            if (mIsBound) {
                mTrackPlayerService.actionSeekTo(mUserSelectedPosition);
            }
        }
    };

    private void updateStateInfo(@State int state) {
        switch (state) {
            case State.PREPARE:
                showLoading();
                stopUpdate();
                break;
            case State.PLAYING:
                hideLoading();
                mImageStated.setImageDrawable(getResources()
                        .getDrawable(R.drawable.ic_pause_circle_filled_black));
                updateSeekBar();
                break;
            case State.PAUSE:
                hideLoading();
                mImageStated.setImageDrawable(getResources()
                        .getDrawable(R.drawable.ic_play_circle_filled_black));
                break;
            default:
                break;
        }
    }

    private void stopUpdate() {
        if (mHandler != null) {
            mHandler.removeCallbacks(mRunnable);
        }
    }

    private void updateSeekBar() {
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mSeekBar.setProgress((int) ((double) mTrackPlayerService.getCurrentPosition()
                        / mTrackPlayerService.getDuration() * HUNDRED));
                mTextViewStart.setText(StringUtil.convertMilisecondToTimer
                        (mTrackPlayerService.getCurrentPosition()));
                mHandler.postDelayed(this, DELAY_MILLIS);
            }
        };
        mHandler.post(mRunnable);
    }

    private void updateLoopMode(@LoopMode int loopType) {
        switch (loopType) {
            case LoopMode.NO_LOOP:
                mImageLoop.setImageResource(R.drawable.ic_repeat_none);
                break;
            case LoopMode.LOOP_ONE:
                mImageLoop.setImageResource(R.drawable.ic_repeat_one);
                break;
            case LoopMode.LOOP_LIST:
                mImageLoop.setImageResource(R.drawable.ic_repeat_all);
                break;
            default:
                mImageLoop.setImageResource(R.drawable.ic_repeat_none);
                break;
        }
    }

    private void updateShuffleMode(@ShuffleMode int mode) {
        switch (mode) {
            case ShuffleMode.OFF:
                mImageShuffle.setImageResource(R.drawable.ic_shuffle_white);
                break;
            case ShuffleMode.ON:
                mImageShuffle.setImageResource(R.drawable.ic_shuffle_black);
                break;
            default:
                mImageShuffle.setImageResource(R.drawable.ic_shuffle_white);
                break;
        }
    }
}
