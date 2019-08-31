package com.example.mytestapp.ui.weibo.homepage;

import com.example.mytestapp.Base.fragment.BaseListMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.ui.weibo.homepage.binder.WeiboHomepageBinder;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;
import com.example.mytestapp.ui.weibo.homepage.presenter.WeiboHomePagePresenter;
import com.example.mytestapp.ui.weibo.homepage.view.WeiboHomepageView;

import java.util.List;

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
        WeiboHomepageResp.StatusesBean data = null;
        if(page==1){
            if(!items.isEmpty())
                data= (WeiboHomepageResp.StatusesBean) items.get(0);
            presenter.getData(data==null?0:data.getId(),0);
        }else{
            if(!items.isEmpty())
                data= (WeiboHomepageResp.StatusesBean) items.get(items.size()-1);
            presenter.getData(0,data==null?0:data.getId());
        }

    }

    @Override
    public void getDataSuccessed(WeiboHomepageResp s) {
        boolean hasNext=false;
        if(s.getStatuses().size()==20)hasNext=true;
        if(mCurrentPageNumber==1)hasNext=true;
        loadDataSuccessList(s.getStatuses(),mCurrentPageNumber,hasNext);
    }

    @Override
    public void loadDataSuccessList(List<WeiboHomepageResp.StatusesBean> newListData, int currentPage, boolean hasNext) {
        refreshComplete();
        mRefreshLayout.setEnableLoadMore(hasNext);
        if (newListData.isEmpty()){
            if(currentPage==1){
                showToast("没有新微博");
            }else{
                showToast("没有更多了");
            }
            return;
        }
        if (currentPage==1) {
            //下拉刷新操作,清除现有数据
            items.addAll(0,newListData);
        }else{
            items.addAll(newListData.subList(1,newListData.size()-1));
        }
        if (items.isEmpty()) {
            statusLayoutManager.showEmptyLayout();
        } else {
            statusLayoutManager.showSuccessLayout();
        }
        mAdapter.notifyDataSetChanged();
    }
}
