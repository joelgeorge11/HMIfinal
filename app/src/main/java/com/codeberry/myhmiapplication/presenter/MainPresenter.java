package com.codeberry.myhmiapplication.presenter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.codeberry.myhmiapplication.constants.Constants;
import com.codeberry.myhmiapplication.model.IMainModelNotify;
import com.codeberry.myhmiapplication.model.MainModel;
import com.codeberry.myhmiapplication.view.IMainView;
import com.codeberry.settingsinterfacemanager.ISettinsgInterface;

import java.util.Timer;
import java.util.TimerTask;

public class MainPresenter implements  IMainPresenter, IMainModelNotify {


    private IMainView mIMainView;
    private MainModel mMainModel;

    public MainPresenter(IMainView mainView) {
        mIMainView = mainView;
        mMainModel = new MainModel(this);
    }

    @Override
    public void bindService(Context context) {
        mMainModel.bindExternalService(context);
    }

    @Override
    public void notifyServiceConnection() {
        mIMainView.loadFragment();
    }
}
