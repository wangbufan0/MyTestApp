package com.example.mytestapp.Base.Retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class myRest {
    private static Retrofit retrofit;


    public static Retrofit getInstance(){
        if(retrofit==null){
            retrofit=initRetrofit();
        }
            return retrofit;
    }




    public static Retrofit initRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://news-at.zhihu.com/api/4/")
                .client(okHttpClient)
                .build();
        return retrofit;
    }



}
