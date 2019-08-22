package com.example.mytestapp.ui.news.news.home.fragment;

import com.example.mytestapp.Base.fragment.BaseListMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.ui.news.home.domain.HomepageResp1;
import com.example.mytestapp.ui.news.home.presenter.HomePagePresenter;
import com.example.mytestapp.ui.news.home.view.HomepageViewI;
import com.example.mytestapp.ui.news.news.home.binder.NewsBinder;

public class NewsFragment extends BaseListMvpFragment<HomepageResp1.ResultBean.DataBean> implements HomepageViewI {

    HomePagePresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setTitle("社会");
    }

    @Override
    protected void registerMultiType() {
        NewsBinder newsBinder = new NewsBinder();
        mAdapter.register(HomepageResp1.ResultBean.DataBean.class,newsBinder);
    }

    @Override
    protected void initPresenter() {
        mPresenter = PresenterProviders.of(this, HomePagePresenter.class);
        loadData(1);
    }

    @Override
    protected void loadData(int page) {
        mPresenter.getHomepageDatas("shehui");
    }

    public void refresh(){
        mRefreshLayout.autoRefresh();
    }

    @Override
    public void getHomepageDatassuccessed(HomepageResp1 homepageResp) {
        loadDataSuccessList(homepageResp.getResult().getData(),mCurrentPageNumber,false);
    }
}
