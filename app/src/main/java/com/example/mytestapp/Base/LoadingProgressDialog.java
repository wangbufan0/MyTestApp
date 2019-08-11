package com.example.mytestapp.Base;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;

import com.example.mytestapp.R;



public class LoadingProgressDialog extends Dialog {
    public LoadingProgressDialog(@NonNull Context context) {
        this(context, 0);
    }

    public LoadingProgressDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setCancelable(false);
        setContentView(R.layout.ui_loading_layout);
    }
}
