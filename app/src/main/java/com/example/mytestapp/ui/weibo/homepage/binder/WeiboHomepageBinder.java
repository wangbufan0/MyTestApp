package com.example.mytestapp.ui.weibo.homepage.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.ui.weibo.homepage.binder.hlder.WeiboHomepageViewHolder;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;

import me.drakeet.multitype.ItemViewBinder;

public class WeiboHomepageBinder extends ItemViewBinder<WeiboHomepageResp.StatusesBean, WeiboHomepageViewHolder> {
    @NonNull
    @Override
    protected WeiboHomepageViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return WeiboHomepageViewHolder.getInstance(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull WeiboHomepageViewHolder weiboHomepageViewHolder, @NonNull WeiboHomepageResp.StatusesBean communityAttentionResp) {
        weiboHomepageViewHolder.postDataToUI(communityAttentionResp);
    }

}
