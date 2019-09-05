package com.example.mytestapp.load.image;

import android.graphics.Bitmap;

import java.io.File;

/**
 * @Author : wangbufan
 * @Time : 13:58 2019-08-14
 * @description :
 * --------------------------------------
 */
public interface GetImagePathCallBack {
    void Successed(File file);
    void BitMapSuccessed(Bitmap bitmap);

    void Failed();
}
