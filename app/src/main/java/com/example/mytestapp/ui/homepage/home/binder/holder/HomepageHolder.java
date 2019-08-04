package com.example.mytestapp.ui.homepage.home.binder.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.R;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp;
import com.example.mytestapp.ui.news.detail.NewsDetailActivity;
import com.example.mytestapp.utils.GLideUtil;

public class HomepageHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;


        public static HomepageHolder getInstance(LayoutInflater inflater, ViewGroup parent){
            View view = inflater.inflate(R.layout.item_news_layout,parent,false);
            return  new HomepageHolder(view);
        }


        public HomepageHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_title);
            imageView=itemView.findViewById(R.id.iv1);
        }

        public void postDataToUI(final HomepageResp.StoriesBean Data){
            textView.setText(Data.getTitle());
            GLideUtil.loadImageViewLoding(itemView.getContext(), Data.getImages().get(0),imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NewsDetailActivity.launch(itemView.getContext(),Data.getId());
                }
            });
        }

}
