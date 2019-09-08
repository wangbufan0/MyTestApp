package com.example.mytestapp.ui.weibo.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mytestapp.Base.Activity.BaseMvpActivity;
import com.example.mytestapp.Base.clickablespan.CustomUrlSpan;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.news.community.BigImage.BigImageActivity;
import com.example.mytestapp.ui.weibo.details.domin.WeiboDetailsCommentResp;
import com.example.mytestapp.ui.weibo.details.fragment.WeiboDetailsCommentfragment;
import com.example.mytestapp.ui.weibo.details.view.WeiboDetailsView;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;
import com.example.mytestapp.ui.weibo.util.NineImageViewAdapter;
import com.example.mytestapp.ui.weibo.util.WeiboImageUrlUtil;
import com.example.mytestapp.utils.GLideUtil;
import com.google.android.material.tabs.TabLayout;
import com.jaeger.ninegridimageview.NineGridImageView;

import java.util.List;

/**
 * @Name: WeiboDetailsActivity
 * @Author: wangbufan
 * @Date: 2019/8/31 16:43
 * @Description:
 */
public class WeiboDetailsActivity extends BaseMvpActivity implements WeiboDetailsView {

    private ImageView IvAvatar;
    private TextView TvName;
    private TextView TvTime;
    private  TextView tvText;
    private List<String> imageUrls;
    private String avatarUrl,Name,Time,Text;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public long id;
    private String[] titles = {"评论","转发","点赞"};
    private Fragment[] fragments=new Fragment[1];

    public static void launch(Context context, WeiboHomepageResp.StatusesBean data){
        Intent intent=new Intent(context,WeiboDetailsActivity.class);
        Bundle bundle=new Bundle();
        bundle.putSerializable("detailsData",data);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    protected void parseArguments() {
        WeiboHomepageResp.StatusesBean data= (WeiboHomepageResp.StatusesBean) getIntent().getSerializableExtra("detailsData");
        imageUrls =WeiboImageUrlUtil.getMiddleImageURL(data.getPic_urls());
        avatarUrl =data.getUser().getAvatar_large();
        Name = data.getUser().getName();
        Time = data.getCreated_at();
        Text = data.getText();
        id=data.getId();
        titles[1]="点赞："+data.getAttitudes_count()+"人";
        titles[0]="评论："+data.getComments_count()+"人";
        titles[2]="转发："+data.getReposts_count()+"人";
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initView() {
        IvAvatar = findViewById(R.id.iv_avatar);
        TvName = findViewById(R.id.tv_name);
        TvTime = findViewById(R.id.tv_time);
        tvText = findViewById(R.id.tv_text);
        imageChange();
        GLideUtil.loadImageViewRound(this, avatarUrl, IvAvatar);
        TvName.setText(Name);
        TvTime.setText(Time);
        tvText.setText(Text);
        interceptHyperLink(tvText);

        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.viewpager);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if(fragments[position]==null)fragments[position]=new WeiboDetailsCommentfragment();
                return fragments[position];
            }
            @Override
            public int getCount() {
                return 1;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }




    @Override
    protected void loadData(int page) {

    }

    private void imageChange(){
        LinearLayout linearLayout=findViewById(R.id.ll_image);
        List<String> imageurls=imageUrls;
        linearLayout.removeAllViews();
        if(imageurls.size()==1){
            ImageView imageView=new ImageView(this);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            //获得屏幕宽高
            WindowManager manager = this.getWindowManager();
            DisplayMetrics outMetrics = new DisplayMetrics();
            manager.getDefaultDisplay().getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int height=outMetrics.heightPixels;
            //加载图片
            GLideUtil.loadImageView(this,imageurls.get(0),imageView);
            imageView.setAdjustViewBounds(true);
            imageView.setMaxHeight(height*2/3);
            imageView.setMaxWidth(width*2/3);
            linearLayout.addView(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    BigImageActivity.launch(v.getContext(),imageUrls.get(0));
                }
            });
        }else if(imageurls.size()>1){
            NineGridImageView nineGridImageView=new NineGridImageView(this);
            nineGridImageView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            nineGridImageView.setGap(1);
            nineGridImageView.setShowStyle(NineGridImageView.STYLE_GRID);
            linearLayout.addView(nineGridImageView);
            nineGridImageView.setAdapter(new NineImageViewAdapter());
            nineGridImageView.setImagesData(imageurls);
        }else{

        }
    }


    /**
     * 拦截超链接
     * @param tv
     */
    private void interceptHyperLink(TextView tv) {
        tv.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = tv.getText();
        if (text instanceof Spannable) {
            int end = text.length();
            Spannable spannable = (Spannable) tv.getText();
            URLSpan[] urlSpans = spannable.getSpans(0, end, URLSpan.class);
            if (urlSpans.length == 0) {
                return;
            }

            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
            // 循环遍历并拦截 所有http://开头的链接
            for (URLSpan uri : urlSpans) {
                String url = uri.getURL();
                if (url.indexOf("http://") == 0) {
                    CustomUrlSpan customUrlSpan = new CustomUrlSpan(this,url);
                    spannableStringBuilder.setSpan(customUrlSpan, spannable.getSpanStart(uri),
                            spannable.getSpanEnd(uri), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            tv.setText(spannableStringBuilder);
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_weibo_details_layout;
    }

    @Override
    public void loadCommentSuccessed(WeiboDetailsCommentResp resp) {
        WeiboDetailsCommentfragment fragment= (WeiboDetailsCommentfragment) fragments[0];
        fragment.postData2UI(resp);
    }
}
