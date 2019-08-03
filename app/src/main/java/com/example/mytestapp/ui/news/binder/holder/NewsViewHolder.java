package com.example.mytestapp.ui.news.binder.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.news.domain.NewsResq;

public class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;


        public static NewsViewHolder getInstance(LayoutInflater inflater, ViewGroup parent){
            View view = inflater.inflate(R.layout.item_news_layout,parent,false);
            return  new NewsViewHolder(view);
        }


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_title);
            imageView=itemView.findViewById(R.id.iv1);
        }

        public void postDataToUI(NewsResq.TopStoriesBean Data){
            textView.setText(Data.getTitle());
            Glide.with(itemView.getContext())
                    .load(Data.getImage())
                    .into(imageView);
        }

}
