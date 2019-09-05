package com.example.mytestapp.ui.home.webview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.R;

public class WebViewActivity extends BaseMvpActivity {
    private String url;
    private WebView webView;
    private ProgressBar progressBar;

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
        progressBar=findViewById(R.id.progressbar);
        webView=findViewById(R.id.wv);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                webView.loadUrl(request.getUrl().toString());
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if(newProgress==100)
                    progressBar.setVisibility(View.GONE);
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.setProgress(newProgress);
                }

            }
        });

        webView.loadUrl(url);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()){
                webView.goBack();
                return true;
            }
            else
                finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void loadData(int page) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_news_detail_layout;
    }

}
