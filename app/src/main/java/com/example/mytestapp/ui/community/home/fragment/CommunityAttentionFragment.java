package com.example.mytestapp.ui.community.home.fragment;

import android.view.View;

import com.example.mytestapp.Base.fragment.BaseListMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.ui.community.home.binder.CommunityAttentionBinder;
import com.example.mytestapp.ui.community.home.domain.CommunityAttentionResp;
import com.example.mytestapp.ui.community.home.presenter.CommunityAttentionPresenter;
import com.example.mytestapp.ui.community.home.view.CommunityAttentionViewI;

import java.util.List;

public class CommunityAttentionFragment extends BaseListMvpFragment<CommunityAttentionResp> implements CommunityAttentionViewI {

    CommunityAttentionPresenter mPresenter;


    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setVisibility(View.GONE);
    }

    @Override
    protected void registerMultiType() {
        CommunityAttentionBinder communityAttentionBinder = new CommunityAttentionBinder();
        mAdapter.register(CommunityAttentionResp.class,communityAttentionBinder);
    }

    @Override
    protected void initPresenter() {

        mPresenter= PresenterProviders.of(this,CommunityAttentionPresenter.class);

    }

    @Override
    protected void loadData(int page) {
        mPresenter.loadData();
    }


    @Override
    public void getDataSuccessed(List<CommunityAttentionResp> data) {
        loadDataSuccessList(data,mCurrentPageNumber,false);
    }

    public void refresh(){
        mRecyclerView.smoothScrollToPosition(0);
        mRefreshLayout.autoRefresh();
    }
}
