package com.example.mytestapp.manager.news;

import android.text.TextUtils;

import com.blankj.utilcode.util.SPUtils;

public class UserManager {

    private static final String SP_SPACE_KEY_USER_NAME = "SP_SPACE_KEY_USER_NAME";
    private static final String SP_KEY_USER_TOKEN = "SP_KEY_USER_TOKEN";
    private static final String SP_KEY_USER_PHONE = "SP_KEY_USER_PHONE";

    private volatile static UserManager sUserManager;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (null == sUserManager) {
            synchronized (UserManager.class) {
                if (null == sUserManager) {
                    sUserManager = new UserManager();
                }
            }
        }
        return sUserManager;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(SPUtils.getInstance(SP_SPACE_KEY_USER_NAME).getString(SP_KEY_USER_TOKEN));
    }

    public void saveToken(String tokenStr) {
        if (TextUtils.isEmpty(tokenStr)) return;
        SPUtils.getInstance(SP_SPACE_KEY_USER_NAME).put(SP_KEY_USER_TOKEN, tokenStr, true);
    }

    public String getToken() {
        return SPUtils.getInstance(SP_SPACE_KEY_USER_NAME).getString(SP_KEY_USER_TOKEN, "");
    }

    public void clearToken() {
        SPUtils.getInstance(SP_SPACE_KEY_USER_NAME).put(SP_KEY_USER_TOKEN, "");
    }

    public void savePhoneNumber(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber)) return;
        SPUtils.getInstance(SP_SPACE_KEY_USER_NAME).put(SP_KEY_USER_PHONE, phoneNumber, true);
    }

    public String getPhoneNumber() {
        return SPUtils.getInstance(SP_SPACE_KEY_USER_NAME).getString(SP_KEY_USER_PHONE, "");
    }
}