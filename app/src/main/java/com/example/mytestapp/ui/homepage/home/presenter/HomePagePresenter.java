package com.example.mytestapp.ui.homepage.home.presenter;

import com.example.mytestapp.Base.Retrofit.MyRest;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp;
import com.example.mytestapp.ui.homepage.home.domain.HomepageService;
import com.example.mytestapp.ui.homepage.home.view.HomepageViewI;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HomePagePresenter extends BasePresenter<HomepageViewI> {

    public void getHomepageDatas() {


        MyRest.getInstance()
                .create(HomepageService.class)
                .GetHomepageReverse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MyObserver<HomepageResp>(this, true) {
                    @Override
                    public void onSuccess(HomepageResp homepageResp) {
                        mMvpView.getHomepageDatassuccessed(homepageResp);
                    }
                });


//                        new Subscriber<HomepageResp>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(HomepageResp homepageResp) {
//                        mMvpView.getHomepageDatassuccessed(homepageResp);
//                    }
//
//                });

    }

}
