package com.example.mytestapp.ui.homepage.home.fragment;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.Base.decoration.BaseItemDecoration;
import com.example.mytestapp.Base.fragment.BaseMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.homepage.home.binder.HomepageBinder;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp1;
import com.example.mytestapp.ui.homepage.home.presenter.HomePagePresenter;
import com.example.mytestapp.ui.homepage.home.view.HomepageViewI;
import com.example.mytestapp.ui.homepage.home.widget.ScrollingPictureLayout;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class HomepageFragment extends BaseMvpFragment implements HomepageViewI, View.OnClickListener {

    ScrollingPictureLayout scrollingPictureLayout;
    HomePagePresenter homePagePresenter;
    private RecyclerView recyclerView;
    protected MultiTypeAdapter mAdapter;

    Items items;


    @Override
    protected void initPresenter() {
        homePagePresenter = PresenterProviders.of(this, HomePagePresenter.class);
        loadData(1);
    }

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setTitle("热门");
        titleLayoutI.getmImageViewBack().setVisibility(View.GONE);
    }

    @Override
    protected void initView() {
        scrollingPictureLayout = (ScrollingPictureLayout) findViewById(R.id.scrolling_picture);
        findViewById(R.id.mBt_yiyuan).setOnClickListener(this);
        findViewById(R.id.mBt_yisheng).setOnClickListener(this);
        findViewById(R.id.mBt_jibing).setOnClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new BaseItemDecoration(1));
        items = new Items();
        mAdapter = new MultiTypeAdapter();
        mAdapter.setItems(items);
        mAdapter.register(HomepageResp1.ResultBean.DataBean.class, new HomepageBinder());
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    protected void loadData(int page) {
        homePagePresenter.getHomepageDatas("top");
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_homepage_layout;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mBt_yiyuan:
                showToast("点击了医院");
                break;
            case R.id.mBt_yisheng:
                showToast("点击了医生");

                break;
            case R.id.mBt_jibing:
                showToast("点击了疾病");
                break;
        }
    }

    @Override
    public void getHomepageDatassuccessed(HomepageResp1 homepageResp) {


        scrollingPictureLayout.PostDataToUI(homepageResp.getResult().getData().subList(0,5));
        items.clear();
        items.addAll(homepageResp.getResult().getData().subList(5,10));
        mAdapter.notifyDataSetChanged();
    }
}
