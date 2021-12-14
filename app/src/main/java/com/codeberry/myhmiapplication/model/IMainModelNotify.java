package com.codeberry.myhmiapplication.model;

import java.util.List;

public interface IMainModelNotify {

   void notifyServiceConnection();

   interface ControlModel{
      List<String> getTitles();
      List<Integer> getImages();
   }
}
