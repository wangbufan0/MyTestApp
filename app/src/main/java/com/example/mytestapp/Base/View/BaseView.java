package com.example.mytestapp.Base.View;

import androidx.lifecycle.LifecycleOwner;

public interface BaseView extends LifecycleOwner {

    void showToast(String s);

    void hideWaitDialog();

    void showWaitDialog();

    void showWaitDialog(String text);

}
