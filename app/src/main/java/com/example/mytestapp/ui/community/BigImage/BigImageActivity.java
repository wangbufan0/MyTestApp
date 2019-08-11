package com.example.mytestapp.ui.community.BigImage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.R;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.List;

public class BigImageActivity extends BaseMvpActivity {
    private ViewPager vpImage;
    private PhotoView icon;
    private TextView tvIndex;
    //接受Intent传递的参数
    private int position;
    private List<String> paths;//存放图片url的数组


    public static void launch(Context context,int index,List<String> data) {

        Intent intent = new Intent(context, BigImageActivity.class);
        intent.putExtra("position", index);
        intent.putStringArrayListExtra("paths", (ArrayList<String>) data);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, 0);

    }


    @Override
    protected void initBar() {
        super.initBar();
        titleLayoutI.setVisibility(View.GONE);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);

        paths = intent.getStringArrayListExtra("paths");

        vpImage = findViewById(R.id.mVp);

        vpImage.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return paths == null ? 0 : paths.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View view = LayoutInflater.from(BigImageActivity.this).inflate(R.layout.item_bigimage_layout, null);
                icon = view.findViewById(R.id.pv_image);
                tvIndex = view.findViewById(R.id.tv_index);
                icon.setBackgroundColor(getResources().getColor(R.color.color_hei));
                tvIndex.setText((position + 1) + "/" + paths.size());

                Glide.with(BigImageActivity.this)
                        .load(paths.get(position))
                        .diskCacheStrategy(DiskCacheStrategy.ALL)//缓存模式
                        .into(icon);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        overridePendingTransition(0, android.R.anim.fade_out);
                    }
                });
                container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        vpImage.setCurrentItem(position, true);

    }

    @Override
    protected void loadData(int page) {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_bigimage_layout;
    }


}
