package com.example.mytestapp.ui.home.webview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.R;

public class WebViewActivity extends BaseMvpActivity {
    private String url;
    private WebView webView;

    public static void launch(Context context,String url){
        Intent intent = new Intent(context, WebViewActivity.class);
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

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_detail_layout;
    }

}
