package com.example.mytestapp.ui.news.home.presenter;

import com.example.mytestapp.Base.Retrofit.myRest;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.news.home.domain.NewsResq;
import com.example.mytestapp.ui.news.home.domain.NewsService;
import com.example.mytestapp.ui.news.home.view.NewsViewI;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class NewsPresenter extends BasePresenter<NewsViewI> {


    public void getNesDatas() {

        myRest.getInstance()
                .create(NewsService.class)
                .GetNewsReverse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsResq>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(NewsResq newsResq) {
                        mMvpView.getNewsDatasuccessed(newsResq);
                    }
                });

    }

}
