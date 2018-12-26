package com.example.themo.musicmarvelous.ui.main.home;

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
import com.example.themo.musicmarvelous.data.model.Genre;
import com.example.themo.musicmarvelous.data.repository.GenreRepository;
import com.example.themo.musicmarvelous.data.source.GenresLocalDataSource;

import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View {
    private Context mContext;
    private HomeContract.Presenter mPresenter;
    private RecyclerView mRecyclerView;

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

        mPresenter = new HomePresenter(this,
                GenreRepository.getInstance(new GenresLocalDataSource()));
        mPresenter.loadGenres();
        return view;
    }

    @Override
    public void showGenres(List<Genre> genres) {
        GenreAdapter adapter = new GenreAdapter(genres, mContext);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showSongsByGenre() {

    }
}
