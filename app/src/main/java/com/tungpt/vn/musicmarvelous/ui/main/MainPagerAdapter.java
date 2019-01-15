package com.tungpt.vn.musicmarvelous.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.tungpt.vn.musicmarvelous.constants.TabEntity;
import com.tungpt.vn.musicmarvelous.ui.main.download.DownloadFragment;
import com.tungpt.vn.musicmarvelous.ui.main.home.HomeFragment;
import com.tungpt.vn.musicmarvelous.ui.main.personal.PersonalFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final int TAB_COUNT = 3;
    private DownloadFragment mDownloadFragment;
    private PersonalFragment mmPersonalFragment;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case TabEntity.TAB_HOME:
                return HomeFragment.newInstance();
            case TabEntity.TAB_LIBRARY:
                mmPersonalFragment = new PersonalFragment();
                return mmPersonalFragment;
            case TabEntity.TAB_DOWNLOAD:
                mDownloadFragment = new DownloadFragment();
                return mDownloadFragment;
            default:
                return HomeFragment.newInstance();
        }
    }

    public void updateFavoriteData() {

    }

    public void updateDownloadData() {
        mDownloadFragment.refresh();
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }
}
