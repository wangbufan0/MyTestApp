package com.example.mytestapp.ui.homepage.home.presenter;

import com.example.mytestapp.Base.Retrofit.BaseRetrofit;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp;
import com.example.mytestapp.ui.homepage.home.domain.HomepageService;
import com.example.mytestapp.ui.homepage.home.view.HomepageViewI;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class HomePagePresenter extends BasePresenter<HomepageViewI> {

    public void getHomepageDatas() {


        BaseRetrofit.getInstance()
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



    }

}
