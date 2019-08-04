package com.example.mytestapp.ui.homepage.home.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.ui.homepage.home.binder.holder.HomepageHolder;
import com.example.mytestapp.ui.homepage.home.domain.HomepageResp;

import me.drakeet.multitype.ItemViewBinder;

public class HomepageBinder extends ItemViewBinder<HomepageResp.StoriesBean, HomepageHolder> {

    @NonNull
    @Override
    protected HomepageHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return HomepageHolder.getInstance(inflater,parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull HomepageHolder homepageHolder, @NonNull HomepageResp.StoriesBean topStoriesBean) {
        homepageHolder.postDataToUI(topStoriesBean);
    }


}
