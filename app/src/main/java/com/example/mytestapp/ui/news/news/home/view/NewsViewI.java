package com.example.mytestapp.ui.news.news.home.view;

import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.ui.news.news.home.domain.NewsResq;

public interface NewsViewI extends BaseView {

    void getNewsDatasuccessed(NewsResq Datas);
}
