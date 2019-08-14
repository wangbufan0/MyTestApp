package com.example.mytestapp.Base.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytestapp.Base.LoadingProgressDialog;
import com.example.mytestapp.Base.Title.TitleLayoutI;
import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.R;
import com.example.mytestapp.utils.ToastUtil;

import me.bakumon.statuslayoutmanager.library.DefaultOnStatusChildClickListener;
import me.bakumon.statuslayoutmanager.library.StatusLayoutManager;

/**
 * @Date : 2019-08-12
 * @Time : 15:06
 * @author : wangbufan
 *-------------------------------
 */


public abstract class BaseMvpActivity extends AppCompatActivity implements BaseView {

    protected TitleLayoutI titleLayoutI;
    protected FrameLayout rootView;
    protected StatusLayoutManager statusLayoutManager;
    protected LoadingProgressDialog progressDialog;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_cativity_base);
        rootView = findViewById(R.id.common_root_view);
        parseArguments();
        rootView.addView(inflate(getLayoutRes()),
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        initStatusLayout();
        initBar();
        initView();
        initPresenter();
    }


    protected View inflate(@LayoutRes int resource) {
        if (resource == 0) return null;
        return getLayoutInflater().inflate(resource, null);
    }


    @Override
    public void showToast(String s) {
        ToastUtil.showToast(this, s);
    }


    @Override
    public void hideWaitDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showWaitDialog() {
        showWaitDialog("");
    }

    @Override
    public void showWaitDialog(String text) {
        if (progressDialog == null) {
            progressDialog = new LoadingProgressDialog(this);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }


    protected void initBar() {
        titleLayoutI = findViewById(R.id.common_toolbar);
    }

    protected void initStatusLayout() {
        // 设置重试事件监听器
        statusLayoutManager = new StatusLayoutManager.Builder(rootView)
                .setLoadingLayout(inflate(R.layout.common_layout_loading))
                .setEmptyLayout(inflate(R.layout.common_layout_empty))
                .setEmptyClickViewID(R.id.common_empty_box)
                .setErrorLayout(inflate(R.layout.common_layout_error))
                .setErrorClickViewID(R.id.common_error_box)
                // 设置重试事件监听器
                .setOnStatusChildClickListener(new DefaultOnStatusChildClickListener() {
                    @Override
                    public void onEmptyChildClick(View view) {
                        statusLayoutManager.showLoadingLayout();
                        loadData(1);
                    }

                    @Override
                    public void onErrorChildClick(View view) {
                        statusLayoutManager.showLoadingLayout();
                        loadData(1);
                    }
                })
                .build();
    }


    protected void parseArguments() {
    }

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract void loadData(int page);

    protected abstract int getLayoutRes();
}
