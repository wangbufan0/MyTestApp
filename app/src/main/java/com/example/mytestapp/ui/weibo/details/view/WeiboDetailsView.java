package com.example.mytestapp.ui.weibo.details.view;

import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.ui.weibo.details.domin.WeiboDetailsCommentResp;

/**
 * @Name: WeiboDetailsView
 * @Author: wangbufan
 * @Date: 2019/9/5 22:26
 * @Description:
 */
public interface WeiboDetailsView extends BaseView {

    void loadCommentSuccessed(WeiboDetailsCommentResp resp);

}
