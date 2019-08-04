package com.example.mytestapp.ui.community.home.binder;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.mytestapp.ui.community.home.binder.hlder.CommunityAttentionViewHolder;
import com.example.mytestapp.ui.community.home.domain.CommunityAttentionResp;

import me.drakeet.multitype.ItemViewBinder;

public class CommunityAttentionBinder extends ItemViewBinder<CommunityAttentionResp, CommunityAttentionViewHolder> {
    @NonNull
    @Override
    protected CommunityAttentionViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return CommunityAttentionViewHolder.getInstance(parent);
    }

    @Override
    protected void onBindViewHolder(@NonNull CommunityAttentionViewHolder communityAttentionViewHolder, @NonNull CommunityAttentionResp communityAttentionResp) {
        communityAttentionViewHolder.postDataToUI(communityAttentionResp);
    }

}
