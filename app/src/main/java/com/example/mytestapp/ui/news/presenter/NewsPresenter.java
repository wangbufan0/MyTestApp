package com.example.mytestapp.ui.news.presenter;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.news.view.NewsViewI;

import java.util.ArrayList;
import java.util.List;


public class NewsPresenter extends BasePresenter<NewsViewI>{

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mMvpView.getNewsDatasuccessed((List<String>) msg.obj);
        }
    };

    public void getNewsData(){
        Message message =new Message();
        List<String> data=new ArrayList<>();
        data.add("5555");
        data.add("6666");
        data.add("77777");
        message.obj=data;
        handler.sendMessageDelayed(message,2000);
    }
}
