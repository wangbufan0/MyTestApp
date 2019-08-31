package com.example.mytestapp.ui.translation.presenter;

import com.example.mytestapp.Retrofit.translation.TranslationRetrofit;
import com.example.mytestapp.Base.observer.MyObserver;
import com.example.mytestapp.Base.observer.RxSchedulers;
import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.translation.domain.TranslationResp;
import com.example.mytestapp.ui.translation.domain.TranslationService;
import com.example.mytestapp.ui.translation.view.TranslationViewI;
import com.example.mytestapp.utils.MD5Util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Name: TranslationPresenter
 * @Author: wangbufan
 * @Date: 2019/8/21 23:11
 * @Description:
 */
public class TranslationPresenter extends BasePresenter<TranslationViewI>{

    public static String appid="20190602000304123";
    public static String securityKey="13h2Q37ayuJy_kAxkkER";

    public void getTranslationData(String q,String from ,String to)  {

        Map<String,String> params=new HashMap<>();
        params.put("q",q);
        params.put("from",from);
        params.put("to",to);
        params.put("appid",appid);
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        // 签名
        String src = appid + q + salt + securityKey; // 加密前的原文
        params.put("sign", MD5Util.md5(src));

        TranslationRetrofit.getInstance()
                .create(TranslationService.class)
                .getTranslationData(params)
                .compose(RxSchedulers.<TranslationResp>compose())
                .subscribe(new MyObserver<TranslationResp>(this) {
                    @Override
                    public void onSuccess(TranslationResp s) {
                        mMvpView.getDataSuccess(s);
                    }
                });

    }

}
