package com.example.mytestapp.ui.weibo.details.fragment;

import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.example.mytestapp.Base.fragment.BaseListMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.ui.weibo.details.WeiboDetailsActivity;
import com.example.mytestapp.ui.weibo.details.binder.WeiboDetailsCommentBinder;
import com.example.mytestapp.ui.weibo.details.domin.WeiboDetailsCommentResp;
import com.example.mytestapp.ui.weibo.details.presenter.WeiboDetailsPresenter;

import java.util.List;

/**
 * @Name: WeiboDetailsCommentfragment
 * @Author: wangbufan
 * @Date: 2019/9/5 21:55
 * @Description:
 */
public class WeiboDetailsAttitudefragment extends BaseListMvpFragment<WeiboDetailsCommentResp.CommentsBean> {

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
        long since = 0,max=0;
        if(!items.isEmpty()){
            if(page==1){
                since=((WeiboDetailsCommentResp.CommentsBean)items.get(0)).getId();
            }else{
                max=((WeiboDetailsCommentResp.CommentsBean)items.get(items.size()-1)).getId();
            }
        }
        presenter.getCommentDatas(id,since,max);
    }

    public void postData2UI(WeiboDetailsCommentResp resp){
        boolean hasnext=true;
       // if(resp.getComments().isEmpty())hasnext=false;
        loadDataSuccessList(resp.getComments(),mCurrentPageNumber,hasnext);
    }

    @Override
    public void loadDataSuccessList(List<WeiboDetailsCommentResp.CommentsBean> newListData, int currentPage, boolean hasNext) {

        refreshComplete();
        mRefreshLayout.setEnableLoadMore(hasNext);


        if (newListData == null) return;

        //添加数据
        if(currentPage==1){
            items.addAll(0,newListData);
        }else
            items.addAll(newListData);
        if (items.isEmpty()) {
            statusLayoutManager.showEmptyLayout();
        } else {
            statusLayoutManager.showSuccessLayout();
        }
        mAdapter.notifyDataSetChanged();


    }
}
