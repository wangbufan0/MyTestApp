package com.example.mytestapp.ui.news.news.home.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.ui.news.home.domain.HomepageResp1;
import com.example.mytestapp.ui.news.news.home.binder.holder.NewsViewHolder;

import me.drakeet.multitype.ItemViewBinder;

public class NewsBinder extends ItemViewBinder<HomepageResp1.ResultBean.DataBean, NewsViewHolder> {

    @NonNull
    @Override
    protected NewsViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return NewsViewHolder.getInstance(inflater,parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, @NonNull HomepageResp1.ResultBean.DataBean topStoriesBean) {
        newsViewHolder.postDataToUI(topStoriesBean);
    }




}
