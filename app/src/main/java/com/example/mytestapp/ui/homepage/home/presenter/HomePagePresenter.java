package com.example.mytestapp.ui.homepage.home.presenter;

import com.example.mytestapp.Base.Retrofit.BaseRetrofit;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.observer.RxSchedulers;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp1;
import com.example.mytestapp.ui.homepage.home.domain.HomepageService;
import com.example.mytestapp.ui.homepage.home.view.HomepageViewI;


public class HomePagePresenter extends BasePresenter<HomepageViewI> {

    public void getHomepageDatas(String top) {


        BaseRetrofit.getInstance()
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
