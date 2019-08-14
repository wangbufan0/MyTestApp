package com.example.mytestapp.load.image;

import android.content.Context;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.concurrent.ExecutionException;

/**
 * @Author : wangbufan
 * @Time : 13:57 2019-08-14
 * @description :
 * --------------------------------------
 */
public class GetImagePathService implements Runnable {

    private String url;
    private Context context;
    private GetImagePathCallBack callBack;


    public GetImagePathService(Context context, String url, GetImagePathCallBack callBack) {
        this.context = context;
        this.url = url;
        this.callBack = callBack;
    }

    @Override
    public void run() {
        File file = null;
        try {
           file= Glide.with(context)
                    .asFile()
                    .load(url)
                    .submit()
                    .get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (file == null) callBack.Failed();
            else callBack.Successed(file);
        }
    }
}
