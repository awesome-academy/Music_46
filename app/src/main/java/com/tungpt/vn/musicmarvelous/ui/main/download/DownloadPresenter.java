package com.tungpt.vn.musicmarvelous.ui.main.download;

import com.tungpt.vn.musicmarvelous.constants.Constants;
import com.tungpt.vn.musicmarvelous.data.model.Track;
import com.tungpt.vn.musicmarvelous.data.repository.TrackRepository;
import com.tungpt.vn.musicmarvelous.data.source.TrackDataSource;

import java.util.List;

public class DownloadPresenter implements DownloadContract.Presenter,
        TrackDataSource.OnFetchDataListener<Track> {

    private DownloadContract.View mView;
    private TrackRepository mTrackRepository;

    public DownloadPresenter(DownloadContract.View view) {
        mView = view;
        mTrackRepository = TrackRepository.getInstance();
    }

    @Override
    public void getOfflineTracks() {
        mTrackRepository.getOfflineTracksInFolder(Constants.DOWNLOAD_DIRECTORY, this);
    }

    @Override
    public void onFetchDataSuccess(List<Track> data) {
        if (data == null || data.isEmpty()) {
            mView.showNoTrackInDevice();
        } else {
            mView.showTracks(data);
        }
    }

    @Override
    public void onFetchDataFailure(Exception e) {
        mView.showError(e.getMessage());
    }
}
