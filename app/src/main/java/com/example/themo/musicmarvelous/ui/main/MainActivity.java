package com.example.themo.musicmarvelous.ui.main;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.themo.musicmarvelous.R;
import com.example.themo.musicmarvelous.constants.Constants;
import com.example.themo.musicmarvelous.constants.State;
import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.service.TrackPlayerController;
import com.example.themo.musicmarvelous.service.TrackPlayerService;
import com.example.themo.musicmarvelous.ui.main.home.HomeFragment;
import com.example.themo.musicmarvelous.ui.main.personal.PersonalFragment;
import com.example.themo.musicmarvelous.ui.main.play.PlayTrackActivity;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener {
    private MainContract.Presenter mPresenter;
    private ActionBar mActionBar;
    private ConstraintLayout mConstraintBottomControl;
    private TrackPlayerService mTrackPlayerService;
    private boolean mIsBounded;
    private ImageView mImageViewTrack;
    private TextView mTextViewTitle;
    private TextView mTextViewArtist;
    private Track mTrack;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(new Intent(this, TrackPlayerService.class),
                mConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mIsBounded = true;
            mTrackPlayerService = ((TrackPlayerService.LocalBinder) service).getService();
            mTrackPlayerService.setTrackInfoListener(new TrackPlayerController.TrackInfoListener() {
                @Override
                public void onTrackChanged(Track track) {
                    updateTrackInfo(track);
                }

                @Override
                public void onStateChanged(int state) {
                    updateStateInfo(state);
                }

                @Override
                public void onLoopChanged(int loopType) {
                }

                @Override
                public void onShuffleChanged(int shuffleMode) {
                }
            });
            updateStateInfo(mTrackPlayerService.getMediaState());
            updateTrackInfo(mTrackPlayerService.getCurrentTrack());
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mIsBounded = false;
            mTrackPlayerService.setTrackInfoListener(null);
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mConnection);
        mIsBounded = false;
    }

    @Override
    protected void onDestroy() {
        if (mTrackPlayerService != null) mTrackPlayerService.setTrackInfoListener(null);
        super.onDestroy();
    }

    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (MainContract.Presenter) presenter;
    }

    public void initView() {
        mActionBar = getSupportActionBar();
        loadFragment(new HomeFragment());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                mActionBar.setTitle(R.string.title_home);
                loadFragment(HomeFragment.newInstance());
                return true;
            case R.id.navigation_music:
                mActionBar.setTitle(R.string.title_library_music);
                loadFragment(PersonalFragment.newInstance());
                return true;
            case R.id.navigation_settings:
                mActionBar.setTitle(R.string.title_settings);
                return true;
            default:
                mActionBar.setTitle(R.string.title_home);
                loadFragment(HomeFragment.newInstance());
                break;
        }
        return false;
    }


    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateStateInfo(int state) {

    }

    @Override
    public void updateTrackInfo(Track track) {
        if (track == null) return;
        mConstraintBottomControl.setVisibility(View.VISIBLE);
        if (track == mTrack) return;
        Glide.with(this).load(track.getArtworkUrl())
                .apply(new RequestOptions()
                        .override(Constants.DEFAULT_ITEM_SIZE)
                        .centerCrop()
                        .error(R.drawable.logo_app))
                .into(mImageViewTrack);
        mTextViewTitle.setText(track.getTitle());
        mTextViewArtist.setText(track.getPublisherAlbumTitle());
        mTrack = track;
    }

    @Override
    public void showAddToFavoriteSuccess() {

    }

    @Override
    public void showAddToFavoriteError() {

    }

    @Override
    public void showRemoveFromFavoriteSuccess() {

    }

    @Override
    public void showRemoveFromFavoriteError() {

    }

    private void gotoPlayTrackActivity() {
        startActivity(PlayTrackActivity.getIntent(this));
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}
