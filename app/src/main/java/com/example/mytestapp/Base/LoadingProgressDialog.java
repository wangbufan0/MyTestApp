package com.example.mytestapp.Base;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import androidx.annotation.NonNull;

import com.example.mytestapp.R;


/**
 * Date:  2019-06-06
 * Time:  14:54
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
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
