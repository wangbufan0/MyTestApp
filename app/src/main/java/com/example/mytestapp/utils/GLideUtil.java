package com.example.mytestapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mytestapp.R;

public class GLideUtil {
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).into(mImageView);
    }

    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView) {
        Glide.with(mContext).load(path).placeholder(R.drawable.glide_loading).error(R.drawable.gide_error).into(mImageView);
    }

}
