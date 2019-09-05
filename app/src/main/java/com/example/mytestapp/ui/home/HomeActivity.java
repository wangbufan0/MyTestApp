package com.example.mytestapp.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytestapp.R;
import com.example.mytestapp.ui.lottery.LotteryActivity;
import com.example.mytestapp.ui.news.NewsActivity;
import com.example.mytestapp.ui.translation.TranslationActivity;
import com.example.mytestapp.ui.weibo.home.WeiboActivity;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    Context context=this;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById(R.id.bt).setOnClickListener(this);
        findViewById(R.id.bt_weibo).setOnClickListener(this);
        findViewById(R.id.bt_translation).setOnClickListener(this);
        findViewById(R.id.bt_lottery).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt:
                NewsActivity.launch(context);
                break;
            case R.id.bt_weibo:
                WeiboActivity.launch(context);
                break;
            case R.id.bt_translation:
                TranslationActivity.launch(context);
                break;
            case R.id.bt_lottery:
                LotteryActivity.launch(context);
                break;
        }
    }
}
