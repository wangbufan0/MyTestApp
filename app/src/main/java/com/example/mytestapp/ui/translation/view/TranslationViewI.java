package com.example.mytestapp.ui.translation.view;

import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.ui.translation.domain.TranslationResp;

/**
 * @Name: TranslationViewI
 * @Author: wangbufan
 * @Date: 2019/8/21 22:48
 * @Description:
 */
public interface TranslationViewI extends BaseView{
    void getDataSuccess(TranslationResp resp);
}
