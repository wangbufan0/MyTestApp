package com.example.mytestapp.ui.community.home.binder.hlder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.R;
import com.example.mytestapp.ui.community.BigImage.BigImageActivity;
import com.example.mytestapp.ui.community.home.domain.CommunityAttentionResp;
import com.example.mytestapp.utils.GLideUtil;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.List;

public class CommunityAttentionViewHolder extends RecyclerView.ViewHolder {

    private TextView id, neirong;
    private ImageView touxiang;
    private NineGridImageView nineGridImageView;


    public static CommunityAttentionViewHolder getInstance(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community_attention_layout,parent,false);
        return new CommunityAttentionViewHolder(view);
    }

    public CommunityAttentionViewHolder(@NonNull View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.mTv_id);
        neirong = itemView.findViewById(R.id.mTv_neirong);
        touxiang = itemView.findViewById(R.id.mIv_touxiang);
        nineGridImageView = itemView.findViewById(R.id.mNiv);

    }

    public void postDataToUI(CommunityAttentionResp data){

        id.setText(data.getId());
        neirong.setText(data.getNeirong());

        GLideUtil.loadImageViewRound(itemView.getContext(),data.getUrlTouxiang(),touxiang);

        //图片适配器
        NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
            @Override//设置显示图片的方法
            protected void onDisplayImage(Context context, ImageView imageView, String s) {
                GLideUtil.loadImageViewLoding(context,s,imageView);
            }

            @Override//自定义imageview
            protected ImageView generateImageView(Context context) {
                return super.generateImageView(context);
            }

            @Override//图片点击事件
            protected void onItemImageClick(Context context, int index, List<String> list) {

                BigImageActivity.launch(context,index,list);
            }
        };
        nineGridImageView.setAdapter(mAdapter);
        nineGridImageView.setImagesData(data.getUrlNineIv());



    }

}
