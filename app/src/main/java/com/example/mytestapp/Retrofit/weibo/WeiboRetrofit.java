package com.example.mytestapp.Retrofit.weibo;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Name: WeiboRetrofit
 * @Author: wangbufan
 * @Date: 2019/8/27 10:44
 * @Description:
 */
public class WeiboRetrofit {

    private static WeiboRetrofit mRest;
    private Retrofit retrofit;
    private static final int DEFAULT_TIME = 10;    //默认超时时间
    private static final long RETRY_TIMES = 1;   //重订阅次数

    public static int getDefaultTime() {
        return DEFAULT_TIME;
    }


    public static WeiboRetrofit getInstance() {
        if (mRest == null) {
            mRest = new WeiboRetrofit();
        }
        return mRest;
    }

    public static long getRETRY_TIMES() {
        return RETRY_TIMES;
    }

    private WeiboRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        builder.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        //设置拦截器
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        OkHttpClient okHttpClient = builder.build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.weibo.com/2/")
                .client(okHttpClient)
                .build();
    }
    public <T> T create(Class<T> service) {
        return this.retrofit.create(service);
    }


}
