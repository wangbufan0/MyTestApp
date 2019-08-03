package com.example.mytestapp.ui.news.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.ui.news.binder.holder.NewsViewHolder;
import com.example.mytestapp.ui.news.domain.NewsResq;

import me.drakeet.multitype.ItemViewBinder;

public class NewsBinder extends ItemViewBinder<NewsResq.TopStoriesBean, NewsViewHolder> {

    @NonNull
    @Override
    protected NewsViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return NewsViewHolder.getInstance(inflater,parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, @NonNull NewsResq.TopStoriesBean topStoriesBean) {
        newsViewHolder.postDataToUI(topStoriesBean);
    }




}
