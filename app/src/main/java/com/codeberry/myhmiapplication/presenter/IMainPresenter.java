package com.codeberry.myhmiapplication.presenter;

import android.content.Context;

import java.util.List;

public interface IMainPresenter {

    void bindService(Context context);

    interface ControlPresenter{
        List<String> getTitles();

        List<Integer> getImages();

    }

}
