package com.example.themo.musicmarvelous.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.themo.musicmarvelous.R;
import com.example.themo.musicmarvelous.ui.main.home.HomeFragment;

public class MainActivity extends AppCompatActivity implements MainContract.View,
        BottomNavigationView.OnNavigationItemSelectedListener {
    private MainContract.Presenter mPresenter;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter = new MainPresenter(this);
        initView();
    }

    @Override
    public void setPresenter(Object presenter) {
        mPresenter = (MainContract.Presenter) presenter;
    }

    public void initView() {
        mActionBar = getSupportActionBar();
        loadFragment(new HomeFragment());
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                mActionBar.setTitle(R.string.title_home);
                loadFragment(HomeFragment.newInstance());
                return true;
            case R.id.navigation_music:
                mActionBar.setTitle(R.string.title_library_music);
                return true;
            case R.id.navigation_settings:
                mActionBar.setTitle(R.string.title_settings);
                return true;
            default:
                mActionBar.setTitle(R.string.title_home);
                loadFragment(HomeFragment.newInstance());
                break;
        }
        return false;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
