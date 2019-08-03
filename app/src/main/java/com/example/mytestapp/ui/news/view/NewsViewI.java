package com.example.mytestapp.ui.news.view;

import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.ui.news.domain.NewsResq;

public interface NewsViewI extends BaseView {

    void getNewsDatasuccessed(NewsResq Datas);
}
