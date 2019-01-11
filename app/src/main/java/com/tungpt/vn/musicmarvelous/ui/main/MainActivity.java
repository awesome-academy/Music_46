package com.tungpt.vn.musicmarvelous.ui.main;

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
import android.widget.SearchView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tungpt.vn.musicmarvelous.R;
import com.tungpt.vn.musicmarvelous.constants.Constants;
import com.tungpt.vn.musicmarvelous.constants.State;
import com.tungpt.vn.musicmarvelous.data.model.Track;
import com.tungpt.vn.musicmarvelous.service.TrackPlayerController;
import com.tungpt.vn.musicmarvelous.service.TrackPlayerService;
import com.tungpt.vn.musicmarvelous.ui.main.home.HomeFragment;
import com.tungpt.vn.musicmarvelous.ui.main.personal.PersonalFragment;
import com.tungpt.vn.musicmarvelous.ui.main.play.PlayTrackActivity;
import com.tungpt.vn.musicmarvelous.ui.main.search.SearchFragment;

public class MainActivity extends AppCompatActivity implements MainContract.View, View.OnClickListener,
        BottomNavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {
    private MainContract.Presenter mPresenter;
    private ActionBar mActionBar;
    private ConstraintLayout mConstraintBottomControl;
    private TrackPlayerService mTrackPlayerService;
    private boolean mIsBounded;
    private ImageView mImageViewTrack;
    private TextView mTextViewTitle;
    private TextView mTextViewArtist;
    private ImageView mImageViewState;
    private ImageView mImageViewPrevious;
    private ImageView mImageViewNext;
    private Track mTrack;
    private ProgressBar mProgressBar;
    private SearchFragment mSearchFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        initComponent();
        initView();
        setupEvents();
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

    public void initComponent() {
        mImageViewState = findViewById(R.id.image_control_play_pause);
        mImageViewNext = findViewById(R.id.image_control_next);
        mImageViewPrevious = findViewById(R.id.image_control_previous);
        mImageViewTrack = findViewById(R.id.image_control_track);
        mTextViewTitle = findViewById(R.id.text_control_title);
        mTextViewArtist = findViewById(R.id.text_control_artist);
        mConstraintBottomControl = findViewById(R.id.constraint_bottom_control);

    }

    private void setupEvents() {
        mImageViewState.setOnClickListener(this);
        mImageViewPrevious.setOnClickListener(this);
        mImageViewNext.setOnClickListener(this);
        mConstraintBottomControl.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchMenu = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchMenu.getActionView();
        searchView.setQueryHint(getString(R.string.search_hint));
        searchView.setOnQueryTextListener(this);
        searchMenu.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                openSearchFragment();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                closeSearchFragment();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
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
        switch (state) {
            case State.PREPARE:
                showLoading();
                break;
            case State.PLAYING:
                hideLoading();
                mImageViewState.setImageDrawable(getResources()
                        .getDrawable(R.drawable.ic_pause_circle_filled_black));
                break;
            case State.PAUSE:
                hideLoading();
                mImageViewState.setImageDrawable(getResources()
                        .getDrawable(R.drawable.ic_play_circle_filled_black));
                break;
            default:
                break;
        }
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        if (mSearchFragment == null) return false;
        mSearchFragment.submitQueryText(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    private void openSearchFragment() {
        mSearchFragment = SearchFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frame_container, mSearchFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void closeSearchFragment() {
        getSupportFragmentManager().popBackStack();
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
