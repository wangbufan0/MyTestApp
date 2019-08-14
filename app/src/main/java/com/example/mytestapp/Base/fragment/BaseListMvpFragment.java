package com.example.mytestapp.Base.fragment;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.Base.decoration.BaseItemDecoration;
import com.example.mytestapp.R;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public abstract class BaseListMvpFragment<T> extends BaseMvpFragment {

    public RefreshLayout mRefreshLayout;
    protected RecyclerView mRecyclerView;
    protected MultiTypeAdapter mAdapter;
    protected Items items;
    protected int mCurrentPageNumber = 1;

    @Override
    protected void initView() {
        initRecyclerView();
        mAdapter=new MultiTypeAdapter();
        items=new Items();
        mAdapter.setItems(items);
        registerMultiType();
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void initRecyclerView() {
        mRefreshLayout = (RefreshLayout) findViewById(R.id.common_refreshLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.common_recyclerView);
        mRecyclerView.addItemDecoration(new BaseItemDecoration(1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRefreshLayout.setEnableRefresh(true);
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                loadData(++mCurrentPageNumber);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mCurrentPageNumber = 1;
                loadData(mCurrentPageNumber);
            }

        });
        statusLayoutManager.showLoadingLayout();
    }

    protected abstract void registerMultiType();



    public void loadDataSuccessList(List<T> newListData, int currentPage, boolean hasNext) {
        refreshComplete();
        mRefreshLayout.setEnableLoadMore(hasNext);


        if (newListData == null) return;
        if (isRefreshAction(currentPage)) {
            //下拉刷新操作,清除现有数据
            items.clear();
        }
        //添加数据
        items.addAll(newListData);
        if (items.isEmpty()) {
            statusLayoutManager.showEmptyLayout();
        } else {
            statusLayoutManager.showSuccessLayout();
        }
        mAdapter.notifyDataSetChanged();
    }


    /**
     * @return 是否是刷新行为
     */
    private boolean isRefreshAction(int currentPage) {
        return currentPage == 1;
    }

    protected void refreshComplete() {
        mRefreshLayout.finishLoadMore();
        mRefreshLayout.finishRefresh();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_base_list_layout;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(mCurrentPageNumber);
    }
}
