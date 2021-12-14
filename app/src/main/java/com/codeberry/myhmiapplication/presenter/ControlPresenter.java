package com.codeberry.myhmiapplication.presenter;

import com.codeberry.myhmiapplication.model.ControlModel;

import java.util.List;

public class ControlPresenter implements IMainPresenter.ControlPresenter{
    private ControlModel mControlModel=new ControlModel();

    @Override
    public List<String> getTitles() {
        return mControlModel.getTitles();
    }

    @Override
    public List<Integer> getImages() {
        return mControlModel.getImages();
    }
}
