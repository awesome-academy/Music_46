package com.tungpt.vn.musicmarvelous.ui.main.search;

import android.content.Context;
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

import com.tungpt.vn.musicmarvelous.R;
import com.tungpt.vn.musicmarvelous.data.model.Track;
import com.tungpt.vn.musicmarvelous.ui.main.TrackListener;
import com.tungpt.vn.musicmarvelous.ui.main.genredetail.GenreDetailAdapter;
import com.tungpt.vn.musicmarvelous.utils.ScrollListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements SearchContract.View  {

    private SearchContract.Presenter mPresenter;
    private GenreDetailAdapter mAdapter;
    private TrackListener mTrackListener;
    private RecyclerView mRecyclerSearch;
    private ProgressBar mProgressLoading;
    private String mQuery;

    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        SearchFragment genreDetailFragment = new SearchFragment();
        return genreDetailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupComponents(View view) {
        mProgressLoading = view.findViewById(R.id.progress_loading);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new GenreDetailAdapter(mTrackListener, getContext());
        mRecyclerSearch = view.findViewById(R.id.recycler_genre_detail);
        mRecyclerSearch.setLayoutManager(linearLayoutManager);
        mRecyclerSearch.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerSearch.setAdapter(mAdapter);
        mRecyclerSearch.addOnScrollListener(new ScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int offset) {
                mPresenter.searchTracks(mQuery, offset);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TrackListener) {
            mTrackListener = (TrackListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mTrackListener = null;
    }

    @Override
    public void showNoTrackAvailable() {
        Toast.makeText(getContext(), R.string.msg_no_track_available, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTracks(ArrayList<Track> trackList) {
        mAdapter.updateListTrack(trackList);
    }

    @Override
    public void showLoadingIndicator() {
        mProgressLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingIndicator() {
        mProgressLoading.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingTrackError(String err) {

    }

    @Override
    public void setPresenter(SearchContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    public void submitQueryText(String query) {
        mAdapter.clearData();
        mPresenter.searchTracks(query, 0);
    }
}
