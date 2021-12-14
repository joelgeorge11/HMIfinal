package com.codeberry.myhmiapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;

import com.codeberry.myhmiapplication.view.MainFragment;
import com.codeberry.myhmiapplication.view.SettingsFragment;

public class MainActivity extends AppCompatActivity  {

    private FragmentContainerView mMainFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        addBaseFragment();
                //sendBroadcast(new Intent("com.codeberry.settingsService.AutoStart.ACTION_CUSTOM"));

    }

    private void initializeView() {
        mMainFrameLayout = findViewById(R.id.frame_mainfragment);

    }

    private void addBaseFragment() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        supportFragmentManager.beginTransaction()
                .add(R.id.frame_mainfragment, new MainFragment())
                .commit();
    }


}