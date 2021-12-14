package com.codeberry.myhmiapplication.view;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeberry.myhmiapplication.R;
import com.codeberry.myhmiapplication.presenter.ControlPresenter;
import com.codeberry.myhmiapplication.presenter.SettingsPresenter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControlFragment#//newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment {

    ControlPresenter mControlPresenter=new ControlPresenter();
    RecyclerView dataList;
    Adapter1 adapter;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_control, container, false);

        dataList = view.findViewById(R.id.dataList);
        adapter = new Adapter1(this,mControlPresenter.getTitles(),mControlPresenter.getImages());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapter);
        return view;
    }
}