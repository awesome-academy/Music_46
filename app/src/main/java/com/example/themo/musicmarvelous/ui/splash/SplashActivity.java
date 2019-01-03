package com.example.themo.musicmarvelous.ui.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.example.themo.musicmarvelous.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity implements SplashContract.View {
    private static final int REQUEST_PERMISSION_STORAGE = 123;
    private SplashContract.Presenter mPresenter;
    private Boolean mIsPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new SplashPresenter(this);
        mPresenter.updateUI();
        initPermission();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_STORAGE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mIsPermission = true;
                } else {
                    mIsPermission = false;
                }
                break;
        }
    }

    public void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSION_STORAGE);
            } else {
                mIsPermission = true;
            }
        } else {
            mIsPermission = true;
        }
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
