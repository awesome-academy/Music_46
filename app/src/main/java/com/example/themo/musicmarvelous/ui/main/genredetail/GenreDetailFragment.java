package com.example.themo.musicmarvelous.ui.main.genredetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.themo.musicmarvelous.R;
import com.example.themo.musicmarvelous.constants.Constants;
import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.data.repository.TrackRepository;
import com.example.themo.musicmarvelous.ui.main.TrackListener;

import java.util.ArrayList;
import java.util.List;

public class GenreDetailFragment extends Fragment implements GenreDetailContract.View {
    private GenreDetailContract.Presenter mPresenter;
    private GenreDetailAdapter mAdapter;
    private TrackListener mTrackListener;
    private List<Track> mTracks;
    private RecyclerView mRecyclerTracks;
    private ProgressBar mProgressLoading;
    private String mGenre;

    public GenreDetailFragment() {
    }

    public static GenreDetailFragment newInstance(String genre) {
        GenreDetailFragment genreDetailFragment = new GenreDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.GENRE_TYPE, genre);
        genreDetailFragment.setArguments(bundle);
        return genreDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_genre_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view);
        mPresenter = new GenreDetailPresenter(this, TrackRepository.getInstance());
        mGenre = getArguments().getString(Constants.GENRE_TYPE);
        mPresenter.loadTracks(mGenre, Constants.LIMIT_DEFAULT, Constants.OFFSET_DEFAULT);

    }

    private void initComponents(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new GenreDetailAdapter(mTrackListener, getContext());
        mRecyclerTracks = view.findViewById(R.id.recycler_genre_detail);
        mRecyclerTracks.setLayoutManager(linearLayoutManager);
        mRecyclerTracks.setHasFixedSize(false);
        mRecyclerTracks.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerTracks.setAdapter(mAdapter);
    }

    @Override
    public void showTrack(ArrayList<Track> tracks) {
        mAdapter.updateListTrack(tracks);
    }

    @Override
    public void showNoTrackAvailable() {
        Toast.makeText(getContext(), R.string.msg_no_track_available, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingTracksError(String error) {

    }

    @Override
    public void setPresenter(GenreDetailContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
