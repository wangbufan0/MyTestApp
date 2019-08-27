package com.example.mytestapp.ui.weibo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.mytestapp.R;
import com.example.mytestapp.manager.weibo.WeiboManager;
import com.example.mytestapp.ui.weibo.domain.SelfWbAuthListener;
import com.example.mytestapp.ui.weibo.homepage.WeiboHomepageFragment;
import com.example.mytestapp.ui.weibo.key.Constants;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

public class WeiboActivity extends AppCompatActivity {

    AuthInfo mAuthInfo;
    SsoHandler mSsoHandler;
    FrameLayout frameLayout;
    Fragment fragment;

    public static void launch(Context context){
        Intent intent=new Intent(context,WeiboActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weibo);
        fragment=new WeiboHomepageFragment();
        if(WeiboManager.getInstance().isLogin()){
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frame_layout,fragment)
                    .show(fragment)
                    .commit();
        }else{
            mAuthInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
            WbSdk.install(this,mAuthInfo);
            mSsoHandler = new SsoHandler(this);
            mSsoHandler. authorize(new SelfWbAuthListener());
        }
       // WeiboManager.getInstance().cleanToken();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
}
