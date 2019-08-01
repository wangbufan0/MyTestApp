package com.example.mytestapp.Base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mytestapp.Base.Title.TitleLayoutI;
import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.R;
import com.example.mytestapp.utils.ToastUtil;

public abstract class BaseMvpFragment extends Fragment implements BaseView {

    protected TitleLayoutI titleLayoutI;
    protected FrameLayout rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.common_cativity_base,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parseArguments();
        rootView = view.findViewById(R.id.common_root_view);
        rootView.addView(inflate(getLayoutRes()), new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        initStatusLayout();
        initBar();
        initView();
        initPresenter();
    }

    @Override
    public void showToast(String s) {
        ToastUtil.showToast(getContext(),s);
    }

    public View findViewById(int idRes){return getView().findViewById(idRes);}

    protected void initStatusLayout(){

    }

    protected View inflate(@LayoutRes int resource) {
        if (resource == 0) return null;
        return getLayoutInflater().inflate(resource, null);
    }

    protected void parseArguments() {
    }

    protected  void initBar() {
        titleLayoutI = getView().findViewById(R.id.common_toolbar);
        titleLayoutI.getmImageViewBack().setVisibility(View.GONE);
    }

    protected abstract void initPresenter();

    protected abstract void initView();

    protected abstract void loadData(int page);

    protected abstract int getLayoutRes();
}
