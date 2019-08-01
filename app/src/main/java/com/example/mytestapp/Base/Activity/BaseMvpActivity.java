package com.example.mytestapp.Base.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytestapp.Base.Title.TitleLayoutI;
import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.R;
import com.example.mytestapp.utils.ToastUtil;

public abstract class BaseMvpActivity extends AppCompatActivity implements BaseView {

    protected TitleLayoutI titleLayoutI;
    protected FrameLayout rootView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_cativity_base);
        rootView = findViewById(R.id.common_root_view);
        parseArguments();
        rootView.addView(inflate(getLayoutRes()), new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initBar();
        initView();
        initPresenter();
    }
    protected View inflate(@LayoutRes int resource) {
        if (resource == 0) return null;
        return getLayoutInflater().inflate(resource, null);
    }

    protected abstract void initPresenter();


    protected void initBar() {
        titleLayoutI = findViewById(R.id.common_toolbar);
    }


    @Override
    public void showToast(String s) {
        ToastUtil.showToast(this,s);
    }


    protected void parseArguments() {
    }

    protected abstract void initView();

    protected abstract void loadData(int page);

    protected abstract int getLayoutRes();

}
