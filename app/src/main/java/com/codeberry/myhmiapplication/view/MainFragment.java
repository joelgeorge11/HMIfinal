package com.codeberry.myhmiapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import com.codeberry.myhmiapplication.R;
import com.codeberry.myhmiapplication.constants.Constants;
import com.codeberry.myhmiapplication.presenter.IMainPresenter;
import com.codeberry.myhmiapplication.presenter.MainPresenter;
import com.codeberry.myhmiapplication.presenter.SettingsPresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements IMainView {
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter adapter;





    private MainPresenter mMainPresenter;
    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        tabLayout=view.findViewById(R.id.tablayout);
        viewPager2=view.findViewById(R.id.viewpager2);
        initObjects();
        bindService();
        return view;
    }

    private void initObjects() {
        mMainPresenter = new MainPresenter(this);
    }

    private void bindService() {
        mMainPresenter.bindService(getActivity().getApplicationContext());
    }

    @Override
    public void loadFragment() {


        FragmentManager fm=getActivity().getSupportFragmentManager();
        adapter=new FragmentAdapter(fm,getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addTab(tabLayout.newTab().setText("Control"));
        tabLayout.addTab(tabLayout.newTab().setText("Settings"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        TabLayout.Tab tab= tabLayout.getTabAt(1);
        tab.select();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

//        FragmentManager supportFragmentManager = getActivity().getSupportFragmentManager();
//        supportFragmentManager.beginTransaction()
//                .add(R.id.fragmentsettings, new SettingsFragment())
//                .commit();
    }
}