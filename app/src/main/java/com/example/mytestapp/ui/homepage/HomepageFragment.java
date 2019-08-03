package com.example.mytestapp.ui.homepage;

import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.Base.decoration.BaseItemDecoration;
import com.example.mytestapp.Base.fragment.BaseMvpFragment;
import com.example.mytestapp.Base.presenter.PresenterProviders;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.homepage.presenter.HomePagePresenter;
import com.example.mytestapp.ui.homepage.view.HomepageViewI;
import com.example.mytestapp.ui.homepage.widget.ScrollingPictureLayout;
import com.example.mytestapp.ui.news.binder.NewsBinder;

import java.util.ArrayList;
import java.util.List;

import me.drakeet.multitype.MultiTypeAdapter;

public class HomepageFragment extends BaseMvpFragment implements HomepageViewI,View.OnClickListener {

    ScrollingPictureLayout scrollingPictureLayout ;
    HomePagePresenter homePagePresenter;
    private RecyclerView recyclerView;
    private Button yiyuan, yisheng ,jibing ;
    protected MultiTypeAdapter mAdapter;
    protected List<String> listData = new ArrayList<>();



    @Override
    protected void initPresenter() {
        homePagePresenter= PresenterProviders.of(this,HomePagePresenter.class);
    }

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setTitle("主页");
        titleLayoutI.getmImageViewBack().setVisibility(View.GONE);
    }

    @Override
    protected void initView() {
        scrollingPictureLayout= (ScrollingPictureLayout) findViewById(R.id.scrolling_picture);
        ((Button) findViewById(R.id.mBt_yiyuan)).setOnClickListener(this);
        ((Button) findViewById(R.id.mBt_yisheng)).setOnClickListener(this);
        ((Button) findViewById(R.id.mBt_jibing)).setOnClickListener(this);
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new BaseItemDecoration(1));
        mAdapter=new MultiTypeAdapter();
        mAdapter.setItems(listData);
        NewsBinder newsBinder=new NewsBinder();
     //   mAdapter.register(String.class, newsBinder);
     //   recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void loadData(int page){
        homePagePresenter.getScrollingPicture();
        homePagePresenter.getNewsData();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_homepage_layout;
    }

    @Override
    public void getScrollingPictureSuccessed(List<String> urlPucture) {
        scrollingPictureLayout.PostDataToUI(urlPucture);
    }

    @Override
    public void getNewsDatasuccessed(List<String> Datas) {
        listData.addAll(Datas);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData(1);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
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
}
