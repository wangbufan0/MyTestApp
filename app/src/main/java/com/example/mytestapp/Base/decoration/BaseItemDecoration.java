package com.example.mytestapp.Base.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.utils.SizeUtil;

public class BaseItemDecoration extends RecyclerView.ItemDecoration {

    private int topSpace;

    public BaseItemDecoration() {
        this.topSpace = SizeUtil.dp2px(5);
    }

    public BaseItemDecoration(int topSpace) {
        this.topSpace = topSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.top = topSpace;
    }
}
