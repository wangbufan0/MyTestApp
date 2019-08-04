package com.example.mytestapp.ui.homepage.home.presenter;

import com.example.mytestapp.Base.Retrofit.myRest;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp;
import com.example.mytestapp.ui.homepage.home.domain.HomepageService;
import com.example.mytestapp.ui.homepage.home.view.HomepageViewI;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomePagePresenter extends BasePresenter<HomepageViewI> {

    public void getHomepageDatas() {

        myRest.getInstance()
                .create(HomepageService.class)
                .GetHomepageReverse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomepageResp>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomepageResp homepageResp) {
                        mMvpView.getHomepageDatassuccessed(homepageResp);
                    }

                });

    }

}
