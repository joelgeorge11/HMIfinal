package com.codeberry.myhmiapplication.model;

import android.os.RemoteException;

import com.codeberry.settingsinterfacemanager.SettingsLineItem;

import java.util.List;

public class SettingsModel {

    SettingsInterfaceManager settingsInterfaceManager=SettingsInterfaceManager.getInstance();

    public List<SettingsLineItem> getSettingsListItems() throws RemoteException {
        return settingsInterfaceManager.getAllSettings();
    }
}
