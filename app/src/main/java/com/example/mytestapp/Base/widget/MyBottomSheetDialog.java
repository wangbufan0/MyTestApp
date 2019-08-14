package com.example.mytestapp.Base.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.mytestapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author : wangbufan
 * @Time : 11:40 2019-08-14
 * @description :
 * --------------------------------------
 */
public class MyBottomSheetDialog extends BottomSheetDialog {


    List<View> views;

    public List<View> getViews() {
        return views;
    }

    public MyBottomSheetDialog(@NonNull Context context, String... s) {
        super(context);


        LinearLayout linearLayout=new LinearLayout(context);

        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                                        LinearLayout.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        views=new ArrayList<>();
        for(String s1: s){
            View view =getLayoutInflater().inflate(R.layout.item_dialog_select_layout,null);
            TextView tv= view.findViewById(R.id.text_view);
            tv.setText(s1);
            views.add(tv);
            linearLayout.addView(view);
        }
        View view =getLayoutInflater().inflate(R.layout.item_dialog_select_layout,null);
        TextView tv= view.findViewById(R.id.text_view);
        tv.setText("取消");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        linearLayout.addView(view);
        setContentView(linearLayout);
    }
}
