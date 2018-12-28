package com.example.themo.musicmarvelous.ui.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.themo.musicmarvelous.R;

public class PersonalFragment extends Fragment implements PersonalContract.View, View.OnClickListener {
    private static final int[] ITEM_ID = {R.id.image_track, R.id.image_favorite,
            R.id.image_download, R.id.image_favorite_detail,
            R.id.image_download_detail, R.id.image_track_detail,
            R.id.text_list_track, R.id.text_favorite,
            R.id.text_download};
    private PersonalContract.Presenter mPresenter;
    private TextView mTextViewListTrackCount;
    private TextView mTextViewFavoriteCount;
    private TextView mTextViewDownloadCount;

    public PersonalFragment() {
    }

    public static PersonalFragment newInstance() {
        PersonalFragment personalFragment = new PersonalFragment();
        return personalFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponent(view);
        mPresenter = new PersonalPresenter(this);
        mPresenter.getOfflineTrackCount();
        mPresenter.getFavoriteTrackCount();
        mPresenter.getDownloadTrackCount();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showOfflineTrackCount(int count) {

    }

    @Override
    public void showFavoriteTrackCount(int count) {

    }

    @Override
    public void showDownloadTrackCount(int count) {

    }

    @Override
    public void goToFavoriteTrackFragment() {

    }

    @Override
    public void goToListTrackOfflineFragment() {

    }

    @Override
    public void goToDownloadTrackFragment() {

    }

    private void initComponent(View view) {
        mTextViewListTrackCount = view.findViewById(R.id.text_list_track);
        mTextViewFavoriteCount = view.findViewById(R.id.text_favorite);
        mTextViewDownloadCount = view.findViewById(R.id.text_download);
        mTextViewListTrackCount.setOnClickListener(this);
        mTextViewFavoriteCount.setOnClickListener(this);
        mTextViewDownloadCount.setOnClickListener(this);
        for (int id : ITEM_ID) {
            view.findViewById(id).setOnClickListener(this);
        }
    }
}
