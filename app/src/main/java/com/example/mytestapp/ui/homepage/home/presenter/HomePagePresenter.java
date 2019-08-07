package com.example.mytestapp.ui.homepage.home.presenter;

import com.example.mytestapp.Base.Retrofit.BaseRetrofit;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.observer.RxSchedulers;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp;
import com.example.mytestapp.ui.homepage.home.domain.HomepageService;
import com.example.mytestapp.ui.homepage.home.view.HomepageViewI;


public class HomePagePresenter extends BasePresenter<HomepageViewI> {

    public void getHomepageDatas() {


        BaseRetrofit.getInstance()
                .create(HomepageService.class)
                .GetHomepageReverse()
                .compose(RxSchedulers.<HomepageResp>compose())
                .subscribe(new MyObserver<HomepageResp>(this, true) {
                    @Override
                    public void onSuccess(HomepageResp homepageResp) {
                        mMvpView.getHomepageDatassuccessed(homepageResp);
                    }
                });



    }

}
