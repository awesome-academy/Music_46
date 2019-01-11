package com.tungpt.vn.musicmarvelous.ui.main.search;

import com.tungpt.vn.musicmarvelous.data.model.Track;
import com.tungpt.vn.musicmarvelous.data.repository.TrackRepository;
import com.tungpt.vn.musicmarvelous.data.source.TrackDataSource;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter, TrackDataSource.OnFetchDataListener<Track> {

    private SearchContract.View mView;

    @Override
    public void start() {
        mView.setPresenter(this);
    }

    @Override
    public void onFetchDataSuccess(List<Track> data) {

    }

    @Override
    public void onFetchDataFailure(Exception e) {

    }

    @Override
    public void searchTracks(String name, int offset) {
        mView.showLoadingIndicator();
        TrackRepository.getInstance().searchTracksOnline(name, offset, this);

    }
}
