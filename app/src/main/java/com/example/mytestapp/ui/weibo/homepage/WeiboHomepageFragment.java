package com.example.mytestapp.ui.weibo.homepage;

import com.example.mytestapp.Base.fragment.BaseListMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.ui.weibo.homepage.binder.WeiboHomepageBinder;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;
import com.example.mytestapp.ui.weibo.homepage.presenter.WeiboHomePagePresenter;
import com.example.mytestapp.ui.weibo.homepage.view.WeiboHomepageView;

/**
 * @Name: WeiboHomepageFragment
 * @Author: wangbufan
 * @Date: 2019/8/27 21:41
 * @Description:
 */
public class WeiboHomepageFragment extends BaseListMvpFragment<WeiboHomepageResp.StatusesBean> implements WeiboHomepageView {
    private WeiboHomePagePresenter presenter;
    @Override
    protected void registerMultiType() {
        mAdapter.register(WeiboHomepageResp.StatusesBean.class,new WeiboHomepageBinder());
    }

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setTitle("微博");
    }

    @Override
    protected void initPresenter() {
        presenter= PresenterProviders.of(this, WeiboHomePagePresenter.class);
        loadData(1);
    }

    @Override
    protected void loadData(int page) {
        presenter.getData();
    }

    @Override
    public void getDataSuccessed(WeiboHomepageResp s) {
        loadDataSuccessList(s.getStatuses(),mCurrentPageNumber,false);
    }
}
