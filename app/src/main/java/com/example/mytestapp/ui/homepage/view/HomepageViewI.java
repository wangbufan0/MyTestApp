package com.example.mytestapp.ui.homepage.view;

import com.example.mytestapp.Base.View.BaseView;

import java.util.List;

public interface HomepageViewI extends BaseView {

    void getScrollingPictureSuccessed(List<String> urlPucture);

    void getNewsDatasuccessed(List<String> Datas);
}
