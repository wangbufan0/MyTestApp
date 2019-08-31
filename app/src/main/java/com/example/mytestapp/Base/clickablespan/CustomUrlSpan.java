package com.example.mytestapp.Base.clickablespan;

import android.content.Context;
import android.text.style.ClickableSpan;
import android.view.View;

import com.example.mytestapp.ui.home.webview.WebViewActivity;

/**
 * @Name: CustomUrlSpan
 * @Author: wangbufan
 * @Date: 2019/8/31 21:45
 * @Description:
 */
public class CustomUrlSpan extends ClickableSpan {
    private Context context;
    private String url;
    public CustomUrlSpan(Context context,String url){
        this.context = context;
        this.url = url;
    }

    @Override
    public void onClick(View widget) {
        // 在这里可以做任何自己想要的处理
        WebViewActivity.launch(context,url);
    }
}
