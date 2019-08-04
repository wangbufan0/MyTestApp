package com.example.mytestapp.ui.news.detail;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.news.detail.domain.NewDetailresq;
import com.example.mytestapp.ui.news.detail.presenter.NewsDetailPresenter;
import com.example.mytestapp.ui.news.detail.view.NewsDetailViewI;

public class NewsDetailActivity extends BaseMvpActivity implements NewsDetailViewI {

    private NewsDetailPresenter newsDetailPresenter;
    private String id;
    private WebView webView;

    public static void launch(Context context,int id){
        Intent intent = new Intent(context,NewsDetailActivity.class);
        intent.putExtra("id",""+id);
        context.startActivity(intent);
    }

    @Override
    protected void initPresenter() {
        newsDetailPresenter= PresenterProviders.of(this, NewsDetailPresenter.class);

    }

    @Override
    protected void initView() {
        id=getIntent().getStringExtra("id");
        webView=findViewById(R.id.wv);
       // webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://daily.zhihu.com/story/"+id);
       // loadData(1);
    }

    @Override
    protected void loadData(int page) {
        newsDetailPresenter.getData(id);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_detail_layout;
    }

    @Override
    public void getDataSuccessed(NewDetailresq data) {

    }
}