package com.example.mytestapp.ui.weibo.details.fragment;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.mytestapp.Base.fragment.BaseListMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.ui.weibo.details.WeiboDetailsActivity;
import com.example.mytestapp.ui.weibo.details.binder.WeiboDetailsCommentBinder;
import com.example.mytestapp.ui.weibo.details.domin.WeiboDetailsCommentResp;
import com.example.mytestapp.ui.weibo.details.presenter.WeiboDetailsPresenter;

/**
 * @Name: WeiboDetailsCommentfragment
 * @Author: wangbufan
 * @Date: 2019/9/5 21:55
 * @Description:
 */
public class WeiboDetailsCommentfragment extends BaseListMvpFragment<WeiboDetailsCommentResp.CommentsBean> {

    WeiboDetailsPresenter presenter;
    private long id;

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setVisibility(View.GONE);
    }

    @Override
    protected void registerMultiType() {
        WeiboDetailsCommentBinder binder=new WeiboDetailsCommentBinder();
        mAdapter.register(WeiboDetailsCommentResp.CommentsBean.class  , binder);
    }

    @Override
    protected void parseArguments() {
        id=((WeiboDetailsActivity)ActivityUtils.getTopActivity()).id;
    }

    @Override
    protected void initPresenter() {
        presenter= PresenterProviders.of((WeiboDetailsActivity)ActivityUtils.getActivityByContext(getContext()),WeiboDetailsPresenter.class);
        loadData(1);
    }

    @Override
    protected void loadData(int page) {
        presenter.getCommentDatas(id);
    }

    public void postData2UI(WeiboDetailsCommentResp resp){
        loadDataSuccessList(resp.getComments(),mCurrentPageNumber,false);
    }


}
