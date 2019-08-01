package com.example.mytestapp.Base.Title;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mytestapp.R;

public class TitleLayoutI extends LinearLayout implements View.OnClickListener{

    private ImageView mImageViewBack;
    private TextView mTextViewTitle;
    private TextView mTextViewRightText;
    private View mViewDividerLine;

    public TitleLayoutI(Context context) {
        this(context,null);
    }

    public TitleLayoutI(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_ui_title_layout,this);
        initView();
    }

    private void initView(){
        mImageViewBack = findViewById(R.id.iv_back);
        mImageViewBack.setOnClickListener(this);
        mTextViewTitle = findViewById(R.id.tv_title);
        mTextViewRightText = findViewById(R.id.tv_title_right);
        mTextViewRightText.setText("");
        mViewDividerLine = findViewById(R.id.v_line);
        this.setTitle(getActivityTitleStr());
    }

    private String getActivityTitleStr() {
        return this.getContext() instanceof Activity ? (String)((Activity)this.getContext()).getTitle() : null;
    }
    public void setRightText(String rightText) {
        if (!TextUtils.isEmpty(rightText)) {
            this.mTextViewRightText.setText(rightText);
        }

    }
    public void setTitle(String titleStr) {
        if (!TextUtils.isEmpty(titleStr)) {
            this.mTextViewTitle.setText(titleStr);
        }

    }
    public ImageView getmImageViewBack() {
        return mImageViewBack;
    }

    public TextView getmTextViewTitle() {
        return mTextViewTitle;
    }

    public TextView getmTextViewRightText() {
        return mTextViewRightText;
    }

    public View getmViewDividerLine() {
        return mViewDividerLine;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.iv_back && this.getContext() instanceof Activity) {
            ((Activity)this.getContext()).onBackPressed();
        }
    }
}
