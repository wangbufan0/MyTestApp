package com.example.mytestapp.ui.weibo.homepage.binder.hlder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytestapp.R;
import com.example.mytestapp.ui.news.community.BigImage.BigImageActivity;
import com.example.mytestapp.ui.weibo.homepage.domin.WeiboHomepageResp;
import com.example.mytestapp.utils.GLideUtil;
import com.example.mytestapp.utils.StringUtil;
import com.jaeger.ninegridimageview.NineGridImageView;
import com.jaeger.ninegridimageview.NineGridImageViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeiboHomepageViewHolder extends RecyclerView.ViewHolder {

    private TextView id, neirong;
    private ImageView touxiang;
    private NineGridImageView nineGridImageView;


    public static WeiboHomepageViewHolder getInstance(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community_attention_layout,parent,false);
        return new WeiboHomepageViewHolder(view);
    }

    public WeiboHomepageViewHolder(@NonNull View itemView) {
        super(itemView);

        id = itemView.findViewById(R.id.mTv_id);
        neirong = itemView.findViewById(R.id.mTv_neirong);
        touxiang = itemView.findViewById(R.id.mIv_touxiang);
        nineGridImageView = itemView.findViewById(R.id.mNiv);

    }

    public void postDataToUI(final WeiboHomepageResp.StatusesBean data){

        id.setText(data.getUser().getName());
        neirong.setText(data.getText());
//
           GLideUtil.loadImageViewRound(itemView.getContext(),data.getUser().getAvatar_large(),touxiang);

        nineGridImageView.setAdapter(mAdapter);
        nineGridImageView.setImagesData(getMiddleImageURL(data.getPic_urls()));
    }

    //图片适配器
    private static NineGridImageViewAdapter<String> mAdapter = new NineGridImageViewAdapter<String>() {
        @Override//设置显示图片的方法
        protected void onDisplayImage(Context context, ImageView imageView, String s) {
            GLideUtil.loadImageViewLoding(context,s,imageView);
        }
        @Override//图片点击事件
        protected void onItemImageClick(Context context, int index, List<String> list) {
            BigImageActivity.launch(context,index,getLargeImageURL(list));
        }
    };

    private static List<String> getMiddleImageURL(List<WeiboHomepageResp.StatusesBean.PicUrlsBean> urls){
        //thumbnail 低 bmiddle 中 large 高
        String low = "thumbnail";
        String middle = "bmiddle";
        String large = "large";
        List<String> datas= new ArrayList<>();

        if(urls.isEmpty()) return datas;

        for(int i = 0;i<urls.size();i++){

            datas.add(StringUtil.replace(urls.get(i).getThumbnail_pic(),low,middle));
        }
        return datas;
    }

    private static List<String> getLargeImageURL(List<String> urls){
        //thumbnail 低 bmiddle 中 large 高
        String low = "thumbnail";
        String middle = "bmiddle";
        String large = "large";
        List<String> datas= new ArrayList<>();
        if(urls.isEmpty()) return datas;
        for(int i = 0;i<urls.size();i++){
            datas.add(StringUtil.replace(urls.get(i),middle,large));
            datas.get(0);
        }
        return datas;
    }

}
