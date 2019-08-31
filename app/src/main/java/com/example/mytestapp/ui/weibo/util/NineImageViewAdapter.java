package com.example.mytestapp.ui.weibo.util;

import android.content.Context;
import android.widget.ImageView;

import com.example.mytestapp.ui.news.community.BigImage.BigImageActivity;
import com.example.mytestapp.utils.GLideUtil;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.List;

/**
 * @Name: NineImageViewAdapter
 * @Author: wangbufan
 * @Date: 2019/8/31 17:24
 * @Description:
 */
public class NineImageViewAdapter extends NineGridImageViewAdapter<String> {
    @Override//设置显示图片的方法
    protected void onDisplayImage(Context context, ImageView imageView, String s) {
        GLideUtil.loadImageViewLoding(context, s, imageView);
    }
    @Override//图片点击事件
    protected void onItemImageClick(Context context, int index, List<String> list) {
        BigImageActivity.launch(context, index, WeiboImageUrlUtil.getLargeImageURL(list));
    }
}
