package com.codeberry.myhmiapplication.model;

import com.codeberry.myhmiapplication.R;

import java.util.ArrayList;
import java.util.List;

public class ControlModel implements IMainModelNotify.ControlModel{
    @Override
    public List<String> getTitles() {
        List<String> titles=new ArrayList<>();

        titles.add("Screen off");
        titles.add("Cargo Camera");
        titles.add("Rearview Camera");

        return titles;
    }

    @Override
    public List<Integer> getImages() {
        List<Integer> images=new ArrayList<>();

        images.add(R.drawable.screenoff);
        images.add(R.drawable.caricon);

        return images;
    }
}
