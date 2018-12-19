package com.example.themo.musicmarvelous.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.themo.musicmarvelous.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {
    private SplashContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SplashPresenter(this);
        mPresenter.updateUI();
    }

    @Override
    public void gotoMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
