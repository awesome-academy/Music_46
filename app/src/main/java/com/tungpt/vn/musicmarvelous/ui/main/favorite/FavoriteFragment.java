package com.example.themo.musicmarvelous.ui.main.favorite;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.themo.musicmarvelous.R;
import com.example.themo.musicmarvelous.data.model.Track;
import com.example.themo.musicmarvelous.ui.main.TrackListener;

import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteContract.View {

    private FavoriteContract.Presenter mPresenter;
    private TrackListener mTrackListener;
    private RecyclerView mRecyclerView;

    public FavoriteFragment() {

    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_library_favorite, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupComponents(view);
        mPresenter = new FavoritePresenter(this);
        mPresenter.getAllFavorites();
    }

    private void setupComponents(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_library_favorite);
    }

    @Override
    public void showFavorites(List<Track> tracks) {

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
}
