package com.example.mytestapp.ui.weibo.details.binder.holder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.R;
import com.example.mytestapp.ui.weibo.details.domin.WeiboDetailsCommentResp;
import com.example.mytestapp.utils.GLideUtil;

/**
 * @Name: WeiboDetailsCommentViewHolder
 * @Author: wangbufan
 * @Date: 2019/9/5 22:07
 * @Description:
 */
public class WeiboDetailsCommentViewHolder extends RecyclerView.ViewHolder {

    public static WeiboDetailsCommentViewHolder launch(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weibo_details_conmment_layout,parent,false);
        return new WeiboDetailsCommentViewHolder(view);
    }
    private ImageView IvAvatar;
    private TextView TvName;
    private TextView TvTime;
    private  TextView tvText;

    public WeiboDetailsCommentViewHolder(@NonNull View itemView) {
        super(itemView);
        IvAvatar = itemView.findViewById(R.id.iv_avatar);
        TvName = itemView.findViewById(R.id.tv_name);
        TvTime = itemView.findViewById(R.id.tv_time);
        tvText = itemView.findViewById(R.id.tv_text);
    }

    public void postData2UI(WeiboDetailsCommentResp.CommentsBean data){

        GLideUtil.loadImageViewRound(itemView.getContext(),data.getUser().getProfile_image_url(),IvAvatar);
        TvName.setText(data.getUser().getName());
        TvTime.setText(data.getCreated_at());
        tvText.setText(data.getText());

    }

}
