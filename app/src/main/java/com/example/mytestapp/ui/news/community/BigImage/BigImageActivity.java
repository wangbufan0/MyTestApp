package com.example.mytestapp.ui.news.community.BigImage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.Base.widget.MyBottomSheetDialog;
import com.example.mytestapp.R;
import com.example.mytestapp.load.image.LoadImage;
import com.example.mytestapp.utils.GLideUtil;
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


    public static void launch(Context context, int index, List<String> data) {

        Intent intent = new Intent(context, BigImageActivity.class);
        intent.putExtra("position", index);
        intent.putStringArrayListExtra("paths", (ArrayList<String>) data);
        context.startActivity(intent);
        ((Activity) context).overridePendingTransition(android.R.anim.fade_in, 0);

    }

    public static void launch(Context context, String data) {
        List<String> list = new ArrayList<>();
        list.add(data);
        launch(context, 0, list);

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
            public Object instantiateItem(@NonNull ViewGroup container, final int position) {
                View view = LayoutInflater.from(BigImageActivity.this).inflate(R.layout.item_bigimage_layout, null);
                icon = view.findViewById(R.id.pv_image);
                tvIndex = view.findViewById(R.id.tv_index);

                icon.setBackgroundColor(Color.parseColor("#000000"));
                if (position==0)
                    tvIndex.setVisibility(View.GONE);
                else
                    tvIndex.setText((position + 1) + "/" + paths.size());

                GLideUtil.loadImageViewLoding(container.getContext(),paths.get(position),icon);
                icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        overridePendingTransition(0, android.R.anim.fade_out);
                    }
                });
                icon.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        //下载图片
                        final MyBottomSheetDialog dialog= new MyBottomSheetDialog(icon.getContext(),"保存图片");
                        dialog.getViews().get(0).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                LoadImage.DownloadImage(icon.getContext(),paths.get(position));
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        return true;
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
