package com.example.mytestapp.ui.weibo.homepage.presenter;

import com.example.mytestapp.Base.Retrofit.weibo.WeiboRetrofit;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.observer.RxSchedulers;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.manager.weibo.WeiboManager;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageService;
import com.example.mytestapp.ui.weibo.homepage.view.WeiboHomepageView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Name: WeiboHomePagePresenter
 * @Author: wangbufan
 * @Date: 2019/8/27 10:52
 * @Description:
 */
public class WeiboHomePagePresenter extends BasePresenter<WeiboHomepageView> {

    public void getData(){
        Map<String , String> params = new HashMap<>();
        params.put("access_token", WeiboManager.getInstance().getToken());

        WeiboRetrofit.getInstance()
                .create(WeiboHomepageService.class)
                .getWeiboHomeTimeline(params)
                .compose(RxSchedulers.<WeiboHomepageResp>compose())
                .subscribe(new MyObserver<WeiboHomepageResp>(this,true) {
                    @Override
                    public void onSuccess(WeiboHomepageResp s) {
                        mMvpView.getDataSuccessed(s);
                    }
                });
    }

}
