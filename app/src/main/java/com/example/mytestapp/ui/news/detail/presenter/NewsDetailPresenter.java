package com.example.mytestapp.ui.news.detail.presenter;


import com.example.mytestapp.Base.Retrofit.BaseRetrofit;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.news.detail.domain.NewDetailresq;
import com.example.mytestapp.ui.news.detail.domain.NewsDetailService;
import com.example.mytestapp.ui.news.home.view.NewsViewI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NewsDetailPresenter extends BasePresenter<NewsViewI> {


    public void getData(String id) {

        BaseRetrofit.getInstance()
                .create(NewsDetailService.class)
                .GetNewsReverse(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<NewDetailresq>(this) {

                    @Override
                    public void onSuccess(NewDetailresq newDetailresq) {

                    }
                });

    }

}
