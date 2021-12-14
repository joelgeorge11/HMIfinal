package com.codeberry.myhmiapplication.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeberry.myhmiapplication.R;
import com.codeberry.myhmiapplication.constants.Constants;
import com.codeberry.myhmiapplication.model.SettingsInterfaceManager;
import com.codeberry.myhmiapplication.presenter.SettingsPresenter;
import com.codeberry.settingsinterfacemanager.SettingsLineItem;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    RecyclerView recyclerView;
    QuantityAdapter adapter;
    List<SettingsLineItem> mSettingsLineItem;
    SettingsPresenter mSettingspresenter=new SettingsPresenter();



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    mSettingsLineItem=mSettingspresenter.getSettingsListItems();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                if (null != mSettingsLineItem) {
                    Log.w("akshay", "value recived" + mSettingsLineItem);
                } else {
                    Log.w("akshay", "null value recived");
                }
            }
        },500);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
       // initObjects();
        recyclerView=view.findViewById(R.id.recyclerView);
        //setRecyclerView();
        SettingsInterfaceManager settingsInterfaceManager=SettingsInterfaceManager.getInstance();

        Context context=view.getContext();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter=new QuantityAdapter(getContext(),mSettingsLineItem);
                recyclerView.setAdapter(adapter);
            }
        },501);
        return view;
    }
}