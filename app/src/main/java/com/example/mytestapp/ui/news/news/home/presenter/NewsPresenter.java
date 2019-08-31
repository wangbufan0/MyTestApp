package com.example.mytestapp.ui.news.news.home.presenter;

import com.example.mytestapp.Retrofit.news.NewsRetrofit;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.observer.RxSchedulers;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.news.news.home.domain.NewsResq;
import com.example.mytestapp.ui.news.news.home.domain.NewsService;
import com.example.mytestapp.ui.news.news.home.view.NewsViewI;


public class NewsPresenter extends BasePresenter<NewsViewI> {



    public void getNesDatas() {

        NewsRetrofit.getInstance()
                .create(NewsService.class)
                .GetNewsReverse()
                .compose(RxSchedulers.<NewsResq>compose())
                .subscribe(new MyObserver<NewsResq>(this) {
                    @Override
                    public void onSuccess(NewsResq newsResq) {
                        assert mMvpView != null;
                        mMvpView.getNewsDatasuccessed(newsResq);
                    }
                });

    }

}
