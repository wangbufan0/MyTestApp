package com.example.mytestapp.ui.news.detail.presenter;

import com.example.mytestapp.Base.Retrofit.MyRest;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.news.detail.domain.NewDetailresq;
import com.example.mytestapp.ui.news.detail.domain.NewsDetailService;
import com.example.mytestapp.ui.news.home.view.NewsViewI;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class NewsDetailPresenter extends BasePresenter<NewsViewI> {


    public void getData(String id) {

        MyRest.getInstance()
                .create(NewsDetailService.class)
                .GetNewsReverse(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewDetailresq>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewDetailresq newsDetailService) {

                    }
                });

    }

}
