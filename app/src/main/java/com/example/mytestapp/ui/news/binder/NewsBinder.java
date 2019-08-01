package com.example.mytestapp.ui.news.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.R;
import com.example.mytestapp.ui.news.binder.holder.NewsViewHolder;

import me.drakeet.multitype.ItemViewBinder;

public class NewsBinder extends ItemViewBinder<String, NewsViewHolder> {

    @NonNull
    @Override
    protected NewsViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return  new NewsViewHolder(inflater.inflate(R.layout.item_news_layout,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull NewsViewHolder newsVH, @NonNull String s) {
        newsVH.postDataToUI(s);
    }


}
