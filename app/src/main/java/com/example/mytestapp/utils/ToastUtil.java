package com.example.mytestapp.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
    private static Toast toast;

    public static void showToast(Context context,String s){
        if(toast==null){
            toast=Toast.makeText(context,s,Toast.LENGTH_SHORT);
        }else{
            toast.setText(s);
        }
        toast.show();

    }
}
