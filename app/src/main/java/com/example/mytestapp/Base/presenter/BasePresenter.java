package com.example.mytestapp.Base.presenter;

import androidx.annotation.NonNull;

import com.example.mytestapp.Base.View.BaseView;

import org.jetbrains.annotations.Nullable;

public class BasePresenter<V extends BaseView> {
    @Nullable
    protected V mMvpView;


    protected final void attachToView(@NonNull V view) {
        this.mMvpView = view;
        onAttachToView(view);
    }

    protected void onAttachToView(@NonNull V view) {

    }

    protected final void detachFromView() {
        this.mMvpView = null;
        onDetachFromView();
    }

    protected void onDetachFromView() {

    }

    @Nullable
    public final V getView() {
        return mMvpView;
    }

    public boolean isDestroy() {
        return mMvpView == null;
    }
}
