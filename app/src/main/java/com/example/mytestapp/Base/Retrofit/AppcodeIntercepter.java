package com.example.mytestapp.Base.Retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author : wangbufan
 * @Time : 18:43 2019-08-14
 * @description :
 * --------------------------------------
 */
public class AppcodeIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();

        Request.Builder builder = request.newBuilder();
        builder.header("Authorization","APPCODE ec7246ebb2634c8ebc42ff9f529b7575");

        return chain.proceed(builder.build());

    }
}
