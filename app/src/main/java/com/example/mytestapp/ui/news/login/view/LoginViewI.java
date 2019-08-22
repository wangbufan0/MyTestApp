package com.example.mytestapp.ui.news.login.view;

import com.example.mytestapp.Base.View.BaseView;


/**
 * Date:  2019-06-02
 * Time:  10:24
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public interface LoginViewI extends BaseView {
    void loginSuccess();

    void requestSendAuthCodeSuccess();
}
