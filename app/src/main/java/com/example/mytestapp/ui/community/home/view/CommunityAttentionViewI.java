package com.example.mytestapp.ui.community.home.view;

import com.example.mytestapp.Base.View.BaseView;
import com.example.mytestapp.ui.community.home.domain.CommunityAttentionResp;

import java.util.List;

public interface CommunityAttentionViewI extends BaseView {

    void getDataSuccessed(List<CommunityAttentionResp> data);
}
