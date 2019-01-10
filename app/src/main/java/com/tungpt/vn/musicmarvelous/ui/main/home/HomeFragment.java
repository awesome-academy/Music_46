package com.tungpt.vn.musicmarvelous.ui.main.home;

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

import com.tungpt.vn.musicmarvelous.R;
import com.tungpt.vn.musicmarvelous.data.model.Genre;
import com.tungpt.vn.musicmarvelous.data.repository.GenreRepository;
import com.tungpt.vn.musicmarvelous.data.source.GenresLocalDataSource;
import com.tungpt.vn.musicmarvelous.ui.main.genredetail.GenreDetailFragment;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View, GenreAdapter.OnGenreClickListener {
    private Context mContext;
    private HomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;
    private GenreAdapter mGenreAdapter;

    public HomeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getContext();
        mRecyclerView = view.findViewById(R.id.recycler_genre);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mGenreAdapter = new GenreAdapter(null, mContext);
        mRecyclerView.setAdapter(mGenreAdapter);
        mGenreAdapter.setOnGenreClickListener(this);
        mPresenter = new HomePresenter(this,
                GenreRepository.getInstance(new GenresLocalDataSource()));
        mPresenter.loadGenres();
        return view;
    }

    public static HomeFragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        return homeFragment;
    }

    private void gotoDetailFragment(String genre) {
        GenreDetailFragment detailFragment = GenreDetailFragment.newInstance(genre);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_genre_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void showGenres(List<Genre> genres) {
        mGenreAdapter.setGenres(genres);
    }

    @Override
    public void showSongsByGenre() {

    }

    @Override
    public void onGenreClicked(String genre) {
        gotoDetailFragment(genre);
    }
}
