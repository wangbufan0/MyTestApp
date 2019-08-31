package com.example.mytestapp.ui.weibo.homepage.binder.hlder;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.Base.clickablespan.CustomUrlSpan;
import com.example.mytestapp.R;
import com.example.mytestapp.ui.weibo.details.WeiboDetailsActivity;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;
import com.example.mytestapp.ui.weibo.util.NineImageViewAdapter;
import com.example.mytestapp.ui.weibo.util.WeiboImageUrlUtil;
import com.example.mytestapp.utils.GLideUtil;
import com.jaeger.ninegridimageview.NineGridImageView;

public class WeiboHomepageViewHolder extends RecyclerView.ViewHolder {

    private TextView id, neirong,repost,comment,attitude;
    private ImageView touxiang;
    private NineGridImageView nineGridImageView;


    public static WeiboHomepageViewHolder getInstance(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community_attention_layout, parent, false);
        return new WeiboHomepageViewHolder(view);
    }

    public WeiboHomepageViewHolder(@NonNull View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.mTv_id);
        neirong = itemView.findViewById(R.id.mTv_neirong);
        touxiang = itemView.findViewById(R.id.mIv_touxiang);
        nineGridImageView = itemView.findViewById(R.id.mNiv);
        repost= itemView.findViewById(R.id.tv_repost);
        comment = itemView.findViewById(R.id.tv_comment);
        attitude=itemView.findViewById(R.id.tv_attitudes);

    }

    public void postDataToUI(final WeiboHomepageResp.StatusesBean data) {

        id.setText(data.getUser().getName());
        neirong.setText(data.getText());
        interceptHyperLink(neirong);
        //头像
        GLideUtil.loadImageViewRound(itemView.getContext(), data.getUser().getAvatar_large(), touxiang);
        //九宫格图片
        nineGridImageView.setAdapter(new NineImageViewAdapter());
        nineGridImageView.setImagesData(WeiboImageUrlUtil.getMiddleImageURL(data.getPic_urls()));
        //点击跳转
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeiboDetailsActivity.launch(itemView.getContext(),data);
            }
        });
        //转发评论点赞
        repost.setText("转发："+data.getReposts_count());
        comment.setText("评论："+data.getComments_count());
        attitude.setText("点赞："+data.getAttitudes_count());
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
                    CustomUrlSpan customUrlSpan = new CustomUrlSpan(itemView.getContext(),url);
                    spannableStringBuilder.setSpan(customUrlSpan, spannable.getSpanStart(uri),
                            spannable.getSpanEnd(uri), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                }
            }
            tv.setText(spannableStringBuilder);
        }
    }



}
