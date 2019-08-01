package com.example.mytestapp.ui.news.fragment;

import com.example.mytestapp.Base.fragment.BaseListMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.ui.news.binder.NewsBinder;
import com.example.mytestapp.ui.news.presenter.NewsPresenter;
import com.example.mytestapp.ui.news.view.NewsViewI;

import java.util.List;

public class NewsFragment extends BaseListMvpFragment<String> implements NewsViewI {

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
        mAdapter.register(String.class,newsBinder);
    }

    @Override
    protected void initPresenter() {
        mPresenter = PresenterProviders.of(this, NewsPresenter.class);
    }

    @Override
    protected void loadData(int page) {
        mPresenter.getNewsData();
    }


    @Override
    public void getNewsDatasuccessed(List<String> Datas) {
            loadDataSuccessList(Datas,mCurrentPageNumber,false);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(1);
    }
}
