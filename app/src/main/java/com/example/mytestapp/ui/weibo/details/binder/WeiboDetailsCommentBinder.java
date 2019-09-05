package com.example.mytestapp.ui.weibo.details.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.ui.weibo.details.binder.holder.WeiboDetailsCommentViewHolder;
import com.example.mytestapp.ui.weibo.details.domin.WeiboDetailsCommentResp;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @Name: WeiboDetailsCommentBinder
 * @Author: wangbufan
 * @Date: 2019/9/5 22:07
 * @Description:
 */
public class WeiboDetailsCommentBinder extends ItemViewBinder<WeiboDetailsCommentResp.CommentsBean, WeiboDetailsCommentViewHolder> {
    @NonNull
    @Override
    protected WeiboDetailsCommentViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return WeiboDetailsCommentViewHolder.launch(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull WeiboDetailsCommentViewHolder weiboDetailsCommentViewHolder, @NonNull WeiboDetailsCommentResp.CommentsBean weiboDetailsCommentResp) {
            weiboDetailsCommentViewHolder.postData2UI(weiboDetailsCommentResp);
    }
}
