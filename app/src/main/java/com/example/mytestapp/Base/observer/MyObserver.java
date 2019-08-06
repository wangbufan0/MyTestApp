package com.example.mytestapp.Base.observer;


import android.text.TextUtils;

import com.example.mytestapp.Base.presenter.BasePresenter;

import rx.Subscriber;

public abstract class MyObserver<T> extends Subscriber<T> {
    private BasePresenter mBasePresenter;
    private boolean isShowLoading = false;  //默认不弹loading


    public MyObserver(BasePresenter pBasePresenter) {
        this.mBasePresenter = pBasePresenter;
    }

    public MyObserver(BasePresenter pBasePresenter, boolean isShowLoading) {
        this.mBasePresenter = pBasePresenter;
        this.isShowLoading = isShowLoading;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        if (isDestroy()) return;
        mBasePresenter.getView().hideWaitDialog();
    }

    @Override
    public void onNext(T t) {
        if (isDestroy()) return;
        if (isShowLoading && null != mBasePresenter.getView()) {
            mBasePresenter.getView().hideWaitDialog();
        }
        this.onSuccess(t);
    }
    public abstract void onSuccess(T t);

    private boolean isDestroy() {
        return mBasePresenter == null || mBasePresenter.isDestroy();
    }

    @Override
    public void onStart() {
        if (isShowLoading && null != mBasePresenter.getView()) {
            if (TextUtils.isEmpty(getLoadingMsg())) {
                mBasePresenter.getView().showWaitDialog();
            } else {
                mBasePresenter.getView().showWaitDialog(getLoadingMsg());
            }
        }
    }

    public String getLoadingMsg() {
        return null;
    }


}
