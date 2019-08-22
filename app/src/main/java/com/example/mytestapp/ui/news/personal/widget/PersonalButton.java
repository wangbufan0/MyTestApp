package com.example.mytestapp.ui.news.personal.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.core.content.res.ResourcesCompat;

import com.example.mytestapp.R;

public class PersonalButton extends LinearLayout {

    private Button btn;
    private View mv;


    public PersonalButton(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.person_button_layout, this, true);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PersonalButton);

        Boolean isbootom = ta.getBoolean(R.styleable.PersonalButton_show_bottomline, true);

        this.setClickable(true);
        btn = this.findViewById(R.id.bt);

        if (!isbootom) {
            mv = findViewById(R.id.item_xian);
            mv.setVisibility(View.GONE);
        }
        btn.setText(ta.getString(R.styleable.PersonalButton_show_text));

        Integer integer = ta.getResourceId(R.styleable.PersonalButton_show_leftimg, 0);
        Drawable drawable_left;
        if (integer != 0) {
            drawable_left = ResourcesCompat.getDrawable(getResources(), integer, null);
            drawable_left.setBounds(0, 0, drawable_left.getMinimumWidth(), drawable_left.getMinimumHeight());
        } else
            drawable_left = null;

        Drawable drawable_right = ResourcesCompat.getDrawable(getResources(), R.drawable.icon_arrow_right, null);
        drawable_right.setBounds(0, 0, drawable_right.getMinimumWidth(), drawable_right.getMinimumHeight());
        btn.setCompoundDrawables(drawable_left, null, drawable_right, null);
        ta.recycle();
    }


    @Override
    public void setOnClickListener(OnClickListener l) {
        btn.setOnClickListener(l);
    }


}
