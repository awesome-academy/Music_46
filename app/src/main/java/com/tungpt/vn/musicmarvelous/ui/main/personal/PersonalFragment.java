package com.tungpt.vn.musicmarvelous.ui.main.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tungpt.vn.musicmarvelous.R;
import com.tungpt.vn.musicmarvelous.ui.main.favorite.FavoriteFragment;

public class PersonalFragment extends Fragment implements PersonalContract.View,
        View.OnClickListener {

    private PersonalContract.Presenter mPresenter;
    private LinearLayout mLinearLocal;
    private LinearLayout mLinearDownload;
    private LinearLayout mLinearFavorite;

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
        switch (v.getId()) {
            case R.id.linear_local:
                break;
            case R.id.linear_favorite:
                goToFavoriteTrackFragment();
                break;
            case R.id.linear_download:
                break;
            default:
                break;
        }
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
        FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                .beginTransaction();
        transaction.add(R.id.linear_personal, FavoriteFragment.newInstance());
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void goToListTrackOfflineFragment() {

    }

    @Override
    public void goToDownloadTrackFragment() {

    }

    private void initComponent(View view) {
        mLinearLocal = view.findViewById(R.id.linear_local);
        mLinearDownload = view.findViewById(R.id.linear_download);
        mLinearFavorite = view.findViewById(R.id.linear_favorite);
        mLinearLocal.setOnClickListener(this);
        mLinearFavorite.setOnClickListener(this);
        mLinearDownload.setOnClickListener(this);
    }
}
