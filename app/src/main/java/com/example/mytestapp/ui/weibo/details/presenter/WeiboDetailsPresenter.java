package com.example.mytestapp.ui.weibo.details.presenter;

import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.observer.RxSchedulers;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.Retrofit.weibo.WeiboRetrofit;
import com.example.mytestapp.manager.weibo.WeiboManager;
import com.example.mytestapp.ui.weibo.details.domin.WeiboDetailsCommentResp;
import com.example.mytestapp.ui.weibo.details.domin.WeiboDetailsCommentService;
import com.example.mytestapp.ui.weibo.details.view.WeiboDetailsView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Name: WeiboDetailsPresenter
 * @Author: wangbufan
 * @Date: 2019/9/5 22:29
 * @Description:
 */
public class WeiboDetailsPresenter extends BasePresenter<WeiboDetailsView> {

    public void getCommentDatas(long id){
        Map<String,String> req=new HashMap<>();
        req.put("access_token", WeiboManager.getInstance().getToken());
        req.put("id",String.valueOf(id));

        WeiboRetrofit.getInstance()
                .create(WeiboDetailsCommentService.class)
                .getConmmentResp(req)
                .compose(RxSchedulers.<WeiboDetailsCommentResp>compose())
                .subscribe(new MyObserver<WeiboDetailsCommentResp>(this,true) {
                    @Override
                    public void onSuccess(WeiboDetailsCommentResp weiboDetailsCommentResp) {
                        mMvpView.loadCommentSuccessed(weiboDetailsCommentResp);
                    }
                });

    }

}
