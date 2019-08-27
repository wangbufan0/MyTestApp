package com.example.mytestapp.Base.Retrofit.translation;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Name: TranslationRetrofit
 * @Author: wangbufan
 * @Date: 2019/8/21 22:44
 * @Description:
 */
public class TranslationRetrofit {

    private static TranslationRetrofit mRest;
    private Retrofit retrofit;
    private static final int DEFAULT_TIME = 10;    //默认超时时间
    private static final long RETRY_TIMES = 1;   //重订阅次数

    public static int getDefaultTime() {
        return DEFAULT_TIME;
    }


    public static TranslationRetrofit getInstance() {
        if (mRest == null) {
            mRest = new TranslationRetrofit();
        }
        return mRest;
    }

    public static long getRETRY_TIMES() {
        return RETRY_TIMES;
    }

    private TranslationRetrofit() {

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        builder.connectTimeout(DEFAULT_TIME, TimeUnit.SECONDS);
        //设置拦截器
       // builder.addInterceptor(new AppcodeIntercepter());
       // builder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        OkHttpClient okHttpClient = builder.build();
        retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://api.fanyi.baidu.com/")
                .client(okHttpClient)
                .build();
    }
    public <T> T create(Class<T> service) {
        return this.retrofit.create(service);
    }





}
