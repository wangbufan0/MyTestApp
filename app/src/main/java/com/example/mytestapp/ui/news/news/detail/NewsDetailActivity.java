package com.example.mytestapp.ui.news.news.detail;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.news.news.detail.domain.NewDetailresq;
import com.example.mytestapp.ui.news.news.detail.presenter.NewsDetailPresenter;
import com.example.mytestapp.ui.news.news.detail.view.NewsDetailViewI;

public class NewsDetailActivity extends BaseMvpActivity implements NewsDetailViewI {

    private NewsDetailPresenter newsDetailPresenter;
    private String url;
    private WebView webView;

    public static void launch(Context context,String url){
        Intent intent = new Intent(context,NewsDetailActivity.class);
        intent.putExtra("url",""+url);
        context.startActivity(intent);
    }

    @Override
    protected void initBar() {
        super.initBar();
        TextView textView=titleLayoutI.getmTextViewRightText();
        textView.setText("在浏览器打开");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse(url);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void parseArguments() {
        super.parseArguments();
        url=getIntent().getStringExtra("url");
    }

    @Override
    protected void initPresenter() {
        newsDetailPresenter= PresenterProviders.of(this, NewsDetailPresenter.class);

    }


    @Override
    protected void initView() {

        webView=findViewById(R.id.wv);
       // webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
       // loadData(1);
    }

    @Override
    protected void loadData(int page) {
        //newsDetailPresenter.getData(id);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_detail_layout;
    }

    @Override
    public void getDataSuccessed(NewDetailresq data) {

    }
}
