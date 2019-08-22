package com.example.mytestapp.ui.weibo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytestapp.R;
import com.sina.weibo.sdk.web.WeiboSdkWebActivity;

public class WeiboActivity extends AppCompatActivity {

    public static void launch(Context context){
        Intent intent=new Intent(context,WeiboActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo);

        Intent intent = new Intent(this, WeiboSdkWebActivity.class);
        startActivity(intent);
    }
}
