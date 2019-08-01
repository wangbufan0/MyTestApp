package com.example.mytestapp.ui.homepage;

import android.view.View;

import com.example.mytestapp.Base.fragment.BaseMvpFragment;
import com.example.mytestapp.R;

public class HomepageFragment extends BaseMvpFragment {
    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setTitle("主页");
        titleLayoutI.getmImageViewBack().setVisibility(View.GONE);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void loadData(int page) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_homepage;
    }
}
