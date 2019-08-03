package com.example.mytestapp.ui.homepage.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.mytestapp.R;

import java.util.ArrayList;
import java.util.List;

public class ScrollingPictureLayout extends LinearLayout {

    private ViewPager viewPager;
    private  Context context;
    private LinearLayout pointGroup;



    // 图片资源
    private ArrayList<ImageView> imageList;
    private List<String> datas;
    protected int lastPosition;


    public ScrollingPictureLayout(Context context) {
       this(context,null);
    }

    public ScrollingPictureLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.common_scrollingpicture_layout,this,true);
        this.context=context;
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pointGroup = (LinearLayout)findViewById(R.id.point_group);

    }



    private boolean isRunning = false;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 让viewPager 滑动到下一页
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isRunning) {
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        };
    };


    private class pagerImageClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Toast.makeText(context,String.valueOf(lastPosition),Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRunning=false;
    }

    public void PostDataToUI(List<String> datas){
        this.datas=datas;
        pointGroup.removeAllViews();

        pointGroup.bringToFront();
        imageList = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) { // 初始化图片资源
            ImageView image = new ImageView(context);
            //image.setBackgroundResource(imageIds[i]);
            Glide.with(context)
                    .load(datas.get(i))
                    .into(image);
            image.setOnClickListener(new pagerImageClick());
            imageList.add(image);

            // 添加指示点
            ImageView point = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.rightMargin = 20;
            point.setLayoutParams(params);

            point.setBackgroundResource(R.drawable.bg_scrolling_picture_point);
            if (i == 0) { point.setEnabled(true);
            } else { point.setEnabled(false);
            }
            pointGroup.addView(point);
        }

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            /**
             * 获得页面的总数
             */
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @NonNull
            @Override
            /**
             * 获得相应位置上的view
             * container  view的容器，其实就是viewpager自身
             * position    相应的位置
             */
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(imageList.get(position % imageList.size()));
                // 返回一个和该view相对的object
                return imageList.get(position % imageList.size());
            }
            @Override
            /**
             * 判断 view和object的对应关系
             */
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view==o;
            }
            @Override
            /**
             * 销毁对应位置上的object
             */
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            /**
             * 页面正在滑动的时候，回调*/
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            /* *
             * 页面切换后调用
             * position  新的页面位置*/

            public void onPageSelected(int position) {
                position = position % imageList.size();
                // 改变指示点的状态
                // 把当前点enbale 为true
                pointGroup.getChildAt(position).setEnabled(true);
                // 把上一个点设为false
                pointGroup.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;
            }
            @Override
            /* *
             * 当页面状态发生变化的时候，回调*/

            public void onPageScrollStateChanged(int i) {
                //handler.removeMessages(0);
            }
        });
        /*
         * 自动循环： 1、定时器：Timer 2、开子线程 while true 循环 3、ColckManager 4、 用handler
         * 发送延时信息，实现循环
         */
        isRunning = true;
        // 设置图片的自动滑动
        handler.sendEmptyMessageDelayed(0, 3000);


    }
}
