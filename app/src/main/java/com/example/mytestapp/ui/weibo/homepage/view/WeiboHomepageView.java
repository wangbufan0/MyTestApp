package com.example.mytestapp.ui.weibo.homepage.view;

import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;

/**
 * @Name: WeiboHomepageView
 * @Author: wangbufan
 * @Date: 2019/8/27 10:53
 * @Description:
 */
public interface WeiboHomepageView extends BaseView {

    void getDataSuccessed(WeiboHomepageResp s);

}
