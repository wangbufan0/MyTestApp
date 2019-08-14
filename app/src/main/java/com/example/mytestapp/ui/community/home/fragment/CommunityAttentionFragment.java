package com.example.mytestapp.ui.community.home.fragment;

import android.view.View;

import com.example.mytestapp.Base.fragment.BaseListMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.ui.community.home.binder.CommunityAttentionBinder;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp1;
import com.example.mytestapp.ui.homepage.home.presenter.HomePagePresenter;
import com.example.mytestapp.ui.homepage.home.view.HomepageViewI;

public class CommunityAttentionFragment extends BaseListMvpFragment<HomepageResp1.ResultBean.DataBean> implements HomepageViewI {

    HomePagePresenter mPresenter;
    String s;

    public CommunityAttentionFragment(String s){
        super();
        this.s=s;
    }


    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setVisibility(View.GONE);
    }

    @Override
    protected void registerMultiType() {
        CommunityAttentionBinder communityAttentionBinder = new CommunityAttentionBinder();
        mAdapter.register(HomepageResp1.ResultBean.DataBean.class,communityAttentionBinder);
    }

    @Override
    protected void initPresenter() {

        mPresenter= PresenterProviders.of(this,HomePagePresenter.class);

    }

    @Override
    protected void loadData(int page) {
        mPresenter.getHomepageDatas(s);
    }



    public void refresh(){
        mRecyclerView.smoothScrollToPosition(0);
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void getHomepageDatassuccessed(HomepageResp1 homepageResp) {
        loadDataSuccessList(homepageResp.getResult().getData(),mCurrentPageNumber,false);
    }
}
