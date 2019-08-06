package com.example.mytestapp.ui.news.home.presenter;

import com.example.mytestapp.Base.Retrofit.BaseRetrofit;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.news.home.domain.NewsResq;
import com.example.mytestapp.ui.news.home.domain.NewsService;
import com.example.mytestapp.ui.news.home.view.NewsViewI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class NewsPresenter extends BasePresenter<NewsViewI> {


    public void getNesDatas() {

        BaseRetrofit.getInstance()
                .create(NewsService.class)
                .GetNewsReverse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<NewsResq>(this, true) {
                    @Override
                    public void onSuccess(NewsResq newsResq) {
                        mMvpView.getNewsDatasuccessed(newsResq);
                    }
                });

    }

}
