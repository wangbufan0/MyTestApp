package com.example.mytestapp.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.mytestapp.R;

public class GLideUtil {
    public static void loadImageView(Context mContext, String path, ImageView mImageView) {
        Glide
                .with(mContext)
                .load(path)
                .into(mImageView);
    }

    public static void loadImageViewLoding(Context mContext, String path, ImageView mImageView) {
        Glide
                .with(mContext)
                .load(path)
                .placeholder(R.drawable.glide_loading)
                .error(R.drawable.gide_error)
                .into(mImageView);
    }

    public static void loadImageViewRound(Context mContext, String path, ImageView mImageView){
        Glide.with(mContext)
                .load(path)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .placeholder(R.drawable.glide_loading)
                .error(R.drawable.gide_error)
                .into(mImageView);
    }

}
