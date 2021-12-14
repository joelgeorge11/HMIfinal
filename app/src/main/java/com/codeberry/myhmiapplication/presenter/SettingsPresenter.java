package com.codeberry.myhmiapplication.presenter;

import android.os.RemoteException;

import com.codeberry.myhmiapplication.model.SettingsModel;
import com.codeberry.settingsinterfacemanager.SettingsLineItem;

import java.util.List;

public class SettingsPresenter {
    SettingsModel msettingsModel;
    public SettingsPresenter(){
        msettingsModel=new SettingsModel();
    }
    public List <SettingsLineItem> getSettingsListItems() throws RemoteException {
        return msettingsModel.getSettingsListItems();
    }

}
