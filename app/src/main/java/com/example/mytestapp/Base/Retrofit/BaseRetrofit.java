package com.example.mytestapp.Base.Retrofit;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseRetrofit {
    private static BaseRetrofit mRest;
    private Retrofit retrofit;
    private static final int DEFAULT_TIME = 10;    //默认超时时间
    private final long RETRY_TIMES = 1;   //重订阅次数


    public static BaseRetrofit getInstance() {
        if (mRest == null) {
            mRest = new BaseRetrofit();
        }
        return mRest;
    }

    private BaseRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        builder.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        //设置拦截器
      //  builder.addInterceptor(new BasicParamsInterceptor.Builder().addParamsMap(getCommonMap()).build());
        builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        OkHttpClient okHttpClient = builder.build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .client(okHttpClient)
                .build();
    }

    public <T> T create(Class<T> service) {
        return this.retrofit.create(service);
    }


}