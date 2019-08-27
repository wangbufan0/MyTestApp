package com.example.mytestapp.ui.weibo.domain;

import com.blankj.utilcode.util.ToastUtils;
import com.example.mytestapp.manager.weibo.WeiboManager;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;

/**
 * @Name: SelfWbAuthListener
 * @Author: wangbufan
 * @Date: 2019/8/26 22:52
 * @Description:
 */
public class SelfWbAuthListener implements WbAuthListener {
    @Override
    public void onSuccess(Oauth2AccessToken token) {
        WeiboManager.getInstance().saveToken(token.getToken());
        WeiboManager.getInstance().savePhoneNumber(token.getPhoneNum());
    }

    @Override
    public void cancel() {
        ToastUtils.showShort("cancel");

    }

    @Override
    public void onFailure(WbConnectErrorMessage errorMessage) {
        ToastUtils.showShort(errorMessage.getErrorMessage()+errorMessage.getErrorCode());
    }
}
