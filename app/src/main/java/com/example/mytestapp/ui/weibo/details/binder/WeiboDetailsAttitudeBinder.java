package com.example.mytestapp.ui.weibo.details.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.ui.weibo.details.binder.holder.WeiboDetailsAttitudeHolder;
import com.example.mytestapp.ui.weibo.details.domin.WeiboDetailsCommentResp;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @Name: WeiboDetailsCommentBinder
 * @Author: wangbufan
 * @Date: 2019/9/5 22:07
 * @Description:
 */
public class WeiboDetailsAttitudeBinder extends ItemViewBinder<WeiboDetailsCommentResp.CommentsBean, WeiboDetailsAttitudeHolder> {
    @NonNull
    @Override
    protected WeiboDetailsAttitudeHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return WeiboDetailsAttitudeHolder.launch(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull WeiboDetailsAttitudeHolder weiboDetailsCommentViewHolder, @NonNull WeiboDetailsCommentResp.CommentsBean weiboDetailsCommentResp) {
            weiboDetailsCommentViewHolder.postData2UI(weiboDetailsCommentResp);
    }
}
