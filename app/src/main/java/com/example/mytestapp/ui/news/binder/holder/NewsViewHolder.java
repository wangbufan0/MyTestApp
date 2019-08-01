package com.example.mytestapp.ui.news.binder.holder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.R;

public class NewsViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;


        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.tv_title);
        }

        public void postDataToUI(String s){
            textView.setText(s);
        }

}
