package com.example.mytestapp.manager.weibo;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;

/**
 * @Name: WeiboManager
 * @Author: wangbufan
 * @Date: 2019/8/26 22:59
 * @Description:
 */
public class WeiboManager {
    private static final String SP_SPACE_WEIBO = "SP_SPACE_WEIBO";
    private static final String SP_WEIBO_TOKEN = "SP_WEIBO_TOKEN";
    private static final String SP_WEIBO_PHONE="SP_WEIBO_PHONE";
    private static final String SP_WEIBO_SCROLLX="SP_WEIBO_SCROLLX";
    private static final String SP_WEIBO_SCROLLY="SP_WEIBO_SCROLLY";

    private volatile static WeiboManager weiboManager;

    public static WeiboManager getInstance(){
        if(null==weiboManager){
            synchronized (WeiboManager.class){
                if (null==weiboManager){
                    weiboManager=new WeiboManager();
                }
            }
        }
        return weiboManager;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(SPUtils.getInstance(SP_SPACE_WEIBO).getString(SP_WEIBO_TOKEN));
    }
    public void cleanToken(){
        SPUtils.getInstance(SP_SPACE_WEIBO).put(SP_WEIBO_TOKEN, "", true);
    }

    public void saveToken(String tokenStr) {
        if (TextUtils.isEmpty(tokenStr)) return;
        SPUtils.getInstance(SP_SPACE_WEIBO).put(SP_WEIBO_TOKEN, tokenStr, true);
    }

    public String getToken() {
        return SPUtils.getInstance(SP_SPACE_WEIBO).getString(SP_WEIBO_TOKEN, "");
    }
    public void savePhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) return;
        SPUtils.getInstance(SP_SPACE_WEIBO).put(SP_WEIBO_PHONE, phoneNumber, true);
    }

    public String getPhoneNumber() {
        return SPUtils.getInstance(SP_SPACE_WEIBO).getString(SP_WEIBO_PHONE, "");
    }

    public void saveScroll(int x,int y){
        SPUtils.getInstance(SP_SPACE_WEIBO).put(SP_WEIBO_SCROLLX,x,true);
        SPUtils.getInstance(SP_SPACE_WEIBO).put(SP_WEIBO_SCROLLY,y,true);
    }

    public int getScrollX(){
        return SPUtils.getInstance(SP_SPACE_WEIBO).getInt(SP_WEIBO_SCROLLX,0);
    }
    public int getScrollY(){
        return SPUtils.getInstance(SP_SPACE_WEIBO).getInt(SP_WEIBO_SCROLLY,0);
    }
}
