package com.example.mytestapp.Base.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRest {
    private static MyRest mRest;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;


    public static MyRest getInstance() {
        if (mRest == null) {
            mRest = new MyRest();
        }
        return mRest;
    }

    private MyRest() {
        okHttpClient = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .client(okHttpClient)
                .build();
    }

    public <T> T create(Class<T> service) {
        return this.retrofit.create(service);
    }


}
