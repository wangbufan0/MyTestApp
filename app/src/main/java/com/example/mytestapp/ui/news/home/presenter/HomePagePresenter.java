package com.example.mytestapp.ui.news.home.presenter;

import com.example.mytestapp.Retrofit.news.NewsRetrofit;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.observer.RxSchedulers;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.news.home.domain.HomepageResp1;
import com.example.mytestapp.ui.news.home.domain.HomepageService;
import com.example.mytestapp.ui.news.home.view.HomepageViewI;


public class HomePagePresenter extends BasePresenter<HomepageViewI> {

    public void getHomepageDatas(String top) {


        NewsRetrofit.getInstance()
                .create(HomepageService.class)
                .GetHomepageReverse(top)
                .compose(RxSchedulers.<HomepageResp1>compose())
                .subscribe(new MyObserver<HomepageResp1>(this, true) {
                    @Override
                    public void onSuccess(HomepageResp1 homepageResp) {
                        mMvpView.getHomepageDatassuccessed(homepageResp);
                    }
                });

    }

}
