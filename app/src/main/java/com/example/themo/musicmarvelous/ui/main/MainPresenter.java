package com.example.themo.musicmarvelous.ui.main;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }
}
