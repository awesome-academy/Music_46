package com.tungpt.vn.musicmarvelous.ui.main.download;

import com.tungpt.vn.musicmarvelous.data.model.Track;

import java.util.List;

public interface DownloadContract {
    interface Presenter {
        void getOfflineTracks();
    }

    interface View {
        void showTracks(List<Track> tracks);

        void showNoTrackInDevice();

        void showError(String error);
    }
}
