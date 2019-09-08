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
public class WeiboDetailsAttitudeHolder extends RecyclerView.ViewHolder {

    public static WeiboDetailsAttitudeHolder launch(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weibo_details_conmment_layout,parent,false);
        return new WeiboDetailsAttitudeHolder(view);
    }
    private ImageView IvAvatar;
    private TextView TvName;
    private TextView tvSignature;

    public WeiboDetailsAttitudeHolder(@NonNull View itemView) {
        super(itemView);
        IvAvatar = itemView.findViewById(R.id.iv_avatar);
        TvName = itemView.findViewById(R.id.tv_name);
        tvSignature=itemView.findViewById(R.id.tv_signature);
    }

    public void postData2UI(WeiboDetailsCommentResp.CommentsBean data){
        GLideUtil.loadImageViewRound(itemView.getContext(),data.getUser().getProfile_image_url(),IvAvatar);
        TvName.setText(data.getUser().getName());
    }

}
