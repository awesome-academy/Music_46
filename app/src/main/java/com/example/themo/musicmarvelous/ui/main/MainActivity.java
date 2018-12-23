package com.example.themo.musicmarvelous.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.themo.musicmarvelous.R;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (MainContract.Presenter) presenter;
    }

    public void initView() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_music:
                    return true;
                case R.id.navigation_settings:
                    return true;
                default:
                    break;
            }
            return false;
        }
    };
}
