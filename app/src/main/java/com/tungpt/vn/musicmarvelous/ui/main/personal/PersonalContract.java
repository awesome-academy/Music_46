package com.tungpt.vn.musicmarvelous.ui.main.personal;

public interface PersonalContract {
    interface Presenter {
        void getOfflineTrackCount();

        void getFavoriteTrackCount();

        void getDownloadTrackCount();
    }

    interface View {
        void showOfflineTrackCount(int count);

        void showFavoriteTrackCount(int count);

        void showDownloadTrackCount(int count);
        
        void goToFavoriteTrackFragment();

        void goToListTrackOfflineFragment();

        void goToDownloadTrackFragment();
    }
}
