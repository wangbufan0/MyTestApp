package com.example.mytestapp.ui.news.login.presenter;

import com.example.mytestapp.Base.presenter.BasePresenter;
import com.example.mytestapp.ui.news.login.view.LoginViewI;

public class LoginPresenter extends BasePresenter<LoginViewI> {

    public void requestSendAuthCode(String phoneNum) {
        if(phoneNum.equals("18158833227"))mMvpView.requestSendAuthCodeSuccess();

    }

    public void requestLogin(String phoneNum, String authCode) {
        if(phoneNum.equals("18158833227")&&authCode.equals("1"))mMvpView.loginSuccess();

    }

}

