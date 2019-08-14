package com.example.mytestapp.ui.homepage.home.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.ui.homepage.home.binder.holder.HomepageHolder;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp1;

import me.drakeet.multitype.ItemViewBinder;

public class HomepageBinder extends ItemViewBinder<HomepageResp1.ResultBean.DataBean, HomepageHolder> {

    @NonNull
    @Override
    protected HomepageHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return HomepageHolder.getInstance(inflater,parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull HomepageHolder homepageHolder, @NonNull HomepageResp1.ResultBean.DataBean topStoriesBean) {
        homepageHolder.postDataToUI(topStoriesBean);
    }


}
