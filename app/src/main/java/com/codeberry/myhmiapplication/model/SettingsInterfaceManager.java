package com.codeberry.myhmiapplication.model;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.codeberry.myhmiapplication.constants.Constants;
import com.codeberry.settingsinterfacemanager.ISettinsgInterface;
import com.codeberry.settingsinterfacemanager.SettingsLineItem;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsInterfaceManager {
    private static SettingsInterfaceManager sSettingsInterfaceManager;
    private IMainModelInterfaceNotify mMainModelInterfaceNotify;

    private ISettinsgInterface mIsettingInterface = null;

    private SettingsInterfaceManager() {
    }

    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.w("Suh", "Service connected");
            mIsettingInterface = ISettinsgInterface.Stub.asInterface(service);
            mMainModelInterfaceNotify.notifyServiceConnection();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    public static SettingsInterfaceManager getInstance() {
        if (null == sSettingsInterfaceManager) {
            sSettingsInterfaceManager = new SettingsInterfaceManager();
        }
        return sSettingsInterfaceManager;
    }


    public void addMainModelNotificationListener(IMainModelInterfaceNotify iMainModelInterfaceNotify) {
        mMainModelInterfaceNotify = iMainModelInterfaceNotify;
    }


    public void bindExternalService(final Context context) {

                Intent vehicleServiceIntent = new Intent();
                vehicleServiceIntent.setClassName(Constants.VSM_SERVICE_PACKAGE_NAME,
                        Constants.VSM_SERVICE_PACKAGE_NAME
                                + Constants.VSM_SERVICE_CLASS_NAME);
                vehicleServiceIntent.putExtra(Constants.BINDER_TYPE,
                        1);
                if (null == mIsettingInterface) {
                    boolean serviceConnection = context.bindService(vehicleServiceIntent, myConnection, Context
                            .BIND_AUTO_CREATE);
                    if (serviceConnection) {
                        Log.w("Suh", "Service binding success");
                    } else {
                        Log.w("Suh", "Service binding failed");
                    }
                } else {
                    mMainModelInterfaceNotify.notifyServiceConnection();
                }
    }
    public List<SettingsLineItem> getAllSettings() throws RemoteException {
        List<SettingsLineItem> settingsLineItem = mIsettingInterface.getAllSettings();
        return settingsLineItem;
    }

    /**
     *
     * @return
     */
    public String getVehicleModel() {
        String vehicleModel = "";
        if (null != mIsettingInterface) {
            try {
               List<SettingsLineItem> settingsLineItem = mIsettingInterface.getAllSettings();
               if (null != settingsLineItem) {
                   Log.w("suh", "value recived" + settingsLineItem.size());
               } else {
                   Log.w("suh", "null value recived");
               }
            } catch (RemoteException e) {
                Log.w("suh", "get vehicle model exception");
            }
        } else {
            Log.w("suh", "mIsettingInterface is null");
        }
        return vehicleModel;
    }

}
