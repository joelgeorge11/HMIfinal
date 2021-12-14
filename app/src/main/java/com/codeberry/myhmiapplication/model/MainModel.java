package com.codeberry.myhmiapplication.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.codeberry.myhmiapplication.constants.Constants;
import com.codeberry.settingsinterfacemanager.ISettinsgInterface;

import java.util.Timer;
import java.util.TimerTask;

public class MainModel implements IMainModelInterfaceNotify {

    SettingsInterfaceManager mSettingsInterfaceManager;

    /**
     * Instance of IVehicleServiceInterface.
     */

    private IMainModelNotify mMainModelNotify;


    public MainModel(IMainModelNotify mainModelNotify) {
        mMainModelNotify = mainModelNotify;
        mSettingsInterfaceManager = SettingsInterfaceManager.getInstance();
        mSettingsInterfaceManager.addMainModelNotificationListener(this);
    }

    public void bindExternalService(final Context context) {
        mSettingsInterfaceManager.bindExternalService(context);
    }

    @Override
    public void notifyServiceConnection() {
        String vehileModel = mSettingsInterfaceManager.getVehicleModel();
        Log.w("suhail", "HMI-Vehicle model is" + vehileModel);
        mMainModelNotify.notifyServiceConnection();
    }
}
