package com.example.mytestapp.ui.news.home.fragment;

import com.example.mytestapp.Base.fragment.BaseListMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.ui.news.home.binder.NewsBinder;
import com.example.mytestapp.ui.news.home.domain.NewsResq;
import com.example.mytestapp.ui.news.home.presenter.NewsPresenter;
import com.example.mytestapp.ui.news.home.view.NewsViewI;

public class NewsFragment extends BaseListMvpFragment<NewsResq.StoriesBean> implements NewsViewI {

    NewsPresenter mPresenter;

    @Override
    protected void initView() {
        super.initView();

    }

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setTitle("资讯");
    }

    @Override
    protected void registerMultiType() {
        NewsBinder newsBinder = new NewsBinder();
        mAdapter.register(NewsResq.StoriesBean.class,newsBinder);
    }

    @Override
    protected void initPresenter() {
        mPresenter = PresenterProviders.of(this, NewsPresenter.class);
    }

    @Override
    protected void loadData(int page) {

        mPresenter.getNesDatas();
    }


    @Override
    public void getNewsDatasuccessed(NewsResq Datas) {
        loadDataSuccessList(Datas.getStories(),mCurrentPageNumber,false);
    }
}
